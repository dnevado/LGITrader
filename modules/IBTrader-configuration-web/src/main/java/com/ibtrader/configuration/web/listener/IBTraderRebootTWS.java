package com.ibtrader.configuration.web.listener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;

@Component(
     immediate = true, property = {"destination.name=RebootTWS"},
     service = MessageListener.class
 )
 public class IBTraderRebootTWS extends BaseMessageListener {
 
     @Override
     protected void doReceive(Message message) throws Exception {
         if (_log.isInfoEnabled()) {
            _log.info("Received: " + message);
        }

       String payload = (String)message.getPayload();
        if (_log.isInfoEnabled()) {
            _log.info("Message payload: " + payload);
        }

        String responseDestinationName = message.getResponseDestinationName();

        if ((responseDestinationName != null) &&
            (responseDestinationName.length() > 0)) {

            Message responseMessage = new Message();

            responseMessage.setDestinationName(responseDestinationName);
            responseMessage.setResponseId(message.getResponseId());

            //This is just for demo purposes

            responseMessage.setPayload(payload);

            _messageBus.sendMessage(
                message.getResponseDestinationName(), responseMessage);
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(IBTraderRebootTWS.class);

    @Reference
    private volatile MessageBus _messageBus;
}