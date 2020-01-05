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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.TriggerState;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
import com.ibtrader.scheduler.impl.StorageTypeAwareSchedulerEntryImpl;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.CronUtil;
import com.ibtrader.util.Utilities;


import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;



/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderSimulation.class)
public class IBTraderSimulation  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderSimulation.class);
	
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
		    
		    return StorageType.MEMORY_CLUSTERED;
		  }
		@Deactivate
		protected void deactivate() {
			
			System.out.println("TradingRead deactivate runningJob:" + runningJob); 

			
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
		
		   // if we were initialized (i.e. if this is called due to CA modification)
	    schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(schedulerEntryImpl, StorageType.PERSISTED);
	    // update the trigger for the scheduled job.
		
	    schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),10, TimeUnit.SECOND));  
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
			   		_log.debug("StartSimulationCron already running, not starting again");
			        return;
		   }
		
			try
			{
				runningJob = true;
				
				CronUtil cronThread = new CronUtil();
				_log.debug("Start StartSimulationCron doReceive");
				cronThread.StartSimulationCron(message);
			}
			catch (Exception e)
			{
				runningJob = false;
			}
			runningJob = false; 
		
		
	
			
} // END RECEIVER
}// END CLASS
