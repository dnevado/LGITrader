package com.ibtrader.cron;

import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.scheduler.impl.StorageTypeAwareSchedulerEntryImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;

import java.util.Calendar;
import java.util.List;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.ibtrader.util.CronUtil;


/* CADA 1 MINUTO VERIFICAMOS LOS VENCIMIENTOS FUTUROS EN UTC / CET */

/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderOrderRequestMaintance.class)
public class IBTraderOrderRequestMaintance  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderOrderRequestMaintance.class);
	private SchedulerEngineHelper _schedulerEngineHelper;
    private volatile boolean runningJob = false;
    private volatile boolean _initialized = false;

	
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
		
		try 
		{
			
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
		}
		catch (Exception e)
		{
			
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
		   // if we were initialized (i.e. if this is called due to CA modification)
	    schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(schedulerEntryImpl, StorageType.PERSISTED);
	    // update the trigger for the scheduled job.
		
	    schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),6, TimeUnit.HOUR));  
		_log.info("Activating CRON..."  + schedulerEntryImpl.getTrigger());
		
		if (_initialized) {
		      // first deactivate the current job before we schedule.
		      deactivate();
		}
		
		 // set the initialized flag.
		_initialized = true;
		
	   _schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
			
	   if(runningJob) 
	   {
		   		_log.debug("StartDeletingOldOrderRequestCron already running, not starting again");
		   	
		        return;
	   }	
		try
		{
			runningJob = true;					
			CronUtil cronThread = new CronUtil();
			_log.debug(" Start IBTraderFillRequiredPastRealtime doReceive");
			cronThread.StartDeletingOldOrderRequestCron(message);
		}
		catch (Exception e)
		{
			runningJob = false;
			_log.debug("StartDeletingOldOrderRequestCron doReceive" + e.getMessage());
		}
		runningJob = false; 
		
			
	} // END RECEIVER
}// END CLASS
