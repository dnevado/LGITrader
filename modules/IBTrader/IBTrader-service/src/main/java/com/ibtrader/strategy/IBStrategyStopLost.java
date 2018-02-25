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

public class IBStrategyStopLost extends StrategyImpl {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyStopLost.class);
	private Position currentPosition = null;
	@Override
	public long execute(Share _share, Market _market) {
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
		
		Order BuyPositionTWS = new Order();
		BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));			
		BuyPositionTWS.totalQuantity(currentPosition.getShare_number());
		BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    
		BuyPositionTWS.lmtPrice(Utilities.RoundPrice(this.getValueOut()));
		BuyPositionTWS.auxPrice(Utilities.RoundPrice(this.getValueOut()));				
		BuyPositionTWS.action(_OperationTYPE);			
		
		_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
		this.setTargetOrder(BuyPositionTWS);			
				
		currentPosition.setPrice_out(this.getValueOut());
		currentPosition.setState_out(PositionStates.statusTWSCallBack.PendingSubmit.toString());
		/* si metemos el date sell en las parciales, no entran las siguientes */
		/* acumulo las acciones vendidas y a vender en la operativa */
		currentPosition.setDate_out(new Date());
		currentPosition.setDescription("Venta. Sell Stop Lost.");
		currentPosition.setStrategy_out(ConfigKeys.STRATEGY_SELL_STOP_LOST.toString());		 		
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
	
	public IBStrategyStopLost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl) {
	
	boolean verified = false;
	boolean existsPositionToExit = false;
	try
    {		
	existsPositionToExit = PositionLocalServiceUtil.ExistsPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId());
	if (existsPositionToExit)		
	{	
		  			
		Realtime oShareLastRTime = (Realtime)  RealtimeLocalServiceUtil.findLastRealTime(_share.getShareId(), _share.getCompanyId(), _share.getGroupId());
		currentPosition = PositionLocalServiceUtil.findPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId());
		
		if (oShareLastRTime!=null && currentPosition!=null && currentPosition.getPercentualstoplost_out()>0)
		{	
			boolean bExistslost;
			double percentual_stop_lost = currentPosition.getPercentualstoplost_out();
			double entry_price  = currentPosition.getPrice_real_in();
			double current_price = oShareLastRTime.getValue();

			if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString()))  // operacion de compra normal..??
				bExistslost = (current_price < (entry_price - (entry_price * percentual_stop_lost)));
			else
				bExistslost = (current_price > (entry_price + (entry_price * percentual_stop_lost)));
			if (bExistslost)
			{
				
			    this.setValueIn(current_price);
			    this.setValueLimitOut(current_price);	
				this.setVerified(Boolean.TRUE);												
				verified = true;
			}			
		}  //if (HoraActual.compareTo(HoraFinLecturaMaxMin)>0)   
	}
    }// NO EXISTE POSICION    
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
		return true;	
		
	}

	
	
}