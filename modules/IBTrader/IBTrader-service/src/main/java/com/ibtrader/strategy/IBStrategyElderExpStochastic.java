

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
import com.ibtrader.util.AroonIndicatorUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.DirectionalMovementADXRUtil;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;


/* https://compraraccionesdebolsa.com/los-sistemas-de-trading/triple-pantalla-Elder/ 
 * http://www.megabolsa.com/2015/08/01/tecnica-de-trading-de-triple-pantalla/*/

public class IBStrategyElderExpStochastic extends StrategyImpl {

	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyElderExpStochastic.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, String> Parameters = new HashMap<String,String>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER = "First Screen Exponencial Mobile Average Periods Number {26}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE = "Mobile Average Candle Size (Minutes) {5}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_STOCHASTUC_PERIDOS  = "K Stochastic Periods  {14}";  // offset hasta desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET = "Mobile Average Trade Until x Minutes From CloseMarket {0}"; // operar hasta minutos antes de cierre mercado
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET = "OffSet From Open Market (Minutes) To Start Trading {30}";  // offset desde inicio de mercado en minutos
	private static String _EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE = "Operation Type [ALL, BUY, SELL]";  // offset desde inicio de mercado en minutos
	
	private static String _EXPANDO_STOCHASTUC_OVERBOUGHT_RATE  = "Overbought Stochastic Rate {80}";  // offset hasta desde inicio de mercado en minutos		
	private static String _EXPANDO_STOCHASTUC_OVERSOLD_RATE  = "Oversold Stochastic Rate {20}";  // offset hasta desde inicio de mercado en minutos		
	private static String _EXPANDO_X_TICKS_ENTRYPRICE  = "X Ticks From MinMax Last Bar To Buy Sell {1}";  // offset hasta desde inicio de mercado en minutos		

	
	long  _num_stochasticP = 8;   // Periodos
	long  _num_macdP = 8;   // Periodos
	long  _num_macdT = 5;   // Tiempo de barras
	long  _num_stochastic_rate_overbought = 0;
	long  _num_stochastic_rate_oversold = 50;
	
	long  _num_ticks_fromLastbar  = 0;   // Tiempo de barras
	
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
	
	
	public IBStrategyElderExpStochastic() {
		
		super();
		// TODO Auto-generated constructor stub
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
		User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
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
				
		_num_macdP      				= this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER,0);
		_num_macdT 		 				= this.getJsonStrategyShareParams().getLong(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,0);
		_num_stochastic_rate_overbought = this.getJsonStrategyShareParams().getLong(_EXPANDO_STOCHASTUC_OVERBOUGHT_RATE,0);
		_num_stochastic_rate_oversold   = this.getJsonStrategyShareParams().getLong(_EXPANDO_STOCHASTUC_OVERSOLD_RATE,0);
		_num_stochasticP      			= this.getJsonStrategyShareParams().getLong(_EXPANDO_STOCHASTUC_PERIDOS,0);  		
		_num_ticks_fromLastbar 			= this.getJsonStrategyShareParams().getLong(_EXPANDO_X_TICKS_ENTRYPRICE,0);		
		operationfilter					= this.getJsonStrategyShareParams().getString(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,"ALL").trim();
		
		if (_num_stochasticP==0 || _num_macdP==0 || _num_macdT==0 || _num_stochastic_rate_overbought==0 || _num_stochastic_rate_oversold==0 || _num_ticks_fromLastbar==0)
			return Boolean.FALSE;
		
		/* SOLO PODEMOS ENTRAR EN EL PERIODO MARCADO DE CADA MINUTO, PARA LO CUAL OBTENEMOS EL RESTO */	
		
		/* TIMEZONE AJUSTADO */
	
	
		long currentSeconds = _calendarFromNow.get(Calendar.SECOND);
		
