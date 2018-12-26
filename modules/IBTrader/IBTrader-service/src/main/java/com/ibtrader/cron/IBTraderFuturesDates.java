package com.ibtrader.cron;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.ibtrader.util.CronUtil;


/* CADA 1 MINUTO VERIFICAMOS LOS VENCIMIENTOS FUTUROS EN UTC / CET */

/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderFuturesDates.class)
public class IBTraderFuturesDates  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderFuturesDates.class);
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
		_schedulerEngineHelper.unregister(this);
	}
	
	@Activate
	@Modified
	protected void activate() {
		
	/* 	 que se dispare en el proximo minuto y el dia +28   
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				calendar.getTime(),cron.toString())); */
	 
	     schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),20, TimeUnit.SECOND));  
		_log.info("Activating CRON..."  + schedulerEntryImpl.getTrigger());
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
		
		CronUtil.StartVerifyFuturesDatesCron(message);
	 	 
			
} // END RECEIVER
}// END CLASS
