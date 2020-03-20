

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
import com.ibtrader.data.model.AuditIndicatorsStrategy;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.AuditIndicatorsStrategyLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;
import com.ibtrader.data.service.persistence.impl.AuditIndicatorsStrategyPersistenceImpl;
import com.ibtrader.techindicadors.IBTraderPricesPeaksValleyIndicator;
import com.ibtrader.techindicadors.IBTraderPeaksValleyIndicator;
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
import com.ibtrader.util.CandleUtil;
import com.ibtrader.util.AroonIndicatorUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.DirectionalMovementADXRUtil;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;


/* 
 * La divergencia alcista se identifica cuando se forman dos mínimos consecutivos, y el más reciente ha alcanzado un nivel más bajo que su precedente. Al mismo tiempo, el histograma del MACD alcanza dos mínimos alcistas, esto es que el más reciente de los cuales se encuentra a un nivel superior que el mínimo anterior.
  La divergencia bajista se identifica cuando se forman dos máximos consecutivos alcistas, el más reciente es más alto que su precedente. A la vez, el histograma del MACD tiene dos máximos descendientes consecutivos, el más reciente de los cuales se encuentra más bajo que el anterior.
 * 
 * divergencia floja, porque el indicador no llega a cruzar cero entre ambos picos negativos, que sería lo ideal.
 * 
 * 
 *
 * 
 * 
 * STRATEGIA DESCRITA AQUI 
 * 
 *  1. DIVERGENCIAS PRECIO 
 *  2- ROTURAS MINIMOS / MAXIMOS RELATIVOS 
 *  		A) ENTRAR EN LA ROTURA PARA CONFIRMAR LA DIVERGENCIA 
 *  		B) ENTRAR EN LA CONFIFORMACION SEGUN COMENTA EL GRAFICO 
 *  https://www.rankia.com/blog/vender-para-comprar/2556484-estrategia-usando-divergencia-macd 
 *  https://www.rankia.com/blog/vender-para-comprar/2561254-aplicando-macd-analisis-iag
 *  https://www.babypips.com/learn/forex/9-rules-for-trading-divergences
 *  */
public class IBStrategyPriceDivergences extends StrategyImpl {

	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyPriceDivergences.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, String> Parameters = new HashMap<String,String>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE = "Mobile Average Candle Size (Minutes) {5}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET = "Mobile Average Trade Until x Minutes From CloseMarket {0}"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET = "OffSet From Open Market (Minutes) To Start Trading {30}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE = "Operation Type [ALL, BUY, SELL]";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MACD_PERIODOS_SHORT_EXP_MOBILE = "MACD Short-Avg Mobile Periods {12}";  // {} SINTAXIS PARA VALORES POR DEFECTO   
	private static String _EXPANDO_MACD_PERIODOS_LONG_EXP_MOBILE = "MACD  Long-Avg Mobile Periods {26}";  //   SINTAXIS PARA VALORES POR DEFECTO	
	private static String _EXPANDO_MACD_PERIODOS_SIGNALLINE_EXP_MOBILE = "MACD  Signal Line Avg Mobile Periods {9}";  //   SINTAXIS PARA VALORES POR DEFECTO
	
	long  _num_stochasticP = 8;   // Periodos
	long  _num_macdP = 8;   // Periodos
	long  _num_macdT = 5;   // Tiempo de barras
	
	long  _num_shortAvgMACD_P = 12;   // Tiempo de barras
	long  _num_longAvgMACD_P = 26;   // Tiempo de barras
	long  _num_signalLineMACD_P = 9;   // Tiempo de barras
	
	
	String operationfilter="";    // ALL, BUY, SELL
	
	
	boolean bBuyOperation = Boolean.FALSE;									
	boolean bSellOperation = Boolean.FALSE;
	JSONObject _tradeDescription;// // acumular la traza de los valores introducidos
	
