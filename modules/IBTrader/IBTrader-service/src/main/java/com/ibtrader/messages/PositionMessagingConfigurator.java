
package com.ibtrader.messages;

import com.liferay.portal.kernel.concurrent.CallerRunsPolicy;
import com.liferay.portal.kernel.concurrent.RejectedExecutionHandler;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component (
	    immediate = true,
	    service = PositionMessagingConfigurator .class
	)
	public class PositionMessagingConfigurator {
		
	
		private String MESSAGE_ID_LISTENER = "position/update";
		@Activate
		protected  void activate(BundleContext bundleContext) {
			
			_log.debug("Inside MyMessagingConfigurator activate===");
			
			_bundleContext = bundleContext;
			
			registerMessageDestination();
		}
		
		
		protected  void registerDestination( DestinationConfiguration destinationConfiguration) {

			Destination destination = _destinationFactory.createDestination(destinationConfiguration);

			Dictionary<String, Object> properties = new HashMapDictionary<>();

			properties.put("destination.name", destination.getName());

			ServiceRegistration<Destination>	serviceRegistration= _bundleContext.registerService(
					Destination.class, destination, properties);
			
			

			_serviceRegistrations.put(destination.getName(), serviceRegistration);
		}
		
		
		
		protected  void registerMessageDestination() {
			
			DestinationConfiguration destinationConfiguration =
				new DestinationConfiguration(
					DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
					MESSAGE_ID_LISTENER);

			destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);

			RejectedExecutionHandler rejectedExecutionHandler =
				new CallerRunsPolicy() {

					@Override
					public   void rejectedExecution(
						Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

						if (_log.isWarnEnabled()) {
							_log.warn(
								"The current thread will handle the request " +
									"because the graph walker's task queue is at " +
										"its maximum capacity");
						}

						super.rejectedExecution(runnable, threadPoolExecutor);
					}

				};

			destinationConfiguration.setRejectedExecutionHandler(
				rejectedExecutionHandler);

			registerDestination(destinationConfiguration);
		}

		
		@Deactivate
		protected  void deactivate() {
			
			unregisterMessageDestinations();
			
			_bundleContext = null;
			
		}
		
		
		protected  void unregisterMessageDestinations() {
			
			for (ServiceRegistration<Destination> serviceRegistration :   _serviceRegistrations.values()) {

	            Destination destination = _bundleContext.getService(
	                serviceRegistration.getReference());

	            serviceRegistration.unregister();

	            destination.destroy();

	        }

	        _serviceRegistrations.clear();
			
			
		}

		
	    
	    private static final Log _log = LogFactoryUtil.getLog(	    		PositionMessagingConfigurator.class);
	    
	    private static final int _MAXIMUM_QUEUE_SIZE = 200;
	    
	    private BundleContext _bundleContext;

	    @Reference
	    private DestinationFactory _destinationFactory;
	    
	    //private ServiceRegistration _serviceRegistrations;

	   private final Map<String, ServiceRegistration<Destination>>
	        _serviceRegistrations = new HashMap<String, ServiceRegistration<Destination>>();
	}