		_calendarFromNow.set(Calendar.SECOND, 0);
		_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
		long _ModMinuteToEntry = _calendarFromNow.get(Calendar.MINUTE) % _num_macdT;
		if (_ModMinuteToEntry!=0)  // NO ESTOY EN EL MINUTO 5,10,15,20..etc (para las barras de 5)
			return Boolean.FALSE;
		
		
		calFechaActualWithDeadLine.add(Calendar.MINUTE, this.getJsonStrategyShareParams().getInt(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET));
		
		existsPosition = PositionLocalServiceUtil.ExistsOpenPosition (_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),
				Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId()), Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
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
			
				// ya no obtenemos el maximo y minimo, sino el correspondiente al tramo que me han dicho
				
				Double lastRealtime = null;	
				
				double  previousmax_value;
				double  previousmin_value;
				
				if (!isSimulation_mode())
				{
					Realtime oShareLastRTime =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
					lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
					/* BARRA ANTERIOR */
					Realtime oRTimeWidthRange = BaseIndicatorUtil.getMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 1,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
					previousmax_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
					previousmin_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;
				}					
				else
				{
					HistoricalRealtime oShareLastRTime = HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_FromNow);
					lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
					/* BARRA ANTERIOR */
					HistoricalRealtime oRTimeWidthRange = BaseIndicatorUtil.getHistoricalMinMaxBarFromShare(_calendarFromNow,  _num_macdT, 1,_share.getShareId(), _share.getCompanyId(), _share.getGroupId()) ;
					previousmax_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMax_value()>0 ? oRTimeWidthRange.getMax_value() : 0; 
					previousmin_value  = oRTimeWidthRange!=null && oRTimeWidthRange.getMin_value()>0 ? oRTimeWidthRange.getMin_value() : 0;

				}
				
				if  (Validator.isNotNull(lastRealtime))
				{
				//_ActualDateBar, TimeBars, shareId, companyId, groupId, PeriodN)
					Double _avgMobileExponential = BaseIndicatorUtil.getExponentialAvgMobile(_calendarFromNow.getTime(), lastRealtime.doubleValue(), _num_macdT, _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_macdP , isSimulation_mode(), _market);
					
					
					if (_avgMobileExponential!=null)
					{
					
					
						
					double stochasticD = 0d; //new DirectionalMovementADXRUtil(_calendarFromNow.getTime(), _num_macdT, _num_macdP,_share.getShareId(), _share.getCompanyId(), _share.getGroupId());					
					
					
					stochasticD   = BaseIndicatorUtil.getDStochastic(_calendarFromNow.getTime(),  _num_macdT , _share.getShareId(), _share.getCompanyId(), _share.getGroupId(), _num_stochasticP ,isSimulation_mode(),_market);
					
										
					boolean _BuySuccess = false;
					boolean _SellSuccess = false;
				
					_num_stochastic_rate_overbought = this.getJsonStrategyShareParams().getLong(_EXPANDO_STOCHASTUC_OVERBOUGHT_RATE,0);
					_num_stochastic_rate_oversold   = this.getJsonStrategyShareParams().getLong(_EXPANDO_STOCHASTUC_OVERSOLD_RATE,0);
					
					/* https://www.tecnicasdetrading.com/2010/06/el-oscilador-estocastico.html */
					
					boolean bBuyStochasticSignal =  Validator.isNotNull(stochasticD) && stochasticD>0 && stochasticD <=_num_stochastic_rate_oversold;
				    boolean bSellStochasticSignal = Validator.isNotNull(stochasticD) && stochasticD>0 && stochasticD >=_num_stochastic_rate_overbought;
				    
				    /* Si la primera pantalla tiene tendencia alcista y en la segunda pantalla los osciladores indican sobre venta, 
				     * abrimos una posición de compra si en la tercera pantalla  el oscilador está en sobre venta y el precio supera el máximo del día o el de la sesión anterior.
				    Si en la primera pantalla se identifica un movimiento de tendencia a la baja y el oscilador en la segunda pantalla se mueve al alza, hay que estar listos para abrir una posición corta una vez que la tercera pantalla de la señal.
				    
				    * Valores menores de 20 en el oscilador indican condiciones de sobre venta que pueden anticipar un rebote y alza del precio
				    *
				    */
				    boolean bBuyEntryBasedLastBarTicks  =  Boolean.FALSE;
				    boolean bSellEntryBasedLastBarTicks  =  Boolean.FALSE;
				    
				    /* LOS TICKS SON NECESARIOS, NO SOLO PARA FUTUROS */
				    /*  Si queremos entrar a largo y se detecta una posible ruptura de tendencia  
				     * colocamos una orden condicionada de compra un tick por encima  del máximo de la barra precedente. 
				     * Si no se cumple y el valor sigue bajando, bajaremos la orden de compra al siguiente máximo de la última barra cerrada.
				     */
				    bBuyEntryBasedLastBarTicks  = previousmax_value > 0 &&  lastRealtime.doubleValue()  >= previousmax_value  + (_share.getTick_futures() * _num_ticks_fromLastbar) ? Boolean.TRUE : Boolean.FALSE; 
				    bSellEntryBasedLastBarTicks = previousmin_value > 0 &&  lastRealtime.doubleValue()  <= previousmin_value  - (_share.getTick_futures() * _num_ticks_fromLastbar) ? Boolean.TRUE : Boolean.FALSE;
				    
				    
					
					_BuySuccess =  lastRealtime.doubleValue() >_avgMobileExponential.doubleValue() && bBuyStochasticSignal && bBuyEntryBasedLastBarTicks ;					
					_SellSuccess = lastRealtime.doubleValue() <_avgMobileExponential.doubleValue() && bSellStochasticSignal && bSellEntryBasedLastBarTicks;
					
					
					_BuySuccess = _BuySuccess &&  
							(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.BUY.toString())); 

					_SellSuccess = _SellSuccess  &&  
							(operationfilter.equals("ALL") || operationfilter.equals(PositionStates.statusTWSFire.SELL.toString()));

					/*  SACAMOS DEPURACION EN DURANTE LOS TRES PRIMEROS SEGUNDOS EN LOS CORTES DE BARRAS */
				
					_log.debug("_avgMobileExponential for :" + _share.getSymbol() + ":" +  (Validator.isNotNull(_avgMobileExponential) ? _avgMobileExponential.doubleValue() : 0) + " " + Utilities.getWebFormattedDate(_calendarFromNow.getTime(), _IBUser));
					_log.debug("stochasticD:" + stochasticD + ",previousmax_value:" +  previousmax_value + "_num_ticks_fromLastbar:" + _num_ticks_fromLastbar + ",lastRealtime>=previousmax_value + Ticks*nticks:" +  bBuyEntryBasedLastBarTicks );
					_log.debug("stochasticD:" + stochasticD + ",previousmin_value:" +  previousmin_value + "_num_ticks_fromLastbar:" + _num_ticks_fromLastbar + ",lastRealtime<=previousmax_value - Ticks*nticks:" +  bSellEntryBasedLastBarTicks );
					_log.debug("lastRealtime.doubleValue() >_avgMobileExponential.doubleValue() && bBuyStochasticSignal && bBuyEntryBasedLastBarTicks:" + _BuySuccess);
					_log.debug("lastRealtime.doubleValue() <_avgMobileExponential.doubleValue() && bSellStochasticSignal && bSellEntryBasedLastBarTick:" + _SellSuccess);

						
				
					
					/* fecha hora venicmiento  NO proxima */ 
					boolean  IsFutureTradeable = Utilities.IsFutureTradeable(_share,isSimulation_mode());					
					
					if (IsFutureTradeable && (_BuySuccess || _SellSuccess))
					{
					
					    this.setValueIn(lastRealtime.doubleValue());											
						this.setVerified(Boolean.TRUE);												
						verified = true;							
						this.bBuyOperation = _BuySuccess;									
						this.bSellOperation = _SellSuccess;
													
						_tradeDescription = JSONFactoryUtil.createJSONObject();
						_tradeDescription.put("lastRealtime.doubleValue()", lastRealtime.doubleValue());
						_tradeDescription.put("_avgMobileExponential", _avgMobileExponential);	
						_tradeDescription.put("_num_macdP", _num_macdP);
						_tradeDescription.put("_num_macdT", _num_macdT);
						_tradeDescription.put("_num_stochastic_rate_overbought" , _num_stochastic_rate_overbought);
						_tradeDescription.put("_num_stochastic_rate_oversold", _num_stochastic_rate_oversold);
						_tradeDescription.put("bBuyStochasticSignal", bBuyStochasticSignal);
						_tradeDescription.put("bSellStochasticSignal", bSellStochasticSignal);		
						_tradeDescription.put("previousmax_value", previousmax_value);
						_tradeDescription.put("previousmin_value", previousmin_value);
						_tradeDescription.put("operationfilter", operationfilter);		
						_tradeDescription.put("stochasticD", stochasticD);
						_tradeDescription.put("_BuySuccess", _BuySuccess);		
						_tradeDescription.put("_SellSuccess", _SellSuccess);						
					
					}
					
					/* ALMACENAMOS LOS VALORES DE AUDITORIA SI ES TIEMPO REAL
					if (!isSimulation_mode())
					{
						jsonStrategyIndicators= JSONFactoryUtil.createJSONObject();
						jsonStrategyIndicators.put("_avgMobileExponential", _avgMobileExponential);
						jsonStrategyIndicators.put("shareId", _share.getShareId());
						jsonStrategyIndicators.put("bartime", auditTimeFormat.format(_calendarFromNow.getTime()));
						jsonStrategyIndicators.put("lastRealtime", lastRealtime.doubleValue());
						jsonStrategyIndicators.put("periods", _num_macdP);
						jsonStrategyIndicators.put("barsize", _num_macdT);								
						jsonStrategyIndicators.put("stochasticD", stochasticD);								
						
						
						try 
						{
							AuditIndicatorsStrategyPK pkAudit = new AuditIndicatorsStrategyPK(_share.getGroupId(), _share.getCompanyId(), auditTimeFormat.format(_calendarFromNow.getTime()), _strategyImpl.getClass().getName(), _share.getShareId());
							AuditIndicatorsStrategy auditStrategy = AuditIndicatorsStrategyLocalServiceUtil.fetchAuditIndicatorsStrategy(pkAudit);
							if (Validator.isNull(auditStrategy))
							{
								auditStrategy = AuditIndicatorsStrategyLocalServiceUtil.createAuditIndicatorsStrategy(pkAudit);
								auditStrategy.setAuditData(Validator.isNotNull(jsonStrategyIndicators) ? jsonStrategyIndicators.toString() : StringPool.BLANK);
								AuditIndicatorsStrategyLocalServiceUtil.addAuditIndicatorsStrategy(auditStrategy);
								
							}
						}
						catch (Exception e)	{}
						
					}	 */
					
					
					
					
					
				 } // if  (Validator.isNotNull(oShareLastRTime))			   	
				} // if (_avgMobileExponential!=null)			
			}// NO EXISTE POSICION 
		} // if (HoraActual.compareTo(StartHourTrading)>0)   // hora actyual ya ha pasado, podemos entrar en la operativa	
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
		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_PERIODS_NUMBER, String.valueOf(ExpandoColumnConstants.INTEGER));
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_CANDLE_SIZE,  String.valueOf(ExpandoColumnConstants.INTEGER));		
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_TO_CLOSEMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OFFSET_FROM_OPENMARKET,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_STOCHASTUC_PERIDOS,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_STOCHASTUC_OVERBOUGHT_RATE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_STOCHASTUC_OVERSOLD_RATE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE	
	Parameters.put(_EXPANDO_MOBILE_AVERAGE_TRADE_OPERATIONS_TYPE,  String.valueOf(ExpandoColumnConstants.STRING_ARRAY));  // ESTE ES EL UNICO DOUBLE
	Parameters.put(_EXPANDO_X_TICKS_ENTRYPRICE,  String.valueOf(ExpandoColumnConstants.INTEGER));  // ESTE ES EL UNICO DOUBLE
	
	ExpandoTable expandoTable;
	try {
		expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyElderExpStochastic.class.getName());
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