	SimpleDateFormat TimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_SHORT_HOUR_FORMAT);
	SimpleDateFormat auditTimeFormat = new SimpleDateFormat (Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);

	@Override
	public long  execute(Share _share, Market _market,  Date backtestingdDate)
	{
		long returnValue =-1;
		try		
        {
			String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());
			boolean existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),
					_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
			if (existsPosition)
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
		   long number_to_purchase = _share.getNumbertopurchase();

		   String action = PositionStates.statusTWSFire.BUY.toString();
		   if (bSellOperation)
		   {
			   action = PositionStates.statusTWSFire.SELL.toString();
		   }
		   
			// colocamos operacion de compra
  			if (!isSimulation_mode())
  			{
  			
				Order BuyPositionTWS = new Order();
				BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));		
				
				/* EXISTE ALGO SOBREESCRITO */
	    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0)>0)
	    			number_to_purchase =this.getJsonStrategyShareParams().getInt(ConfigKeys._FIELD_NUMBER_TO_PURCHASE,0);    	
				
	    		User user = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
			    boolean bOrderIsWithinBudget =   PositionLocalServiceUtil.IsinRangeUserBudget(user,_share.getMultiplier() *  this.getValueIn() * number_to_purchase, position_mode, _share.getCompanyId(), _share.getGroupId());
			    if (!bOrderIsWithinBudget)
			    	return returnValue;
			    
			    
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
				BuyPositionTWS.action(action);			
				
				_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
				
				this.setTargetOrder(BuyPositionTWS);			
  			}
			/* Posicion en MYSQL de CONTROL. OJO...ANTES SIEMPRE PARA DESPUES CONTROLARLA EN CASO DE ERROR. */
			Position BuyPositionSystem = PositionLocalServiceUtil.createPosition(CounterLocalServiceUtil.increment(Position.class.getName()));
			BuyPositionSystem.setBacktestingId(ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
			BuyPositionSystem.setPrice_in( this.getValueIn());  // ojo, es estimativo
			BuyPositionSystem.setDate_in(!isSimulation_mode() ? new Date() : backtestingdDate);// .setDate_buy(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			BuyPositionSystem.setShare_number(number_to_purchase);
			BuyPositionSystem.setShareId(_share.getShareId());
		
			/* BuyPositionSystem.setState_in(PositionStates.statusTWSCallBack.PendingSubmit.toString());
			BuyPositionSystem.setState(PositionStates.status.PENDING_BUY.toString()); */
			BuyPositionSystem.setType(action);
			BuyPositionSystem.setCompanyId(_share.getCompanyId());
			BuyPositionSystem.setGroupId(_share.getGroupId());
			BuyPositionSystem.setStrategy_in(this.getClass().getName());
			BuyPositionSystem.setDescription(_tradeDescription.toString());
			
    		/* BuyPositionSystem.setSell_percentual_stop_lost(ShareStrategy.getSell_percentual_stop_lost());
			BuyPositionSystem.setSell_percentual_stop_profit(ShareStrategy.getSell_percentual_stop_profit());*/			
			
			/* MODO FAKE CUENTA DEMO */
			BuyPositionSystem.setPosition_mode(position_mode);			
    		BuyPositionSystem = Utilities.fillStatesOrder(BuyPositionSystem);
			/* END MODO FAKE CUENTA DEMO */
 
       		double stoplost =_share.getPercentual_stop_lost();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0)>0)
    			stoplost =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_LOST,0);    			
    		if (stoplost>0)    			
    			BuyPositionSystem.setPercentualstoplost_out(stoplost);
    		/* else
    			BuyPositionSystem.setPercentualstoplost_out(_defaultstop_percent);
    		*/
    		/* tralling stop lost */
    		double percentualtraillingstoplost =_share.getPercentual_trailling_stop_lost();
    		double pricetrailingstop;
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_TRAILLING_STOP_LOST,0)>0)
    			percentualtraillingstoplost =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_TRAILLING_STOP_LOST,0);
    		
    		if (percentualtraillingstoplost>0)    			
    		{	
    			BuyPositionSystem.setPercentual_trailling_stop_lost(percentualtraillingstoplost);
    			if (BuyPositionSystem.getType().equals(PositionStates.statusTWSFire.BUY.toString()))  // operacion de compra normal..??
    				pricetrailingstop = (this.getValueIn() - (this.getValueIn() * percentualtraillingstoplost /100));
    			else
    				pricetrailingstop = (this.getValueIn() + (this.getValueIn() * percentualtraillingstoplost /100));
    			
    			BuyPositionSystem.setPricetrailling_stop_lost(Utilities.RoundPrice(pricetrailingstop));
    		}	
    		double stopprofit =_share.getPercentual_stop_profit();
    		/* EXISTE ALGO SOBREESCRITO */
    		if (this.getJsonStrategyShareParams()!=null && this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0)>0)
    			stopprofit =this.getJsonStrategyShareParams().getDouble(ConfigKeys._FIELD_STOP_PROFIT,0);    	
    		
    		if (stopprofit>0)
    			BuyPositionSystem.setPercentualstopprofit_out(stopprofit);
    			
    		
			
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
	
	

	
	/* 
	backtestingdDate --> backtestingdDate, SI ES BACKTESTING  O NO 
	*/
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	

		boolean verified = Boolean.FALSE;
		boolean existsPosition = Boolean.FALSE;
		
		try
	    {
			
		if (_strategyImpl.getStrategyparamsoverride()==null)
			return Boolean.FALSE;
			
	    this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					
		String HoraActual = "";
		Calendar calFechaActualWithDeadLine;
		Calendar calFechaFinMercado;
		/* TIMEZONE AJUSTADO */
		//Date _FromNow =  !isSimulation_mode() ?    Utilities.getDate(_IBUser) : backtestingdDate;
		Date _FromNow =  !isSimulation_mode() ?    new Date() : backtestingdDate;
		Calendar _calendarFromNow = Calendar.getInstance();
		_calendarFromNow.setTime(_FromNow);
		HoraActual = Utilities.getWebFormattedTime(_calendarFromNow.getTime());
		
		Market marketRealOpenCloseTimes = Utilities.getOpenCloseMarket(_share, backtestingdDate, isSimulation_mode());
		if (Validator.isNull(marketRealOpenCloseTimes)) return false;
		if (!isSimulation_mode())
		{
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual); 
			calFechaFinMercado = Utilities.getNewCalendarWithHour(marketRealOpenCloseTimes.getEnd_hour()); 
		}
		else	
		{
				
			calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(backtestingdDate, HoraActual);
			calFechaFinMercado = Utilities.getNewCalendarWithHour(backtestingdDate, marketRealOpenCloseTimes.getEnd_hour()); 			
		}	
