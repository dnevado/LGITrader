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
	public void execute(Share _share, Market _market, TIMApiWrapper wrapper) {
		// TODO Auto-generated method stub
		try
        {
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
  		    
  			 long  _INCREMENT_ORDER_ID = wrapper.getCurrentOrderId();
			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);			/* insertamos control de ordenes de peticion */
			_order.setCompanyId(_share.getCompanyId());
			_order.setGroupId(_share.getGroupId());
			_order.setShareID(_share.getShareId());
			_order.setCreateDate(new Date());
			_order.setModifiedDate(new Date());			
			IBOrderLocalServiceUtil.updateIBOrder(_order);
			// colocamos operacion de compra
			
			Order BuyPositionTWS = new Order();
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyUSER_TWS, _share.getCompanyId(), _share.getGroupId()));			
			BuyPositionTWS.totalQuantity(new Long(_share.getNumbertopurchase()).intValue());
			BuyPositionTWS.orderType(PositionStates.ordertypes.LMT.toString());		    
			// precio del tick más o menos un porcentaje ...normalmente %1
			// ojo con los FUTUROS..llevan cambios porcentuales
			double ValueLimit = 0.0;
			// largo 
			ValueLimit = this.getValueLimitIn();		
			// precio del tick m�s o menos un porcentaje ...normalmente %1
			// ojo con los FUTUROS..llevan cambios porcentuales
			
			 ValueLimit =0.0;
			// largo 
			ValueLimit = this.getValueIn() + (this.getValueIn()  * this.getJsonStrategyShareParams().getDouble(_EXPANDO_PERCENTUAL_GAP));			
			if (bReachedMin) // corto
			{
				ValueLimit = this.getValueIn()  - (this.getValueIn()   * this.getJsonStrategyShareParams().getDouble(_EXPANDO_PERCENTUAL_GAP));				
			}
			
			if (bIsFutureStock)
			{
				ValueLimit = Utilities.TickLimit_WithMultiplier(this.getValueIn(),_share.getTick_futures(), ValueLimit, bReachedMin);
			}
			
			BuyPositionTWS.lmtPrice(Utilities.RoundPrice(ValueLimit));
			BuyPositionTWS.auxPrice(Utilities.RoundPrice(ValueLimit));					
			/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */
			/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */
			BuyPositionTWS.action(PositionStates.statusTWSFire.BUY.toString());			
			if (bReachedMin)
			{
				BuyPositionTWS.action(PositionStates.statusTWSFire.SELL.toString());
			}
			
			wrapper.openOrder(new Long(_INCREMENT_ORDER_ID).intValue(), oContrat, BuyPositionTWS,null);
			
		//	_INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(Order.class.getName()) +  this.getCLIENT_ID();
			//long  LastPositionID = Utilities.LastPositionIDTws(_share.getCompanyId(),_share.getGroupId());
		
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));
			
		//	BuyPositionSystem.setPositionID(new Long(LastPositionID));
			BuyPositionSystem.setDescription("");
			BuyPositionSystem.setPrice_in( this.getValueIn());  // ojo, es estimativo
			BuyPositionSystem.setDate_in(new Date());// .setDate_buy(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			BuyPositionSystem.setShare_number(_share.getNumbertopurchase());
			BuyPositionSystem.setShareId(_share.getShareId());
			BuyPositionSystem.setState_in(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			BuyPositionSystem.setState(PositionStates.status.PENDING_BUY.toString());
			BuyPositionSystem.setLimit_price_in(Utilities.RoundPrice(ValueLimit));
			BuyPositionSystem.setType(BuyPositionTWS.getAction());
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
    		if (_share.getPercentual_stop_lost()>0)
    			BuyPositionSystem.setPercentualstoplost_out(Double.valueOf(_share.getPercentual_stop_lost()));
    		else
    			BuyPositionSystem.setPercentualstoplost_out(_defaultstop_percent);
    		
    		if (_share.getPercentual_stop_profit()>0)
    			BuyPositionSystem.setPercentualstopprofit_out(Double.valueOf(_share.getPercentual_stop_profit()));
    		else
    			BuyPositionSystem.setPercentualstopprofit_out(_defaultstop_percent);    		
			/* BuyPositionSystem.setSell_percentual_stop_lost(ShareStrategy.getSell_percentual_stop_lost());
			BuyPositionSystem.setSell_percentual_stop_profit(ShareStrategy.getSell_percentual_stop_profit());*/
			BuyPositionSystem.setShare_number_traded(new Long(0));
			BuyPositionSystem.setShare_number_to_trade(_share.getNumbertopurchase()); 
			
			String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, _share.getCompanyId(), _share.getGroupId());	
			boolean bSIMULATED_TRADING = simulated.equals("1");  
			BuyPositionSystem.setSimulation_mode(bSIMULATED_TRADING);			
			PositionLocalServiceUtil.updatePosition(BuyPositionSystem);
			/* Posicion en MYSQL de CONTROL */
			_log.info("Opening order...");

			
        }
			catch (Exception er)
	        {
				_log.info(er.getMessage());
				er.printStackTrace();
			
	        }
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
		
		/* supongamos cierre mercado a las 2200 */
		/* HoraDeadLineToClose sera la hora actual mas los minutos de la estrategia 
		
		calFechaActualWithDeadLine.add(Calendar.MINUTE,this.getSell_all_deadline_min_toclose());
		Calendar calFechaFinMercado = Utilities.getNewCalendarWithHour(_market.getEnd_hour());
		*/
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
					double MinValueWithGapAndLimit = MinValueWithGap - (MinValueWithGap * _share.getPercentual_limit_buy() / 100);
					/* SE ALCANZA EL MAXIMO O MINIMO 
					 * CAMBIO 1.4.2013
					 *  EL REALTIME O CAMBIO DE TICK DEBE ESTAR ENTRE EL MAX O MIN Y EL BORDE SUPERIOR.
					 *  HASTA AHORA, VERIFICABAMOS QUE ESTUVIESE POR ENCIMA. NOS ASEGURAMOS CON EL PRECIO LIMITADO.
					 * */
					bReachedMax = ((oShareLastRTime.getValue() > MaxValueWithGap) &&  (oShareLastRTime.getValue() < MaxValueWithGapAndLimit));
					bReachedMin = ((oShareLastRTime.getValue()  < MinValueWithGap)  &&  (oShareLastRTime.getValue()> MinValueWithGapAndLimit));
				
			
					if (bReachedMax || bReachedMin)					
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
