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
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.ibtrader.scheduler.impl.StorageTypeAwareSchedulerEntryImpl;
import com.ibtrader.util.CronUtil;



/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderRemovePastRealTime.class)
public class IBTraderRemovePastRealTime  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderRemovePastRealTime.class);
	private volatile boolean runningJob = false;
	private volatile boolean _initialized = false;


	private SchedulerEngineHelper _schedulerEngineHelper;

	
	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

   protected StorageType getStorageType() {
	    if (schedulerEntryImpl instanceof StorageTypeAware) {
	      return ((StorageTypeAware) schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.PERSISTED ;
	  }
	@Deactivate
	protected void deactivate() {
		
		System.out.println("IBTraderRemovePastRealTime deactivate runningJob:" + runningJob); 

		
		// if we previously were initialized
	    if (_initialized) {
	      // unschedule the job so it is cleaned up
	      try {
	        _schedulerEngineHelper.unschedule(schedulerEntryImpl, getStorageType());
	      } catch (SchedulerException se) {
	        if (_log.isWarnEnabled()) {
	          _log.warn("Unable to unschedule trigger", se);
	        }
	      }

	      // unregister this listener
	      _schedulerEngineHelper.unregister(this);
	    }
	    
	    // clear the initialized flag
	    _initialized = false;
	   
		 // clear the initialized flag
	
		    
	}
	
	@Activate
	@Modified
	protected void activate() {
		
	/* 	 que se dispare en el proximo minuto y el dia +28   
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				calendar.getTime(),cron.toString())); */
		
				
	    schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(schedulerEntryImpl, StorageType.PERSISTED);
	    // update the trigger for the scheduled job.
		
	    schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),1, TimeUnit.DAY));  
	
		
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
		
		_log.info("IBTraderRemovePastRealTime doReceive runningJob:" + runningJob); 
	   if(runningJob) 
	   {
		   		_log.debug("IBTraderRemovePastRealTime already running, not starting again");
		        return;
	   }	
		try
		{
			runningJob = true;					
			CronUtil cronThread = new CronUtil();			
			cronThread.StartRemovePastRealTime(message);
		}
		catch (Exception e)
		{
			runningJob = false;
			_log.debug("IBTraderRemovePastRealTime doReceive" + e.getMessage());
		}
		runningJob = false; 
			
} // END RECEIVER
}// END CLASS
