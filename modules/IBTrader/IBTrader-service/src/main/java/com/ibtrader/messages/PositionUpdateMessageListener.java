package com.ibtrader.messages;

import com.ibtrader.data.model.Position;
import com.ibtrader.util.MailUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Balavigneshwaran M
 * ENVIA MENSAJES POR EMAIL  SEGUN ACTUALIZACION DE NOTIFICACIONES
 */

@Component(
		immediate = true, property = {"destination.name=position/update" },
		service = MessageListener.class
)

public class PositionUpdateMessageListener extends BaseMessageListener {


	private static Log _log = LogFactoryUtil.getLog(PositionUpdateMessageListener.class);

	

	@Override
	protected void doReceive(Message message) throws MessageListenerException {
		
		try {
			
			_log.debug("Recieved Message : " + message.toString());
			
			
			
			Position position = (Position) message.get("position");
			
			MailUtil.sendPositionNotification(position);
			
			_log.debug("Inside RepositoryMessageImpl  testStr=="+position.getDescription());
			
				
		}
		catch(Exception e) {
			
			_log.error("Invertio : Error creating repository : " + e.getMessage());
			
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
}