//		StrategyShare _strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),_strategyImpl.getStrategyId());
						
		_num_macdT 		 				= this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,0);
		operationfilter					= this.getJsonStrategyShareParams().getString(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,"ALL").trim();
		_num_shortAvgMACD_P   			= this.getJsonStrategyShareParams().getLong(_EXPANDO_MACD_PERIODOS_SHORT_EXP_MOBILE,0);
		_num_longAvgMACD_P    			= this.getJsonStrategyShareParams().getLong(_EXPANDO_MACD_PERIODOS_LONG_EXP_MOBILE,0); 
		_num_signalLineMACD_P 			= this.getJsonStrategyShareParams().getLong(_EXPANDO_MACD_PERIODOS_SIGNALLINE_EXP_MOBILE,0);
		
		if (_num_macdT==0  || _num_shortAvgMACD_P==0 || _num_longAvgMACD_P==0 || _num_signalLineMACD_P==0)
			return Boolean.FALSE;
		
		/* SOLO PODEMOS ENTRAR EN EL PERIODO MARCADO DE CADA MINUTO, PARA LO CUAL OBTENEMOS EL RESTO */	
		
		/* TIMEZONE AJUSTADO */
	
		_calendarFromNow.set(Calendar.SECOND, 0);
		_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
		long _ModMinuteToEntry = _calendarFromNow.get(Calendar.MINUTE) % _num_macdT;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			return Boolean.FALSE;
		
		
		calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET));
		
		existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),
				Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId()), Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);

		if (!existsPosition &&  !calFechaActualWithDeadLine.after(calFechaFinMercado))		
		{	
			// supuestamente estamos leyendo...verificamos si con respecto al mercado ya tenemos los max y min
			// comparamos que la hora de lectura final haya sobrepasado el actual 
			// HHMM
			// HORA DE FIN DE CALCULO DE MAX Y MINIMOS.
			String StartHourTrading = "";
			if (!isSimulation_mode())
				 StartHourTrading =  Utilities.getActualHourFormatPlusMinutes(_calendarFromNow, marketRealOpenCloseTimes.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
			else				
				 StartHourTrading = Utilities.getActualHourFormatPlusMinutes(marketRealOpenCloseTimes.getStart_hour(), this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET));
			// COMPROBAMOS ALGUN TIPO DE ERROR 
			if (StartHourTrading.contains("-1"))
			{
				_log.info("[ Errores formateando las horas de StarHourTrading de la accion. Hora[" + _market.getStart_hour()  + "], Offset1[" + this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET)+ "]");
				return Boolean.FALSE;
			}
				
			
			if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa
			{
				IBTraderPricesPeaksValleyIndicator pricePeaksValleyIndicator =  BaseIndicatorUtil.getPricesPeaksValleyIndicator(_calendarFromNow,  _num_macdT, _share,
						marketRealOpenCloseTimes,isSimulation_mode()) ; // DATOS DE INTRADIA 
				
				boolean _BuySuccess = false;
				boolean _SellSuccess = false;
				
				long barValleyFoundedFirstIndex = 0 ;
				long barValleyFoundedLastIndex = 0;
				
				long barPeakFoundedFirstIndex = 0 ;
				long barPeakFoundedLastIndex = 0;
				
				boolean priceIsMinimumDecreasing  = pricePeaksValleyIndicator.IsMinimumDecreasing();
				boolean priceIsMaximumIncreasing  = pricePeaksValleyIndicator.IsMaximumIncreasing();

				/* SI HAY MINIMOS DECRECIENTES O MAXIMOS CRECIENTES EN EL PRECIO, BUSCAMOS SI HAY MAXIMOS Y MINIMOS EN EL ESTOCASTICO */
				
				
				/* Calendar calBarBefore = Calendar.getInstance();
				calBarBefore.setTime( _calendarFromNow.getTime());
				calBarBefore.add(Calendar.MINUTE,  (int) - _num_macdT);
				*/
				
			 	if (priceIsMinimumDecreasing) //
				{
					barValleyFoundedFirstIndex = pricePeaksValleyIndicator.getFoundedIndFirstValley();
					barValleyFoundedLastIndex = pricePeaksValleyIndicator.getFoundedIndLastValley();

					
				}
				if (priceIsMaximumIncreasing) // ES EL INDICE CON LA BARRA ENCONTRADA, LA BARRA 0 ES LA BARRA ANTERIOR A LA ACTUAL, YA QUE QUITAMOS LA
				{
 					barPeakFoundedFirstIndex = pricePeaksValleyIndicator.getFoundedIndFirstPeak();
 					barPeakFoundedLastIndex = pricePeaksValleyIndicator.getFoundedIndLastPeak();
 					
				}
				

				if (priceIsMaximumIncreasing || priceIsMinimumDecreasing ) //
				{
				
					/* BUSCAMOS EL PRIMER VALLE * PICO 
					 * IGNORAMOS LA PRIMERA BARRA QUE YA LA USE PARA CALCULAR EL CURRENT MACD
					 * */
					
					//double   currentMACD  =  BaseIndicatorUtil.getMACD(_calendarFromNow.getTime(),  _num_macdT , _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_shortAvgMACD_P,_num_longAvgMACD_P ,isSimulation_mode(),_market);

					// INDEX 2 Y 7 DE PRUEBA, MAXIMOS CRECIENTES Y MAXIMOS DECRECIENTES EN EL INDICADOR
					// DUDA  SI BUSCAR EL MAYOR MAXIMO EN TODA LA SERIE O EL PRIMERO 
					IBTraderPeaksValleyIndicator MACDPeaksValleyIndicator =  BaseIndicatorUtil.getMACDPeaksValleyIndicator(_calendarFromNow, 
									_num_macdT,_num_shortAvgMACD_P,_num_longAvgMACD_P,_share,marketRealOpenCloseTimes,isSimulation_mode());
				
					
					/* HACEMOS COINCIDIR PICOS Y VALLES ENTRE PRECIO E INDICADOR , MAXIMOS CON MAXIMOS Y MINIMOS CON MINIMOS
					 * 
					 * 
					 * 
					 *  FALTAN DESARROLLAR LAS DIVERGENCIAS OCULTAS!!!  */
					
					boolean bMACDMinimumIncreasing = MACDPeaksValleyIndicator.IsMinimumIncreasing() 
								&& MACDPeaksValleyIndicator.getFoundedIndFirstValley()==barValleyFoundedFirstIndex
									&& MACDPeaksValleyIndicator.getFoundedIndLastValley()==barValleyFoundedLastIndex;
					
					
					boolean bMACDMaximumDecreasing = MACDPeaksValleyIndicator.IsMaximumDecreasing()
														&& MACDPeaksValleyIndicator.getFoundedIndFirstPeak()==barPeakFoundedFirstIndex 
															&& MACDPeaksValleyIndicator.getFoundedIndLastPeak()==barPeakFoundedLastIndex; 
					
					
					if (bMACDMinimumIncreasing)
					{
						_log.debug("Date:" + _calendarFromNow.getTime()  + ",for:" + _share.getSymbol() + ",priceIsMinimumDecreasing:"  + priceIsMinimumDecreasing + ",bMACDMinimumIncreasing:" + bMACDMinimumIncreasing);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndFirstValley():" + MACDPeaksValleyIndicator.getFoundedIndFirstValley()  + ",barValleyFoundedFirstIndex:" + barValleyFoundedFirstIndex);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndLastValley():" + MACDPeaksValleyIndicator.getFoundedIndLastValley()  + ",barValleyFoundedLastIndex:" + barValleyFoundedLastIndex);
					}
					if (bMACDMaximumDecreasing)
					{
						_log.debug("Date:" + _calendarFromNow.getTime()  + ",for:" + _share.getSymbol() + ",priceIsMaximumIncreasing:"  + priceIsMaximumIncreasing + ",bMACDMaximumDecreasing:" + bMACDMinimumIncreasing);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndFirstPeak():" + MACDPeaksValleyIndicator.getFoundedIndFirstPeak()  + ",barPeakFoundedFirstIndex:" + barPeakFoundedFirstIndex);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndLastPeak():" + MACDPeaksValleyIndicator.getFoundedIndLastPeak()  + ",barPeakFoundedLastIndex:" + barPeakFoundedLastIndex);
					}
					
					/* DIVERGENCIA ??? */
					_BuySuccess = priceIsMinimumDecreasing && bMACDMinimumIncreasing;
					_SellSuccess = priceIsMaximumIncreasing && bMACDMaximumDecreasing;
					
					_BuySuccess = _BuySuccess &&  
							(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.BUY.toString())); 

					_SellSuccess = _SellSuccess  &&  
							(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.SELL.toString()));
					
					
					
					Double lastRealtime = null;	
					
					if (!isSimulation_mode())
					{
						Realtime oShareLastRTime =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
						lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
						
					}					
					else
					{
						HistoricalRealtime oShareLastRTime = HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
						lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
						
					}
					
					/* COGEMOS LOS MAXIMOS Y MINIMOS RELATIVOS ENTRE LA DIVERGENCIA PARA CONFIRMAR LA SEÑAL 
					 *  O BIEN  BUSCAMOS UNA VELA DOJI CON SUFICIENTE MECHA ARRIBA O ABAJO */					 					
					_BuySuccess = _BuySuccess && (lastRealtime > pricePeaksValleyIndicator.getRelativeMaxOfValley() || CandleUtil.wickDown(_FromNow, _share, isSimulation_mode(), _num_macdT));
					_SellSuccess = _SellSuccess && (lastRealtime < pricePeaksValleyIndicator.getRelativeMinOfPeak() || CandleUtil.wickUp(_FromNow, _share, isSimulation_mode(), _num_macdT));
										
					/*  SACAMOS DEPURACION EN DURANTE LOS TRES PRIMEROS SEGUNDOS EN LOS CORTES DE BARRAS */
				
					
					
					
					/* fecha hora venicmiento  NO proxima */ 
					boolean  IsFutureTradeable =  Utilities.IsFutureTradeable(_share,isSimulation_mode());
					
					
					if (IsFutureTradeable && (_BuySuccess || _SellSuccess))
					{
					
						
					    this.setValueIn(lastRealtime.doubleValue());											
						this.setVerified(Boolean.TRUE);												
						verified = true;							
						this.bBuyOperation = _BuySuccess;									
						this.bSellOperation = _SellSuccess;
						
						
						_log.debug("lastRealtime for :" + _share.getSymbol() + " " +  lastRealtime);
						_log.debug("MACDD for :" + _share.getSymbol() + ",date:" + _calendarFromNow.getTime());
						_log.debug("priceIsMinimumDecreasing:" + priceIsMinimumDecreasing);					
						_log.debug("priceIsMaximumIncreasing:" + priceIsMaximumIncreasing);
						_log.debug("bMACDMinimumIncreasing :" + bMACDMinimumIncreasing );
						_log.debug("bMACDMaximumDecreasing:" + bMACDMaximumDecreasing);
						_log.debug("barPeakFoundedFirstIndex:" + barPeakFoundedFirstIndex);
						_log.debug("barPeakFoundedLastIndex:" + barPeakFoundedLastIndex);
						_log.debug("barValleyFoundedFirstIndex:" + barValleyFoundedFirstIndex);
						_log.debug("barValleyFoundedLastIndex:" + barValleyFoundedLastIndex);
						_log.debug("priceIsMaximumIncreasing:" + priceIsMaximumIncreasing);						
						_log.debug("barValleyFounded:" + barValleyFoundedFirstIndex);						
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndFirstValley():" + MACDPeaksValleyIndicator.getFoundedIndFirstValley()  + ",barValleyFoundedFirstIndex:" + barValleyFoundedFirstIndex);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndLastValley():" + MACDPeaksValleyIndicator.getFoundedIndLastValley()  + ",barValleyFoundedLastIndex:" + barValleyFoundedLastIndex);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndFirstPeak():" + MACDPeaksValleyIndicator.getFoundedIndFirstPeak()  + ",barPeakFoundedFirstIndex:" + barPeakFoundedFirstIndex);
						_log.debug("MACDPeaksValleyIndicator.getFoundedIndLastPeak():" + MACDPeaksValleyIndicator.getFoundedIndLastPeak()  + ",barPeakFoundedLastIndex:" + barPeakFoundedLastIndex);
						
						
						_tradeDescription = JSONFactoryUtil.createJSONObject();
						_tradeDescription.put("priceIsMinimumDecreasing:", priceIsMinimumDecreasing);
						_tradeDescription.put("priceIsMaximumIncreasing:", priceIsMaximumIncreasing);
						_tradeDescription.put("bMACDMinimumIncreasing :" , bMACDMinimumIncreasing );
						_tradeDescription.put("bMACDMaximumDecreasing:" , bMACDMaximumDecreasing);						
						_tradeDescription.put("barPeakFoundedFirstIndex:" , barPeakFoundedFirstIndex);						
						_tradeDescription.put("barPeakFoundedLastIndex:" , barPeakFoundedLastIndex);
						_tradeDescription.put("barValleyFoundedFirstIndex:" , barValleyFoundedFirstIndex);
						_tradeDescription.put("barValleyFoundedLastIndex:" , barValleyFoundedLastIndex);
						_tradeDescription.put("MACDPeaksValleyIndicator.getFoundedIndFirstValley():" , MACDPeaksValleyIndicator.getFoundedIndFirstValley());
						_tradeDescription.put("MACDPeaksValleyIndicator.getFoundedIndLastValley():" , MACDPeaksValleyIndicator.getFoundedIndLastValley());
						_tradeDescription.put("MACDPeaksValleyIndicator.getFoundedIndFirstPeak():" , MACDPeaksValleyIndicator.getFoundedIndFirstPeak());
						_tradeDescription.put("MACDPeaksValleyIndicator.getFoundedIndLastPeak():" , MACDPeaksValleyIndicator.getFoundedIndLastPeak());
						_tradeDescription.put("lastRealtime:" , lastRealtime);						
						_tradeDescription.put("_BuySuccess", _BuySuccess);		
						_tradeDescription.put("_SellSuccess", _SellSuccess);
						_tradeDescription.put("price RelativeMaxOfValley()", pricePeaksValleyIndicator.getRelativeMaxOfValley());
						_tradeDescription.put("price getRelativeMinOfPeak()", pricePeaksValleyIndicator.getRelativeMinOfPeak());
						
						
						
					}
					
					
				 } // if  (Validator.isNotNull(oShareLastRTime))			   	
			} // if (_avgMobileExponential!=null)			
		}// NO EXISTE POSICION 
	    }
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
		
	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,  String.valueOf(ExpandoColumnConstants.INTEGER));		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,  String.valueOf(ExpandoColumnConstants.STRING_ARRAY));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MACD_PERIODOS_SHORT_EXP_MOBILE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MACD_PERIODOS_LONG_EXP_MOBILE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MACD_PERIODOS_SIGNALLINE_EXP_MOBILE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	
	
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyPriceDivergences.class.getName());
		long i = 0;
		for (Map.Entry<String, String> parameter : Parameters.entrySet()) {
		
			String _paramName = parameter.getKey();
			String _paramValue = parameter.getValue();
			
			/* EXISTE, SI NO , LO CREAMOS */
			ExpandoColumn ExistsExpando = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), _paramName);
			
			if (ExistsExpando==null)
			{
				ExpandoColumn paramColumn = ExpandoColumnLocalServiceUtil.createExpandoColumn(CounterLocalServiceUtil.increment(ExpandoColumn.class.getName()));
				paramColumn.setName(_paramName);
				paramColumn.setType(Integer.parseInt(_paramValue));	
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
		
		/* PUEDEN VENIR LISTA DE VALORES EN EL NOMBRE DEL EXPANDO []  */		
		 boolean bParamList = parameter.getKey().contains("[") && parameter.getKey().contains("]");
		if (!bParamList && !Utilities.isNumber(_paramValue))
		{
			bOK=Boolean.FALSE;
			this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams");
			break;
				
		}	
		
	}
	return bOK;	
		
	}
}	