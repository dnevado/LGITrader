package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.ib.client.Contract;
import com.ib.client.Order;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
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
		_log.info("UserAccount: detectada posible salida de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
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
				
		if (!isSimulation_mode())
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
		closePosition.setDate_out(!isSimulation_mode() ? new Date() : backtestingdDate);
		closePosition.setDescription(closePosition.getDescription() + StringPool.RETURN_NEW_LINE + this.getClass().getName());
		closePosition.setStrategy_out(this.getClass().getName());
		closePosition.setForceclose(Boolean.FALSE); // no se ejecuta dos veces 
		
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
		String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());
		closePosition = null;
		/* cancelada y abierta */
		lToClose = PositionLocalServiceUtil.findByCloseCompanyGroup(_share.getCompanyId(), _share.getGroupId(), _share.getShareId(), Boolean.TRUE,position_mode); 
		if (lToClose!=null && !lToClose.isEmpty())
		{
			_log.debug("Found positions to close : "  + lToClose.size() );
			for (Position position : lToClose)
			{
				_log.debug("Found position to close : shareId:"  + position.getShareId()  + ",positionId:" + position.getPositionId() );
				if (position.IsOpen() && position.IsCloseable())
				{
					closePosition  = position;
					_log.debug("Open y Closeable : shareId:"  + position.getShareId()  + ",positionId:" + position.getPositionId() );
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
