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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyClosePosition extends StrategyImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyClosePosition.class);
	Position closePosition = null;
	
	@Override
	public long execute(Share _share, Market _market,  Date backtestingdDate)
	{
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
		if (closePosition.getType().equals(PositionStates.statusTWSFire.BUY.toString())) // operacion de compra normal..??
			_OperationTYPE = PositionStates.statusTWSFire.SELL.toString();		
				
		if (Validator.isNull(backtestingdDate))
		{
			Order BuyPositionTWS = new Order();
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));			
			BuyPositionTWS.totalQuantity(closePosition.getShare_number());
			BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    					
			BuyPositionTWS.action(_OperationTYPE);			
			_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
			this.setTargetOrder(BuyPositionTWS);
		}
		//closePosition.setState_out(PositionStates.statusTWSCallBack.PendingSubmit.toString());	
		closePosition.setDate_out(Validator.isNull(backtestingdDate) ? new Date() : backtestingdDate);
		closePosition.setDescription(closePosition.getDescription() + StringPool.RETURN_NEW_LINE + this.getClass().getName());
		closePosition.setStrategy_out(this.getClass().getName());
		
		/* MODO FAKE CUENTA DEMO */
		closePosition = Utilities.fillStatesOrder(closePosition);
		/* END MODO FAKE CUENTA DEMO */
		
		
		PositionLocalServiceUtil.updatePosition(closePosition);
		/* Posicion en MYSQL de CONTROL */
		
		
		
		_log.info("Opening order " + closePosition.getPositionId());
		
		/* RETORNAMOS PORQUE DESPUES HAY QUE METER EN LA POSICION EN NUMERO DE ORDEN DE LA TWS */
		returnValue =  closePosition.getPositionId();

		
    }
	catch (Exception er)
        {
			_log.info(er.getMessage());
			er.printStackTrace();
		
        }
	return returnValue;
	}
	
	public IBStrategyClosePosition() {
		
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public  boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	
	boolean verified = false;
	List<Position> lToClose = null;
	try
    {
		String position_mode = Utilities.getPositionModeType(null, _share.getCompanyId(),_share.getGroupId());

		/* cancelada y abierta */
		lToClose = PositionLocalServiceUtil.findByCloseCompanyGroup(_share.getCompanyId(), _share.getGroupId(), Boolean.TRUE,position_mode); 
		if (lToClose!=null && !lToClose.isEmpty())
		{
			for (Position position : lToClose)
			{
				if (position.IsOpen() && position.IsCloseable())
				{
					closePosition  =position;
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
	public void init(long companyId) {
		// TODO Auto-generated method stub
		super.init(companyId);
	}
	
}
