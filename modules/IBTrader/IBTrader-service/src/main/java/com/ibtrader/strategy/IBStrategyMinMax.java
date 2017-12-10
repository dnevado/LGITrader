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
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyMinMax extends StrategyImpl {

	
	private static Log _log = LogFactoryUtil.getLog(IBStrategyMinMax.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, Long> Parameters = new HashMap<String,Long>();
	
	
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_OFFSET1_FROM_OPENMARKET = "OffSet1FromOpenMarket";
	private static String _EXPANDO_OFFSET2_FROM_OPENMARKET = "OffSet2FromOpenMarket";
	
	
	/*Parameters.put("OffSet1FromOpenMarket", Long.valueOf(ExpandoColumnConstants.LONG));
	Parameters.put("OffSet2FromOpenMarket",  Long.valueOf(ExpandoColumnConstants.LONG));
	*/
	@Override
	public void execute(Share _share, Market _market) {
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
  		    
			//oContrat = (Contract) oTIMApiWrapper.createContract(ShareStrategy.getSymbol(), ShareStrategy.getSecurity_type(),ShareStrategy.getExchange(),oMarket.getCurrency(), _Expiration, "", 0);
			
	  		long  _INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(Order.class.getName()) +  this.getCLIENT_ID();
			
			/* insertamos control de ordenes de peticion */
			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
			_order.setCompanyId(_share.getCompanyId());
			_order.setGroupId(_share.getGroupId());
			_order.setShareID(_share.getShareId());
			/* pedimos tiempo real */
			IBOrderLocalServiceUtil.updateIBOrder(_order);
  		  
  		  
			// colocamos operacion de compra			
			Order BuyPositionTWS = new Order();
			
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId()));
			
			BuyPositionTWS.totalQuantity(new Long(_share.getNumbertopurchase()).intValue());
			BuyPositionTWS.orderType(PositionStates.ordertypes.LMT.toString());		    
		
			// precio del tick más o menos un porcentaje ...normalmente %1
			// ojo con los FUTUROS..llevan cambios porcentuales
			
			double ValueLimit =0.0;
			// largo 
			ValueLimit = this.getValueLimitIn();		
			
		/* 	if (bIsFutureStock)
			{
				ValueLimit = Utilidades.TickLimit_WithMultiplier(ValueToBuy,ShareStrategy.getTick_Futures(), ValueLimit, bReachedMin);
			}
			*/
			
			BuyPositionTWS.lmtPrice( Utilities.RedondeaPrice(ValueLimit));
			BuyPositionTWS.auxPrice(Utilities.RedondeaPrice(ValueLimit));
					
			/*  SI ES UNA COMPRA, NOS POSICIONAMOS CORTOS SI BAJA EL MINIMO */
			BuyPositionTWS.action(PositionStates.statusTWSFire.BUY.toString());			
			
			BuyPositionTWS.action(PositionStates.statusTWSFire.SELL.toString());
			
			_INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(Order.class.getName()) +  this.getCLIENT_ID();
			
			long  LastPositionID = Utilities.LastPositionIDTws(_share.getCompanyId(),_share.getGroupId());
		
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));
			
		//	BuyPositionSystem.setPositionID(new Long(LastPositionID));
			BuyPositionSystem.setDescription("");
			BuyPositionSystem.setPrice_in( this.getValueLimitIn());  // ojo, es estimativo
			BuyPositionSystem.setDate_in(new Date());// .setDate_buy(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			BuyPositionSystem.setShare_number(_share.getNumbertopurchase());
			BuyPositionSystem.setShareId(_share.getShareId());
			BuyPositionSystem.setState_in(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			BuyPositionSystem.setState(PositionStates.status.PENDING_BUY.toString());
			BuyPositionSystem.setLimit_price_in(Utilities.RedondeaPrice(ValueLimit));
			BuyPositionSystem.setType(BuyPositionTWS.getAction());
			//BuyPositionSystem.setRealtimeID_buy_alert(RealTimeID);
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
			
			
			BuyPositionSystem.setSimulation_mode(Utilities.IsSimulationMode(_share.getCompanyId()));
			
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
	public boolean verify(Share _share, Market _market) {
			
			
		boolean verified = false;
		boolean existsP = false;
											
		//LogTWM.log(Priority.INFO, ShareStrategy.getName() );		
		
		try
        {
		
		User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
			
		String HoraActual = Utilities.getHourNowFormat(_IBUser);
			
		/* supongamos cierre mercado a las 2200 */
		/* HoraDeadLineToClose sera la hora actual mas los minutos de la estrategia 
		Calendar calFechaActualWithDeadLine = Utilidades.getNewCalendarWithHour(HoraActual);
		calFechaActualWithDeadLine.add(Calendar.MINUTE,this.getSell_all_deadline_min_toclose());
		Calendar calFechaFinMercado = Utilidades.getNewCalendarWithHour(oMarket.getEnd_hour());
		*/
		
		existsP = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId());
		
			
		/*  CONTROLAMOS EL DEADLINE PARA COMPRAR */ 
		
		// maximos y minimos...ya lo tenemos de la tabla.
		//RealTime oShareMixMaxRTime = RealTimeDAO.getMinMaxRealTime(ShareStrategy.getShareId().intValue());
		// last tick  

		// verificamos compra previa. No compramos si hay una compra previa y estamos en el margen de tiempo de compra.
		// verificamos si ha pasado el tiempo necesario con los offset de lectura 
		
		// 00
		if (!existsP && HoraActual.compareTo(_market.getStart_hour())>=0  && HoraActual.compareTo(_market.getEnd_hour())<=0)		
		{	
			
			
			// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
			// comparamos que la hora de lectura final haya sobrepasado el actual 
			// HHMM
			

			// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.
			/* String HoraInicioLecturaMaxMin = Utilidades.getActualHourFormatPlusMinutes(oMarket.getStart_hour(), ShareStrategy.getOffset1min_read_from_initmarket());
			String HoraFinLecturaMaxMin = Utilidades.getActualHourFormatPlusMinutes(oMarket.getStart_hour(), ShareStrategy.getOffset2min_read_from_initmarket());
			
			 COMPROBAMOS ALGUN TIPO DE ERROR 
			if (HoraInicioLecturaMaxMin.contains("-1") || HoraFinLecturaMaxMin.contains("-1"))
			{
				LogTWM.log(Priority.ERROR, "[Estrategia:StrategyMinMax]: Errores formateando las horas de Max y Min de la acción. Hora[" + oMarket.getStart_hour()  + "], Offset1[" + ShareStrategy.getOffset1min_read_from_initmarket() + "]");
				return false;
			}
				
			
			if (HoraActual.compareTo(HoraFinLecturaMaxMin)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
			{
				
			
			// ya no obtenemos el maximo y mínimo, sino el correspondiente al tramo que me han dicho
			
			Calendar DateMinMaxFrom =Utilidades.getNewCalendarWithHour(HoraInicioLecturaMaxMin);
			Calendar DateMinMaxTo =Utilidades.getNewCalendarWithHour(HoraFinLecturaMaxMin);
			*/
			
			/* TIEMPOS REALES Y MAXIMOS Y MINIMOS CONSEGUIDOS */
			
					
					this.setValueIn(11F);															
					verified = true;
		}
					/* else
					//LogTWM.log(Priority.INFO, ShareStrategy.getSymbol() + " SuccessRuleRepeat:" + bSuccessRule + "------------" +
								"NUM_OPERATION_PROFIT_CORTO|NUM_OPERATION_LOST_LARGO|NUM_OPERATION_LOST_LARGO|NUM_OPERATION_LOST_LARGO " +
								"getBuy_limit_torepeat_same_share:      " + NUM_OPERATION_PROFIT_CORTO + "|" + NUM_OPERATION_LOST_LARGO
								+"|" + NUM_OPERATION_LOST_LARGO + "|" + NUM_OPERATION_LOST_LARGO  + "|" + oRuleRepeatShare.getBuy_limit_torepeat_same_share().intValue());		
					
					
					
				}
				
				}	
			}  *///fin de comprobar lectura de maximos y minimos.

		//}  fin de verificacion de operacion de compra previa 	
		
        }
		catch (Exception er)
        {
			_log.info(er.getMessage());
			//er.printStackTrace();
			verified = false;
        }

		return verified;
	}

	@Override
	public void init(long companyId) {
		// TODO Auto-generated method stub
		// CREAMOS LOS EXPANDOS NECESARIOS 
		
	Parameters.put(_EXPANDO_OFFSET1_FROM_OPENMARKET, Long.valueOf(ExpandoColumnConstants.LONG));
	Parameters.put(_EXPANDO_OFFSET2_FROM_OPENMARKET,  Long.valueOf(ExpandoColumnConstants.LONG));
	
	
	
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyMinMax.class.getName());
		long i = 0;
		for (Map.Entry<String, Long> parameter : Parameters.entrySet()) {
		
			String _paramName = parameter.getKey();
			Long _paramValue = parameter.getValue();
			
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
	
}
