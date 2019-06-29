/*La idea es automatizar este modelo sobre ESZ4, actual vencimiento diciembre del futuro SP500, este campo Symbol cambiará cada trimestre igual que en el anterior desarrollo.

- Se debe capturar el tiempo real tick desde las 15:00 hora España hasta el cierre a 22:00
  en período temporal 5 minutos, donde la primera captura será el precio que refleje a las 15:00:00,  el segundo 15:05:00, tercero 15:10:00 y sucesivos.

- A estas capturas de 5 minutos le aplicamos una media móvil simple de 8 períodos.

- Cuando al cierre de una barra el precio rompa por encima de la mediamovil de 8 períodos y se sitúe por encima en el 75% del rango de la misma, se genera una orden de compra de 1 Futuro a mercado "Market". Y a al inversa cuando la barra perfore la media, venderá.

- El stop se ejecuta cuando una barra cierre en su 100% por debajo de la media movil.

- Cuando alcance 10 puntos de beneficio se ejecuta stop profit, aquí genera un campo donde el trader pueda modificar esta variable.

- Por ejemplo si se combra a 1980 y se vende a 1990, en este precio haría 2 contratos, 1 para cerrar la compra en 80 y otro abre un corto en 90.

- Es posible que debamos limitar el número de operaciones que realice en el día, tenlo en cuenta para crear un contador.

- No necesitamos base de datos anteriores.

1- Si la barra no cumple los 2 criterios, no compra, eso lo tenemos claro.
 A/ La barra debe cruzar la MM8 y al cierre el 75% de su cuerpo debe ser superior al precio cierre de la MM.
y B/ Además, el precio cierre de la barra será => que el 75% del rango.

2- La particularidad de este caso afecta a la primera sentencia, el cierre de la barra se sitúa en su 100% por encima de la MM, con lo cual debe comprar, el filtro B aquí no afecta.

* Cuando al cierre de una barra el precio rompa por encima de la mediamovil de x períodos 
		 * y se sitúe por encima en el % del rango de la misma, 
		 * se genera una orden de compra 
*
*/

