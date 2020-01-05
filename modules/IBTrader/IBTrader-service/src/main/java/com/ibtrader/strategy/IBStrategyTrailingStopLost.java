package com.ibtrader.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;

public class IBStrategyTrailingStopLost extends StrategyImpl {

		
	private static final long serialVersionUID = 1L;
	private static Log _log = LogFactoryUtil.getLog(IBStrategyTrailingStopLost.class);
	private Position currentPosition = null;
	@Override
	public long execute(Share _share, Market _market, Date backtestingdDate) {
		// TODO Auto-generated method stub
	long returnValue=-1;
	try		
    {
		
		String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());

		
		boolean existsPositionToExit = PositionLocalServiceUtil.ExistsPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : ConfigKeys.DEFAULT_BACKTESTINGID_VALUE);
		if (!existsPositionToExit)
			return -1;
		
		_log.info("UserAccount: detectada posible salida  de " + _share.getName() +  "Tick:" + _share.getSymbol() + ",PrecioCompra:" + this.getValueIn());
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
	    String _OperationTYPE = PositionStates.statusTWSFire.BUY.toString(); 
		if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString())) // operacion de compra normal..??
			_OperationTYPE = PositionStates.statusTWSFire.SELL.toString();
		
		if (!isSimulation_mode())
		{
		
			Order BuyPositionTWS = new Order();
			BuyPositionTWS.account(Utilities.getConfigurationValue(IBTraderConstants.keyACCOUNT_IB_NAME, _share.getCompanyId(), _share.getGroupId()));			
			BuyPositionTWS.totalQuantity(currentPosition.getShare_number());
			BuyPositionTWS.orderType(PositionStates.ordertypes.MKT.toString());		    
			BuyPositionTWS.lmtPrice(Utilities.RoundPrice(this.getValueOut()));
			BuyPositionTWS.auxPrice(Utilities.RoundPrice(this.getValueOut()));				
			BuyPositionTWS.action(_OperationTYPE);			
			
			_log.info("Order" + BuyPositionTWS.action()  +","+  BuyPositionTWS.lmtPrice()  +","+ BuyPositionTWS.auxPrice() +","+ BuyPositionTWS.account() +","+ BuyPositionTWS.totalQuantity() +","+ BuyPositionTWS.orderType());
			this.setTargetOrder(BuyPositionTWS);
		}
		currentPosition.setPrice_out(this.getValueOut());
		//currentPosition.setState_out(PositionStates.statusTWSCallBack.PendingSubmit.toString());
		currentPosition.setDate_out(!isSimulation_mode() ?  new Date() : backtestingdDate);
		currentPosition.setDescription(currentPosition.getDescription() + StringPool.RETURN_NEW_LINE  +this.getClass().getName());
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
	
	public IBStrategyTrailingStopLost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
	
	boolean verified = false;
	boolean existsPositionToExit = false;
	try
    {		
	String position_mode = Utilities.getPositionModeType(backtestingdDate, _share.getCompanyId(),_share.getGroupId());
	
	existsPositionToExit = PositionLocalServiceUtil.ExistsPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : -1);
	if (existsPositionToExit)		
	{	
		/* CAMBIAMOS POR EL ULTIMO VALOR MENOR QUE AHORA PARA QUE SE PUEDAN METER VALORES FUTURES COMO CONJUNTO DE PRUEBAS */
		Date _ToNow   =  !isSimulation_mode() ?    new Date() : backtestingdDate;
		
		Double lastRealtime = null;												
		if (!isSimulation_mode())
		{
			Realtime oShareLastRTime =  RealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_ToNow);
			lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();
		}					
		else
		{
			HistoricalRealtime oShareLastRTime = HistoricalRealtimeLocalServiceUtil.findLastRealTimeLessThanDate(_share.getShareId(), _share.getCompanyId(), _share.getGroupId(),_ToNow);
			lastRealtime = Validator.isNull(oShareLastRTime) ? null : oShareLastRTime.getValue();

		}
		

		currentPosition = PositionLocalServiceUtil.findPositionToExit(_share.getGroupId(),_share.getCompanyId(),_share.getShareId(),position_mode, Validator.isNotNull(this.getCurrentBackTesting()) ?  this.getCurrentBackTesting().getBackTId() : -1);
		
		if (lastRealtime!=null && currentPosition!=null && currentPosition.getPercentual_trailling_stop_lost()>0)
		{	
			boolean bExistslost;
			/* PRECIO MEJOR DINAMICO */
			double trailling_stop_lost = currentPosition.getPricetrailling_stop_lost();		
			double percentual_trailling_stop_lost = currentPosition.getPercentual_trailling_stop_lost();
			double current_price = lastRealtime;

			if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString()))  // operacion de compra normal..??
				bExistslost = (current_price < trailling_stop_lost);
			else
				bExistslost = (current_price > trailling_stop_lost);
			if (bExistslost)
			{
				
			    this.setValueOut(current_price);
			    this.setValueLimitOut(current_price);	
				this.setVerified(Boolean.TRUE);												
				verified = true;
			}
			else // ajustamos el trailing dinámicamente 
			{
				double new_trailling_stop;
				boolean bPriceIncreased = Boolean.FALSE;				/* AJUSTAMOS EL PRECIO SIEMPRE Y CUANDO EL PRECIO VAYA AUMENTANDO */
						
				if (currentPosition.getType().equals(PositionStates.statusTWSFire.BUY.toString()))  // operacion de compra normal..??
				{
					new_trailling_stop 	=  (current_price -  (current_price * percentual_trailling_stop_lost /100));
					bPriceIncreased = new_trailling_stop > trailling_stop_lost; // vamos protegiendo mejor el beneficio 
				}
				else
				{
					new_trailling_stop  =  (current_price +  (current_price * percentual_trailling_stop_lost /100));
					bPriceIncreased = new_trailling_stop < trailling_stop_lost;
				}
				if (bPriceIncreased) // son distintos, actualizamos 
				{
					currentPosition.setPricetrailling_stop_lost(new_trailling_stop);
					PositionLocalServiceUtil.updatePosition(currentPosition);
				}
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
	 * strategyshare.strategyminmax.errorparams  =  El formato de los parï¿½metros es err
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
