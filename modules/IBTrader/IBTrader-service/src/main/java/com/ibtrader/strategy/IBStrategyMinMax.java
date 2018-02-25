package com.ibtrader.strategy;

import java.sql.Timestamp;
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
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.ibtrader.interactive.TIMApiGITrader;
import com.ibtrader.interactive.TIMApiWrapper;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyMinMax extends StrategyImpl {

	
	private boolean bReachedMax;
	private boolean bReachedMin;
	
	
	private static Log _log = LogFactoryUtil.getLog(IBStrategyMinMax.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, Double> Parameters = new HashMap<String,Double>();
	
	
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_OFFSET1_FROM_OPENMARKET = "OffSet1FromOpenMarket";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_OFFSET2_FROM_OPENMARKET = "OffSet2FromOpenMarket";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_TRADE_OFFSET_TO_CLOSEMARKET = "TradeUntilOffSetCloseMarket"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_PERCENTUAL_GAP = "PercentualGapFromMinMax";   // porcentaje de subida o baja hasta que se puede comprar, dentro de los limites 
	
    /* 
	public setApiWrapper(TIMApiWrapper wrapper)
	{
		
	}
	*/
	@Override
	public long execute(Share _share, Market _market) {
		// TODO Auto-generated method stub
		long returnValue=-1;
		try		
        {
			
			boolean existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId());
			if (existsPosition)
				return returnValue;
			_log.info("UserAccount: detectada posible entrada de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
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
  		   
  			
			// colocamos operacion de compra
			
			Order BuyPositionTWS = new Order();
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));		
			
			/* EXISTE ALGO SOBREESCRITO */
			long number_to_purchase = _share.getNumbertopurchase();
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0)>0)
    			number_to_purchase =this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0);    	
			
			BuyPositionTWS.totalQuantity(number_to_purchase);
			BuyPositionTWS.orderType(PositionStates.ordertypes.LMT.toString());		    
			// precio del tick más o menos un porcentaje ...normalmente %1
			// ojo con los FUTUROS..llevan cambios porcentuales
			BuyPositionTWS.lmtPrice(Utilities.RoundPrice(this.getValueLimitIn()));
			BuyPositionTWS.auxPrice(Utilities.RoundPrice(this.getValueLimitIn()));					
			/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */		
			BuyPositionTWS.action(PositionStates.statusTWSFire.BUY.toString());			
			if (bReachedMin)
			{
				BuyPositionTWS.action(PositionStates.statusTWSFire.SELL.toString());
			}
			
			_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
			
			this.setTargetOrder(BuyPositionTWS);			
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));			
			BuyPositionSystem.setDescription("");
			BuyPositionSystem.setPrice_in( this.getValueIn());  // ojo, es estimativo
			BuyPositionSystem.setDate_in(new Date());// .setDate_buy(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			BuyPositionSystem.setShare_number(number_to_purchase);
			BuyPositionSystem.setShareId(_share.getShareId());
			BuyPositionSystem.setState_in(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			BuyPositionSystem.setState(PositionStates.status.PENDING_BUY.toString());
			BuyPositionSystem.setLimit_price_in(BuyPositionTWS.lmtPrice());
			BuyPositionSystem.setType(BuyPositionTWS.getAction());
			BuyPositionSystem.setCompanyId(_share.getCompanyId());
			BuyPositionSystem.setGroupId(_share.getGroupId());
			BuyPositionSystem.setStrategy_in(this.getClass().getName());
    		/* 5 PUNTOS o 5%  POR DEFECTO EN LOS FUTUROS */			
    		double _defaultstop_percent = 0;
    		boolean _IsFuture = (_share.getSecurity_type()!=null && _share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_STOCK) ? true : false);
    		if (_IsFuture)
    		{	    			    			
    			_defaultstop_percent = (5 * 100) / this.getValueIn() /100;   
    		}
    		else
    		{
    			_defaultstop_percent = 0.05;
    		}
    		double stoplost =_share.getPercentual_stop_lost();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0)>0)
    			stoplost =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0);    			
    		if (stoplost>0)
    			BuyPositionSystem.setPercentualstoplost_out(stoplost);
    		else
    			BuyPositionSystem.setPercentualstoplost_out(_defaultstop_percent);
    		
    		double stopprofit =_share.getPercentual_stop_profit();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0)>0)
    			stopprofit =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0);    	
    		
    		if (stopprofit>0)
    			BuyPositionSystem.setPercentualstopprofit_out(stopprofit);
    		else
    			BuyPositionSystem.setPercentualstopprofit_out(_defaultstop_percent);    		
			/* BuyPositionSystem.setSell_percentual_stop_lost(ShareStrategy.getSell_percentual_stop_lost());
			BuyPositionSystem.setSell_percentual_stop_profit(ShareStrategy.getSell_percentual_stop_profit());*/
			BuyPositionSystem.setShare_number_traded(new Long(0));
			BuyPositionSystem.setShare_number_to_trade(number_to_purchase); 			
			String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, _share.getCompanyId(), _share.getGroupId());	
			boolean bSIMULATED_TRADING = simulated.equals("1");  
			BuyPositionSystem.setSimulation_mode(bSIMULATED_TRADING);			
			PositionLocalServiceUtil.updatePosition(BuyPositionSystem);
			/* Posicion en MYSQL de CONTROL */
			_log.info("Opening order " + BuyPositionSystem.getPositionId());
			
			/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
			returnValue =  BuyPositionSystem.getPositionId();

			
        }
		catch (Exception er)
	        {
				_log.info(er.getMessage());
				er.printStackTrace();
			
	        }
		return returnValue;
	}
	
	public IBStrategyMinMax() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl) {
	
	boolean verified = false;
	boolean existsPosition = false;
	try
    {
		
	User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
	String HoraActual = Utilities.getHourNowFormat(_IBUser);
	
	Calendar calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual);
	
//	StrategyShare _strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),_strategyImpl.getStrategyId());
	this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					
	
			
	calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_TRADE_OFFSET_TO_CLOSEMARKET));
	Calendar calFechaFinMercado = Utilities.getNewCalendarWithHour(_market.getEnd_hour());
	existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId());
	/*  CONTROLAMOS EL DEADLINE PARA COMPRAR */ 
	// maximos y minimos...ya lo tenemos de la tabla.
	//RealTime oShareMixMaxRTime = RealTimeDAO.getMinMaxRealTime(ShareStrategy.getShareId().intValue());

	// verificamos compra previa. No compramos si hay una compra previa y estamos en el margen de tiempo de compra.
	// verificamos si ha pasado el tiempo necesario con los offset de lectura 		
	// 00
	//_log.info("existsPosition:" +  existsPosition);
	if (!existsPosition &&  !calFechaActualWithDeadLine.after(calFechaFinMercado))		
	{	
		// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
		// comparamos que la hora de lectura final haya sobrepasado el actual 
		// HHMM
		// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.
		
		String HoraInicioLecturaMaxMin = Utilities.getActualHourFormatPlusMinutes(_market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_OFFSET1_FROM_OPENMARKET));
		String HoraFinLecturaMaxMin = Utilities.getActualHourFormatPlusMinutes(_market.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_OFFSET2_FROM_OPENMARKET));
		
		// COMPROBAMOS ALGUN TIPO DE ERROR 
		if (HoraInicioLecturaMaxMin.contains("-1") || HoraFinLecturaMaxMin.contains("-1"))
		{
			_log.info("[Estrategia:StrategyMinMax]: Errores formateando las horas de Max y Min de la acción. "
					+ "Hora[" + _market.getStart_hour()  + "], Offset1[" + this.getJsonStrategyShareParams().getInt(_EXPANDO_OFFSET1_FROM_OPENMARKET)+ "]");
			return false;
		}
			
		
		if (HoraActual.compareTo(HoraFinLecturaMaxMin)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
		{
		
		// ya no obtenemos el maximo y minimo, sino el correspondiente al tramo que me han dicho
		/* TIMEZONE AJUSTADO */
		Date _FromNow = Utilities.getDate(_IBUser);
		Date _ToNow   = Utilities.getDate(_IBUser);
		
		_FromNow = Utilities.setDateWithHour(_FromNow,HoraInicioLecturaMaxMin);
		_ToNow   = Utilities.setDateWithHour(_ToNow,HoraFinLecturaMaxMin);
		
		/* TIEMPOS REALES Y MAXIMOS Y MINIMOS CONSEGUIDOS */			
		List<Double[]>  lMinMaxRealTime = (List<Double[]>)     RealtimeLocalServiceUtil.findMinMaxRealTime(_FromNow, _ToNow, _share.getShareId(), _share.getCompanyId(), _share.getGroupId());  			
		Realtime oShareLastRTime = (Realtime)  RealtimeLocalServiceUtil.findLastRealTime(_share.getShareId(), _share.getCompanyId(), _share.getGroupId());
		
		/* TIENE QUE HABER UNA LISTA DE MINIMO Y MAXIMO */
		if (lMinMaxRealTime!=null && oShareLastRTime!=null && oShareLastRTime.getValue()>0 && lMinMaxRealTime.size()==1)
		{	
			/* double ValueToBuy =0.0; */
			/* estos son los dos limites que debe rotar la accion para comprar */
			
			Object[] aMinMax = (Object[]) lMinMaxRealTime.get(0);
			if (aMinMax[1]!=null && aMinMax[0]!=null)
			{					
			
				double MaxValue =  Double.valueOf(String.valueOf(aMinMax[1])).doubleValue();
				double MinValue = Double.valueOf(String.valueOf(aMinMax[0])).doubleValue();
						
				double MaxValueWithGap = (MaxValue * this.getJsonStrategyShareParams().getDouble(_EXPANDO_PERCENTUAL_GAP) / 100) +  MaxValue ;
				double MinValueWithGap = MinValue  - (MinValue  * this.getJsonStrategyShareParams().getDouble(_EXPANDO_PERCENTUAL_GAP) / 100) ;
				
				double MaxValueWithGapAndLimit = MaxValueWithGap  + (MaxValueWithGap * _share.getPercentual_limit_buy() / 100 );
				double MinValueWithGapAndLimit = MinValueWithGap  - (MinValueWithGap * _share.getPercentual_limit_buy() / 100);
				/* SE ALCANZA EL MAXIMO O MINIMO 
				 * CAMBIO 1.4.2013
				 *  EL REALTIME O CAMBIO DE TICK DEBE ESTAR ENTRE EL MAX O MIN Y EL BORDE SUPERIOR.
				 *  HASTA AHORA, VERIFICABAMOS QUE ESTUVIESE POR ENCIMA. NOS ASEGURAMOS CON EL PRECIO LIMITADO.
				 * */
				bReachedMax = ((oShareLastRTime.getValue() > MaxValueWithGap) &&  (oShareLastRTime.getValue() < MaxValueWithGapAndLimit));
				bReachedMin = ((oShareLastRTime.getValue()  < MinValueWithGap)  &&  (oShareLastRTime.getValue()> MinValueWithGapAndLimit));
			
		
				if (bReachedMax || bReachedMin || true)					
				{
					
				    this.setValueIn(oShareLastRTime.getValue());
					this.setValueLimitIn(MaxValueWithGapAndLimit);									
					this.setVerified(Boolean.TRUE);												
					verified = true;
					
					
				}
			}		//	if (aMinMax[1]!=null && aMinMax[0]!=null)		
			
		 } // 			if (oRTimeEnTramo!=null && oShareLastRTime!=null && oShareLastRTime.getValue()>0)

		}  //if (HoraActual.compareTo(HoraFinLecturaMaxMin)>0)   
	
    }// NO EXISTE POSICION 
    }
	catch (Exception er)
    {
		_log.info(er.getMessage());
		//er.printStackTrace();
		this.setVerified(Boolean.FALSE);				
    }

	return verified;
	}

	@Override
	public void init(long companyId) {
		// TODO Auto-generated method stub
		// CREAMOS LOS EXPANDOS NECESARIOS 
		
		Parameters.put(_EXPANDO_OFFSET1_FROM_OPENMARKET, Double.valueOf(ExpandoColumnConstants.DOUBLE));
		Parameters.put(_EXPANDO_OFFSET2_FROM_OPENMARKET,  Double.valueOf(ExpandoColumnConstants.DOUBLE));	
		Parameters.put(_EXPANDO_TRADE_OFFSET_TO_CLOSEMARKET,  Double.valueOf(ExpandoColumnConstants.DOUBLE));		
		Parameters.put(_EXPANDO_PERCENTUAL_GAP,  Double.valueOf(ExpandoColumnConstants.DOUBLE));
		ExpandoTable expandoTable;
		try {
			expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyMinMax.class.getName());
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
	 * strategyshare.strategyminmax.errorparams  =  El formato de los par�metros es err
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
			
			if (!Validator.isDigit(_paramValue))
			{
				bOK=Boolean.FALSE;
				this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams");
				break;
					
			}	
			
		}
		long OffSet1 = Long.valueOf(paramValues.get(_EXPANDO_OFFSET1_FROM_OPENMARKET));
		long OffSet2 = Long.valueOf(paramValues.get(_EXPANDO_OFFSET2_FROM_OPENMARKET));		
		/* PRIMERO MENOR QUE EL SEGUNDO */
		if (OffSet1>OffSet2)
		{
			bOK=Boolean.FALSE;
			this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams2");
		}
		
		return bOK;	
		
	}

	
	
}
