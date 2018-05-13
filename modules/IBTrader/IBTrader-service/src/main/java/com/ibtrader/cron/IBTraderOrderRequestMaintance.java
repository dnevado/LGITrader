package com.ibtrader.cron;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
import com.ibtrader.util.CronUtil;
import com.ibtrader.util.Utilities;


import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;


/* CADA 1 MINUTO VERIFICAMOS LOS VENCIMIENTOS FUTUROS EN UTC / CET */

/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderOrderRequestMaintance.class)
public class IBTraderOrderRequestMaintance  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderOrderRequestMaintance.class);
	private SchedulerEngineHelper _schedulerEngineHelper;

	
	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	
	@Deactivate
	protected void deactivate() {
		
		/* INTENTAMOS ELIMINAR TODAS LAS QUE SE PUEDEN ELIMINAR, REALTIME, CONTRACT DETAIL, ETC...*/
	
		_log.info("Deleting iborders requestid while rebooting...");
		/* DEJAMOS 7 DIAS PARA LOS REINICIOS PREVENTIVOS */
		Calendar cNow = Calendar.getInstance();	
		cNow.set(Calendar.HOUR_OF_DAY,23);
		cNow.set(Calendar.MINUTE,59);
		cNow.set(Calendar.SECOND,59);
		
		List<IBOrder> _ordersToRemove =  IBOrderLocalServiceUtil.findByRemovableDate(cNow.getTime(), Boolean.TRUE);
		
		for (IBOrder order : _ordersToRemove)
		{
			IBOrderLocalServiceUtil.deleteIBOrder(order);
		}
		 	 
		_schedulerEngineHelper.unregister(this);
		
		_log.info("End Deleting iborders requestid while rebooting...");
	}
	
	@Activate
	@Modified
	protected void activate() {
		
	/* 	 que se dispare en el proximo minuto y el dia +28   
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				calendar.getTime(),cron.toString())); */
	 
	     schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),6, TimeUnit.HOUR));  
		_log.info("Activating CRON..."  + schedulerEntryImpl.getTrigger());
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
		
		CronUtil.StartDeletingOldOrderRequestCron(message);
	 	 
			
	} // END RECEIVER
}// END CLASS
