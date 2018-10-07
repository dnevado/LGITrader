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
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
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
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyCancelPosition extends StrategyImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyCancelPosition.class);
	Position cancelPosition = null;
	
	
	public IBStrategyCancelPosition() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market, StrategyShare _strategyImpl, Date backtestingdDate) {
			
	boolean verified = false;
	List<Position> lCancelled = null;
	try
    {
		String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());

		/* cancelada y abierta */
		lCancelled = PositionLocalServiceUtil.findByCancelShareCompanyGroup(_share.getCompanyId(), _share.getGroupId(),1, _share.getShareId(),position_mode);
		if (lCancelled!=null && !lCancelled.isEmpty())
		{
			for (Position position : lCancelled)
			{
				if (position.IsCancelable())
				{
					
					cancelPosition  = position;
					verified = Boolean.TRUE;
					break;
					
				}
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
	public long execute(Share _share, Market _market, Date backtestingdDate){
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
	   
	  /*  Possible Order States

	   ApiPending - indicates order has not yet been sent to IB server, for instance if there is a delay in receiving the security definition. Uncommonly received.
	   PendingSubmit - indicates the order was sent from TWS, but confirmation has not been received that it has been received by the destination. Most commonly because exchange is closed.
	   PendingCancel - indicates that a request has been sent to cancel an order but confirmation has not been received of its cancellation.
	   PreSubmitted - indicates that a simulated order type has been accepted by the IB system and that this order has yet to be elected. The order is held in the IB system until the election criteria are met. At that time the order is transmitted to the order destination as specified.
	   Submitted - indicates that your order has been accepted at the order destination and is working.
	   ApiCancelled - after an order has been submitted and before it has been acknowledged, an API client client can request its cancellation, producing this state.
	   Cancelled - indicates that the balance of your order has been confirmed cancelled by the IB system. This could occur unexpectedly when IB or the destination has rejected your order.
	   Filled - indicates that the order has been completely filled.
	   Inactive - indicates an order is not working, possible reasons include:
	   it is invalid or triggered an error. A corresponding error code is expected to the error() function.
	   the order is to short shares but the order is being held while shares are being located.
	   an order is placed manually in TWS while the exchange is closed.
	   an order is blocked by TWS due to a precautionary setting and appears there in an untransmitted state
	  */
	   if (cancelPosition.getPendingcancelled()==1)
		{
			
			// las PendingSubmit de entrada  hay que eliminarlas porque no estan negociando. P.e. Prohibido operar a corto, bloqueo de consola
			if (cancelPosition.getState_in().toString().equals(PositionStates.statusTWSCallBack.PendingSubmit.toString()))
			{
				
				/* 1. BORRAMOS LAS ORDENES ASOCIADAS */ 	
				/* 2. SACAMOS LA OPERACION ENVIADA A CANCELAR  */
				/* 3. BORRAMOS LAS POSICIONES   */
				returnValue =  cancelPosition.getPositionId_tws_in();
				
				IBOrder orderin  =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(cancelPosition.getPositionId_tws_in(),cancelPosition.getClientId_in(),cancelPosition.getCompanyId(), cancelPosition.getGroupId());			
//				IBOrder orderout =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(cancelPosition.getPositionId_tws_out(),cancelPosition.getClientId_in(),cancelPosition.getCompanyId(), cancelPosition.getGroupId()); 
				if (orderin!=null)
					IBOrderLocalServiceUtil.deleteIBOrder(orderin);
	/* 			if (orderout!=null)
					IBOrderLocalServiceUtil.deleteIBOrder(orderout); */
				PositionLocalServiceUtil.deletePosition(cancelPosition.getPositionId());
				//PositionLocalServiceUtil.deletePosition(cancelPosition.getPositionId());
				

			}
			else  
			{									
				/* 1. CANCELAMOS LA ENTRADA 
				 * 2. CANCELAMOS LA SALIDA 
				 */
				//getDate_real_out()==null &&   this.getState().equals(PositionStates.status.BUY_OK.toString()); 
				if (cancelPosition.IsPendingOut() && cancelPosition.getPositionId_tws_out()>=0)
				{
					returnValue =  cancelPosition.getPositionId_tws_out();
					IBOrder orderout =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(cancelPosition.getPositionId_tws_out(),cancelPosition.getClientId_out(),cancelPosition.getCompanyId(), cancelPosition.getGroupId()); 
					if (orderout!=null)
						IBOrderLocalServiceUtil.deleteIBOrder(orderout); 
					
					cancelPosition.setState_out(null);
					cancelPosition.setPositionId_tws_out(0);
					cancelPosition.setPrice_out(0);
					cancelPosition.setPrice_real_out(0);
					cancelPosition.setClientId_out(0);
					cancelPosition.setStrategy_out(null);					
					cancelPosition.setPendingcancelled(0);
					
					PositionLocalServiceUtil.updatePosition(cancelPosition);

				}		
				// NO DEBERIA PASAR 
				if (cancelPosition.IsPendingIn()  && cancelPosition.getPositionId_tws_in()>=0)
				{
					returnValue =  cancelPosition.getPositionId_tws_in();
					IBOrder orderin  =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(cancelPosition.getPositionId_tws_in(),cancelPosition.getClientId_in(),cancelPosition.getCompanyId(), cancelPosition.getGroupId());
					if (orderin!=null)
						IBOrderLocalServiceUtil.deleteIBOrder(orderin);
					PositionLocalServiceUtil.deletePosition(cancelPosition);

				}
				if (returnValue!=-1)
				{						
					returnValue =  cancelPosition.getPositionId();
				}
			}
		}			
	   
		/* Posicion en MYSQL de CONTROL */
		
		
		/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
	
		

		
    }
	catch (Exception er)
        {
			_log.info(er.getMessage());
			er.printStackTrace();
		
        }
	return returnValue;
	}	
}