package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.BaseIndicatorUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyStopLostProfitMobileAverage extends StrategyImpl {

	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyStopLostProfitMobileAverage.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, Double> Parameters = new HashMap<String,Double>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER = "Mobile Average Periods Number {8}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE = "Mobile Average Candle Size (Minutes) {5}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_GAP_SIZE = "Mobile Average Percentual Gap Size {75}"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET = "Mobile Average Trade Until x Minutes From CloseMarket"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET = "OffSet From Open Market (Minutes) To Start Trading";  // offset desde inicio de mercado en minutos


	long _num_macdP = 8;   // Periodos
	long  _num_macdT = 5;   // Tiempo de barras
	double _entryrate=75.0;    // Umbral de superacion en la barra en porcentaje 
	
	boolean bBuyOperation = Boolean.FALSE;									
	boolean bSellOperation = Boolean.FALSE;
	private Position currentPosition = null;

	JSONObject _tradeDescription;// // acumular la traza de los valores introducidos
	SimpleDateFormat TimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_SHORT_HOUR_FORMAT);


	
	@Override
	public long execute(Share _share, Market _market, Date backtestingdDate) {
		// TODO Auto-generated method stub
		long returnValue=-1;
		try		
        {
			String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());

			
			boolean existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
			if (!existsPosition)
				return returnValue;
			_log.info("UserAccount: detectada posible entrada de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
			_log.info(_tradeDescription);
			// hace falta???????? ..creo que si, para tener control sobre la operacion de compra /venta 
			SimpleDateFormat sdf = new SimpleDateFormat (Utilities._IBTRADER_FUTURE_SHORT_DATE);
			boolean bIsFutureStock = _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS)  && _share.getExpiry_date()!=null;
			String _Expiration = "";
		    if (bIsFutureStock)
				_Expiration = sdf.format(_share.getExpiry_date());		    
		    Contract oContrat = null;
  		    
  		   if (_share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
  		   {
  			  oContrat = new FutContract( _share.getSymbol(), _Expiration);
 			  //oContrat.multiplier(String.valueOf(oShare.getMultiplier()));		    					
			  oContrat.exchange(_share.getExchange());
			  oContrat.currency(_market.getCurrency());
  		   }
		   else		    					
				oContrat = new StkContract( _share.getSymbol());
  		   
  		   /* NECESARIO PRA LANZAR COMPRA DESDE EL CROUTIL */
  		   this.setTargetContract(oContrat);
  		   
	  	   String _OperationTYPE = PositionStates.statusTWSFire.BUY.toString(); 
	 	   if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString())) // operacion de compra normal..??
	 			_OperationTYPE = PositionStates.statusTWSFire.SELL.toString();		
			// colocamos operacion de compra	
	 	   		// colocamos operacion de compra
			if (!isSimulation_mode())
			{
	 	   
				Order BuyPositionTWS = new Order();
				BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));		
				
				/* EXISTE ALGO SOBREESCRITO */
				long number_to_purchase = _share.getNumbertopurchase();
	    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0)>0)
	    			number_to_purchase =this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0);    	
				
				BuyPositionTWS.totalQuantity(number_to_purchase);
				BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    
				// precio del tick mÃ¡s o menos un porcentaje ...normalmente %1
				// ojo con los FUTUROS..llevan cambios porcentuales
				
				/* ****************************************************************
				 * ****************************************************************
				 * SIEMPRE HAY QUE PONERLO AUNQUE VAYA A MERCADO lmtprice & auxprice
				 * ****************************************************************
				 * **************************************************************** */
				BuyPositionTWS.lmtPrice(Utilities.RoundPrice(this.getValueLimitIn()));
				BuyPositionTWS.auxPrice(Utilities.RoundPrice(this.getValueLimitIn()));					
				/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */		
				BuyPositionTWS.action(_OperationTYPE);			
							
				_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
				
				this.setTargetOrder(BuyPositionTWS);	
			}
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			//currentPosition.setState_out(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			/* si metemos el date sell en las parciales, no entran las siguientes */
			/* acumulo las acciones vendidas y a vender en la operativa */
			currentPosition.setPrice_out(this.getValueIn());
			currentPosition.setDate_out(!isSimulation_mode() ?  new Date() : backtestingdDate);
			currentPosition.setDescription(this.getClass().getName());
			currentPosition.setStrategy_out(this.getClass().getName());		 		
			currentPosition.setDescription(currentPosition.getDescription() + StringPool.RETURN_NEW_LINE + _tradeDescription.toString());	
			
			/* MODO FAKE CUENTA DEMO */
			currentPosition = Utilities.fillStatesOrder(currentPosition);
			/* END MODO FAKE CUENTA DEMO */
			PositionLocalServiceUtil.updatePosition(currentPosition);
			/* Posicion en MYSQL de CONTROL */
			_log.info("Opening order " + currentPosition.getPositionId());
			
			/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
			returnValue =  currentPosition.getPositionId();
			/* Posicion en MYSQL de CONTROL */
			_log.info("Opening order " + currentPosition.getPositionId());
			
			
			/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
			returnValue =  currentPosition.getPositionId();

			
        }
		catch (Exception er)
	        {
				_log.info(er.getMessage());
				er.printStackTrace();
			
	        }
		return returnValue;
	}
	
	public IBStrategyStopLostProfitMobileAverage() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	
	boolean verified = Boolean.FALSE;
	
	boolean existsPositionToExit = false;
	try
    {
		
		String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());

		
		if (_strategyImpl.getStrategyparamsoverride()==null)
			return Boolean.FALSE;
		
		existsPositionToExit = PositionLocalServiceUtil.ExistsPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
		if (existsPositionToExit)	
		{
			currentPosition = PositionLocalServiceUtil.findPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
	
		
	    this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					
	
		User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
		
		String HoraActual = "";
		
		Calendar calFechaActualWithDeadLine = null;
		
		if (!isSimulation_mode())
		{
			HoraActual = Utilities.getHourNowFormat(_IBUser);
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual);
		}
		else	
		{
			HoraActual = Utilities.getHourNowFormat(_IBUser,backtestingdDate);
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(backtestingdDate, HoraActual);					
		}		
		
									
		calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET));
		
		/*  CONTROLAMOS EL DEADLINE PARA COMPRAR */ 
		// maximos y minimos...ya lo tenemos de la tabla.
		//RealTime oShareMixMaxRTime = RealTimeDAO.getMinMaxRealTime(ShareStrategy.getShareId().intValue());
	
		// verificamos compra previa. No compramos si hay una compra previa y estamos en el margen de tiempo de compra.
		// verificamos si ha pasado el tiempo necesario con los offset de lectura 		
		// 00
		//_log.info("existsPosition:" +  existsPosition);
		
		_num_macdP = this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER,0);
		_num_macdT = this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,0);
		_entryrate = this.getJsonStrategyShareParams().getDouble(_EXPANDO_MOBILE_AVERAGE_GAP_SIZE,0.0f) / 100;
		
		
		if (_num_macdP==0 || _num_macdT==0 || (!(_entryrate>0)))
			return Boolean.FALSE;
		
		/* SOLO PODEMOS ENTRAR EN EL PERIODO MARCADO DE CADA MINUTO, PARA LO CUAL OBTENEMOS EL RESTO */	
		/* TIMEZONE AJUSTADO */
		Date _FromNow =  !isSimulation_mode() ?   Utilities.getDate(_IBUser) : backtestingdDate;

		Calendar _calendarFromNow = Calendar.getInstance();
		_calendarFromNow.setTime(_FromNow);		
		_calendarFromNow.set(Calendar.SECOND, 0);
		_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
		long _ModMinuteToEntry = _calendarFromNow.get(Calendar.MINUTE) % _num_macdT;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			return Boolean.FALSE;
	
		// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
		// comparamos que la hora de lectura final haya sobrepasado el actual 
		// HHMM
		// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.		
		String StartHourTrading = "";
		if (!isSimulation_mode())
			 StartHourTrading =  Utilities.getActualHourFormatPlusMinutes(_calendarFromNow, _market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
		else				
			 StartHourTrading = Utilities.getActualHourFormatPlusMinutes(_market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));		// COMPROBAMOS ALGUN TIPO DE ERROR 
		if (StartHourTrading.contains("-1"))
		{
			_log.info("[ Errores formateando las horas de StarHourTrading de la accion. Hora[" + _market.getStart_hour()  + "], Offset1[" + this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET)+ "]");
			return Boolean.FALSE;
		}
			
		
		if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
		{
		
		/* TIMEZONE AJUSTADO */		
		Calendar _calendarFromDate_In = Calendar.getInstance(); // fecha de la compra  currentPosition
		_calendarFromDate_In.setTime(currentPosition.getDate_real_in());		
		_calendarFromDate_In.set(Calendar.SECOND, 0);
		
		/* NO ES EL SIGUIENTE BLOQUE DE BARRA , PODRIAN SOLAPARSE ENTRADA/SALIDA, IGNORAMOS SEGUNDOS DE LA TABLA DE BBDD */
		if (!calFechaActualWithDeadLine.after(_calendarFromDate_In))
			return Boolean.FALSE;
		
		Double vRealtime = null;
		if (!isSimulation_mode())
		{
			Realtime oRTimeEnTramo =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _calendarFromNow.getTime());
			vRealtime  = Validator.isNull(oRTimeEnTramo) ? null : oRTimeEnTramo.getValue();
		}
		else
		{
			HistoricalRealtime oRTimeEnTramo =  HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _calendarFromNow.getTime());
			vRealtime  = Validator.isNull(oRTimeEnTramo) ? null : oRTimeEnTramo.getValue();
		}
		
		
		
		if (vRealtime!=null)
		{
			// todo fue bien, tenemos media movil y de los periodos solicitados.
			// buscamos el cierre de la barra, ultimo valor < que el MINUTE.00  (15.00, 20,00, 25.00)
//			Double _avgMobileSimple = MobileAvgUtil.getSimpleAvgMobile(_calendarFromNow.getTime(), _num_macdT, _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_macdP, isSimulation_mode(), _market);
			Double _avgMobileSimple = BaseIndicatorUtil.getExponentialAvgMobile(_calendarFromNow.getTime(), vRealtime.doubleValue(), _num_macdT, _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_macdP , isSimulation_mode(), _market);

			if (Validator.isNotNull(_avgMobileSimple))
			{
				/* PERIODO CERO , JUSTAMENTE LA BARRA ANTERIOR */
				
				/* PERIODO CERO , JUSTAMENTE LA BARRA ANTERIOR */
				double max_value = 0;
				double min_value = 0;
				double _WidthRangeBar = 0;
				
				if (!isSimulation_mode())
				{
					Realtime oRTimeWidthRange = BaseIndicatorUtil.getMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 0,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
					max_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
					min_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;
				}
				else
				{
					HistoricalRealtime oRTimeWidthRange = BaseIndicatorUtil.getHistoricalMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 0,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
					max_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
					min_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;
				}				
				if (max_value >0  && min_value>0)
				{
					/* RAGO DE LA barra */
					 _WidthRangeBar = max_value - min_value; 
					/* VARIABLE PARA CONTROLAR QUE SI LA BARRA NO CORTE LA MM, NOS ASEGUREMOS QUE LA
					 * BARRA N-1 SI LA CORTE 
					 */
														
					boolean _AvgMovil_InsideBar  = (max_value > _avgMobileSimple.doubleValue()  &&
							min_value< _avgMobileSimple.doubleValue());
					
					boolean _BuySuccess = false;
					boolean _SellSuccess = false;
					
					// que no entre si es la primera posicion y viene de una mecanismo de continuidad de la señal anterior

					
					double _WidthBarRangePercent =  _WidthRangeBar *  _entryrate;
					
					/* HAY CAMBIO DE TENDENCIA */
					boolean _MarketTrendChanged = true;
					if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString()) && 
							vRealtime.doubleValue() >_avgMobileSimple.doubleValue())
					{
									_MarketTrendChanged = false;
					}
					if (currentPosition.getType().equals(PositionStates.statusTWSFire.SELL.toString()) && 
							vRealtime.doubleValue() <_avgMobileSimple.doubleValue())
					{
									_MarketTrendChanged = false;
					}
					
					
					/* A/ La barra debe cruzar la MM8 y al cierre el 75% de su cuerpo debe ser superior al precio cierre de la MM.
					y B/ Además, el precio cierre de la barra será => que el 75% del rango.*/
					
					/* 2- La particularidad de este caso afecta a la primera sentencia, el cierre de la barra se sitúa en su 100% por encima de la MM, con lo cual debe comprar, el filtro B aquí no afecta.
					 * */
					_BuySuccess = BaseIndicatorUtil._IsBuySignalMM8_5MINBar(_avgMobileSimple, max_value,min_value, _WidthBarRangePercent, vRealtime);
					_BuySuccess =  (_BuySuccess && currentPosition.getType().equals(PositionStates.statusTWSFire.SELL.toString()));

					_SellSuccess = BaseIndicatorUtil._IsSellSignalMM8_5MINBar(_avgMobileSimple, max_value,min_value, _WidthBarRangePercent, vRealtime);
					_SellSuccess = (_SellSuccess && currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString()));

					/*_BuySuccess = _BuySuccess &&  
							(_OperationFilter.equals("ALL") || _OperationFilter.equals(PositionStates.statusTWSFire.BUY.toString())); 

					
					_SellSuccess = _SellSuccess  &&  
							(_OperationFilter.equals("ALL") || _OperationFilter.equals(PositionStates.statusTWSFire.SELL.toString()));
					*/
					/*  DNM_RAFADIESTRO  2015 06 16. COMENTAMOS LA CLAUSULA DE QUE NO LA CORTE */ 
					if  ((!_AvgMovil_InsideBar  &&_MarketTrendChanged)  ||  (_AvgMovil_InsideBar && (_SellSuccess || _BuySuccess)))
					{
						
					    this.setValueIn(vRealtime.doubleValue());											
						this.setVerified(Boolean.TRUE);												
						verified = true;
						/* OJO, PUEDE SER QUE NO DE SEÑAL DE COMPRA Y VENTA SEGUN CONDICIONES,
						 * SI NO QUE SE VERIFICA QUE N NO CORTE LA BARRA LA MM  SI
						 */					
						this.bBuyOperation = _BuySuccess;									
						this.bSellOperation = _SellSuccess;
						
						
						_tradeDescription = JSONFactoryUtil.createJSONObject();
						_tradeDescription.put("_WidthBarRangePercent", _WidthBarRangePercent);
						_tradeDescription.put("_AvgMovil_InsideBar", _AvgMovil_InsideBar);
						_tradeDescription.put("_WidthRangeBar", _WidthRangeBar);
						_tradeDescription.put("oRTimeWidthRange.getMax_value()", max_value);
						_tradeDescription.put("oRTimeWidthRange.getMin_value()", min_value);
						_tradeDescription.put("oRTimeEnTramo", vRealtime.doubleValue());
						_tradeDescription.put("_avgMobileSimple", _avgMobileSimple.doubleValue());
						_tradeDescription.put("_periods:", _num_macdP);
						_tradeDescription.put("_candlesize(minutes):", _num_macdT);
						_tradeDescription.put("_barentryrate", _entryrate);										
						_tradeDescription.put("_calendarFromNow", TimeFormat.format(_calendarFromNow.getTime()));
					
					}
				} // 				if (oRTimeWidthRange!=null  && oRTimeWidthRange.getMax_value()>0 && oRTimeWidthRange.getMin_value()>0)


			} // if (oRTimeEnTramo!=null)
		}
		   	
	  } //		if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa

	} // if (existsPositionToExit)
    } // en try
	catch (Exception er)
    {
		_log.info(er.getMessage());
		this.setVerified(Boolean.FALSE);				
    }

	return verified;
	}

	@Override
	public void init(long companyId) {
	// TODO Auto-generated method stub
	// CREAMOS LOS EXPANDOS NECESARIOS 
		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER, Double.valueOf(ExpandoColumnConstants.INTEGER));
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,  Double.valueOf(ExpandoColumnConstants.INTEGER));	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_GAP_SIZE,  Double.valueOf(ExpandoColumnConstants.DOUBLE));  // ESTE ES EL UNICO DOUBLE	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET,  Double.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET,  Double.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	
		
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategySimpleMobileAverage.class.getName());
		long i = 0;
		for (Map.Entry<String, Double> parameter : Parameters.entrySet()) {
		
			String _paramName = parameter.getKey();
			Double _paramValue = parameter.getValue();
			
			/* EXISTE, SI NO , LO CREAMOS */
			ExpandoColumn ExistsExpando = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), _paramName);
			
			if (ExistsExpando==null)
			{
				ExpandoColumn paramColumn = ExpandoColumnLocalServiceUtil.createExpandoColumn(CounterLocalServiceUtil.increment(ExpandoColumn.class.getName()));
				paramColumn.setName(_paramName);
				paramColumn.setType(_paramValue.intValue());	
				paramColumn.setTypeSettings("hidden=0\nindex-type=0\nvisible-with-update-permission=0");
				paramColumn.setCompanyId(companyId);					
				paramColumn.setTableId(expandoTable.getTableId());
				ExpandoColumnLocalServiceUtil.updateExpandoColumn(paramColumn);
				
				ExpandoColumns.add(paramColumn);
			}
			else
			{
				ExpandoColumns.add(ExistsExpando);	
			}
			
		}
		/* method of the interface */
		this.setIBStrategyParams(ExpandoColumns);
		
		
	} catch (PortalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	/* CUSTOM VALIDATOR OF EXPANDO FIELDS 
	 * strategyshare.strategyminmax.errorparams  =  El formato de los parï¿½metros es err
	// strategyshare.strategyminmax.errorparams2  = El desplazamiento desde inicio de mercado debe ser menor que el fin.
	 * 
	 * */
	@Override
	public boolean validateParams(Map<String, String> paramValues) {
	// TODO Auto-generated method stub
	//return super.validateParams(paramValues);
	this.setValidateParamsKeysError("");
	boolean bOK = Boolean.TRUE;
	for (Map.Entry<String, String> parameter : paramValues.entrySet()) {
		String _paramValue = parameter.getValue();
		
		if (!Utilities.isNumber(_paramValue))
		{
			bOK=Boolean.FALSE;
			this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams");
			break;
				
		}	
		
	}
	return bOK;	
		
	}
}	