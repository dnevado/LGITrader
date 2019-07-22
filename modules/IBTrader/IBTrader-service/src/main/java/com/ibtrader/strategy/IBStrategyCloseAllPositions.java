package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyCloseAllPositions extends StrategyImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyCloseAllPositions.class);
	/* PAIR NAME / DATA TYPE PARAMETERES */	
	private static HashMap<String, Integer> Parameters = new HashMap<String,Integer>();
	private List<ExpandoColumn> ExpandoColumns = new ArrayList<ExpandoColumn>(); 
	
	private static String _EXPANDO_DEADLINE_UNTIL_CLOSEMARKET = "Close All Positions After x Min To Close Market {5}, 0 if not applicable";  // offset desde inicio de mercado en minutos
	private Position currentPosition = null;

	
	JSONObject _tradeDescription;

	
	
	@Override
	public long execute(Share _share, Market _market, Date backtestingdDate) {
		// TODO Auto-generated method stub
	long returnValue=-1;
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
	   /* NECESARIO PRA LANZAR COMPRA DESDE EL CROUTIL */
	   this.setTargetContract(oContrat);
	   
		// MyActualPosition contiene la operacion abierta.		
	    String _OperationTYPE = PositionStates.statusTWSFire.BUY.toString(); 
		if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString())) // operacion de compra normal..??
			_OperationTYPE = PositionStates.statusTWSFire.SELL.toString();
		// colocamos operacion de compra
		if (!isSimulation_mode())
		{ 
			Order BuyPositionTWS = new Order();
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));			
			BuyPositionTWS.totalQuantity(currentPosition.getShare_number());
			BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    					
			BuyPositionTWS.action(_OperationTYPE);			
			_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
			this.setTargetOrder(BuyPositionTWS);
		}
		currentPosition.setPrice_out(Utilities.RoundPrice(this.getValueOut()));
		//currentPosition.setState_out(PositionStates.statusTWSCallBack.PendingSubmit.toString());
		/* si metemos el date sell en las parciales, no entran las siguientes */
		/* acumulo las acciones vendidas y a vender en la operativa */
		currentPosition.setDate_out(!isSimulation_mode() ? new Date() : backtestingdDate);
		currentPosition.setDescription(currentPosition.getDescription() + StringPool.RETURN_NEW_LINE + this._tradeDescription.toString());
		currentPosition.setStrategy_out(this.getClass().getName());		 		
		
		/* MODO FAKE CUENTA DEMO */
		currentPosition = Utilities.fillStatesOrder(currentPosition);
		/* END MODO FAKE CUENTA DEMO */
		
		
		PositionLocalServiceUtil.updatePosition(currentPosition);
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
	
	public IBStrategyCloseAllPositions() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	
	boolean verified = false;
	try
    {
		
	String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());
	
	User _IBUser = UserLocalServiceUtil.getUser(_share.getUserCreatedId());
	
	String HoraActual = "";	
	Calendar calFechaActualWithDeadLine;
	Calendar calFechaFinMercado;

	if (!isSimulation_mode())
	{
		HoraActual = Utilities.getHourNowFormat(_IBUser);		
		calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(HoraActual);
		calFechaFinMercado = Utilities.getNewCalendarWithHour(_market.getEnd_hour());


	}
	else	
	{
		HoraActual = Utilities.getHourNowFormat(_IBUser,backtestingdDate);
		calFechaActualWithDeadLine = Utilities.getNewCalendarWithHour(backtestingdDate, HoraActual);
		calFechaFinMercado = Utilities.getNewCalendarWithHour(backtestingdDate, _market.getEnd_hour());			


	}		
	this.setJsonStrategyShareParams(JSONFactoryUtil.createJSONObject(_strategyImpl.getStrategyparamsoverride()));					

	/* MERCADO ABIERTO */	
	if (calFechaActualWithDeadLine.after(calFechaFinMercado))
		return false;
	
	/* CONTROLAMOS QUE SE PUEDAN METER -1 E IGNORAR LA HORA DE CIERRE, ASI FUNCIONA PARA LOS FUTUROS */
	int deadline_until_closemarket = this.getJsonStrategyShareParams().getInt(_EXPANDO_DEADLINE_UNTIL_CLOSEMARKET,0);
	boolean nextToClose =  Boolean.FALSE;
	if (deadline_until_closemarket>0)
	{
		calFechaActualWithDeadLine.add(Calendar.MINUTE, deadline_until_closemarket);
		nextToClose = calFechaActualWithDeadLine.after(calFechaFinMercado);
	}
	currentPosition = PositionLocalServiceUtil.findPositionToExit(_share.getGroupId(), _share.getCompanyId(), _share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
	
	/* SI VA A EXPIRAR EN UN DIA, CERRAMOS POSICION Y NO ENTRAMOS HASTA EL CONTRATO NUEVO 
	 * TODO EN UTC */
	boolean  nextToExpiration = !Utilities.IsFutureTradeable(_share);

	if ((nextToClose || nextToExpiration) && currentPosition!=null) 		  // ya esta en el limite 
	{		    		
			/* TIMEZONE AJUSTADO */
			Date _FromNow =  !isSimulation_mode() ?   Utilities.getDate(_IBUser) : backtestingdDate;
			Calendar _calendarFromNow = Calendar.getInstance();
			_calendarFromNow.setTime(_FromNow);		
			_calendarFromNow.set(Calendar.SECOND, 0);
			_calendarFromNow.set(Calendar.MILLISECOND, 0);
		
			
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
			
			if (Validator.isNotNull(lastRealtime))
			{
				
				_tradeDescription = JSONFactoryUtil.createJSONObject();
				_tradeDescription.put("nextToClose", nextToClose);
				_tradeDescription.put("nextToExpiration", nextToExpiration);
				
				double current_price = lastRealtime.doubleValue();
				this.setVerified(Boolean.TRUE);		
				this.setValueOut(current_price);												
				verified = true;
			}
    }
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
		
		Parameters.put(_EXPANDO_DEADLINE_UNTIL_CLOSEMARKET, Integer.valueOf(ExpandoColumnConstants.INTEGER));		
		ExpandoTable expandoTable;
		try {
			expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, IBStrategyCloseAllPositions.class.getName());
			for (Map.Entry<String, Integer> parameter : Parameters.entrySet()) {
			
				String _paramName = parameter.getKey();
				Integer _paramValue = parameter.getValue();
				
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
			/* PERMITIMOS 0  PARA IGNORAR */
			if (!Validator.isNumber(_paramValue)  && Integer.parseInt(_paramValue)>=0)
			{
				bOK=Boolean.FALSE;
				this.setValidateParamsKeysError("strategyshare.strategyminmax.errorparams");
				break;
					
			}	
			
		}
		return bOK;	
		
	}

	
	
}
