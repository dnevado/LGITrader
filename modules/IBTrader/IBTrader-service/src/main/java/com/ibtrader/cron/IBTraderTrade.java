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
import com.ibtrader.data.service.persistence.ConfigUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;

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


import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.ibtrader.util.CronUtil;



/* property = {"cron.expression=0 0 0 * * ?"}, */
@Component(  immediate = true,  service = IBTraderTrade.class)
public class IBTraderTrade  extends BaseSchedulerEntryMessageListener {


	Log _log = LogFactoryUtil.getLog(IBTraderTrade.class);
	private static boolean runningJob = false;
	private volatile boolean _initialized;
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
		
		// if we previously were initialized
	    if (_initialized) {
	      // unschedule the job so it is cleaned up	  
	        _schedulerEngineHelper.unregister(this);
	      
	     }

	    // clear the initialized flag
	    _initialized = false;
		}
	
	@Activate
	@Modified
	protected void activate() throws SchedulerException {
		
		 /* OJO, SI SE NAJA LA FRECUENCIA DE LANZAMIENTO, PUEDEN PRODUCTIRSE HILOS CONCURRENTES, PORQUE LA VERIFICACION DEL CRONRUNNING ESTA CADA 
		  * 5 SEGUNDOS 
		  */
		
		if (_initialized) {
	      // first deactivate the current job before we schedule.
	      deactivate();
	    }

		
	     schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),10, TimeUnit.SECOND));  

		
		_log.info("Activating CRON IBTraderTrade..."  + schedulerEntryImpl.getTrigger());
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		
		// set the initialized flag.
	    _initialized = true;
	    
	  
		
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
		
	   if(runningJob)
	   {
		   		_log.info("CronTrading already running, not starting again");
		        return;
	   }
		runningJob = true;
		try
		{
			CronUtil.StartTradingCron(message);
		}
		catch (Exception e)
		{
			runningJob = false;
		}
		runningJob = false; 
		
			
} // END RECEIVER
}// END CLASS
