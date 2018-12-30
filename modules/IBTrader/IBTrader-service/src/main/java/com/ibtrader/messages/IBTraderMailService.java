package com.ibtrader.messages;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.ibtrader.mail.GmailCredentials;
import com.ibtrader.mail.GmailService;
import com.ibtrader.mail.GmailServiceImpl;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.google.configuration.GoogleAuthorizationConfiguration;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

/* Componente que sobreescribe el identificador de mail para que pasen todos los envios por aqui y usan
 * GMAIL API como servidor de envio 
 */

@Component(immediate = true, service = IBTraderMailService.class)
public class IBTraderMailService extends BaseMessageListener {

	
	private static final Log _log = LogFactoryUtil.getLog(IBTraderMailService.class);

	
    private ArrayList<MessageListener> messageListeners;
    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        Destination destination = _destination;
        messageListeners = new ArrayList<MessageListener>(destination.getMessageListeners());
        destination.unregisterMessageListeners();
        System.out.println("IBTraderMailService activate mail.. to be implemented");
        destination.register(this);
    }

    @Deactivate
    @Modified
    protected void deactivate(Map<String, Object> properties) {
        Destination destination = _destination;
        destination.unregisterMessageListeners();
        System.out.println("IBTraderMailService unregisterMessageListeners mail.. to be implemented");
        for (MessageListener messageListener : messageListeners) {
            destination.register(messageListener);
        }
    }
    
    @Override
    protected void doReceive(Message message) {
        
    	_log.debug("IBTraderMailService sending mail.. to be implemented" + message);
        try 
        {
        
        MailMessage mailMessage = (MailMessage) message.getPayload();

        _log.debug(mailMessage);
        

		if (Validator.isNotNull(mailMessage) && mailMessage.getTo().length>0) {
			
			//MailEngine.send(mailMessage);
			/* YA CON EL ALMACENAMIENTO UNA VEZ, NECESITAMOS ALMANCEANR EL ACCESS_TOKEN QUE NO EXPIRA */
            /* TRADEABLA FROM JAVA */
            GmailService gmailService;
			try {
				
				gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
				Company company  = CompanyLocalServiceUtil.fetchCompany(PortalUtil.getDefaultCompanyId());
				GoogleAuthorizationConfiguration googleAuthorizationConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(GoogleAuthorizationConfiguration.class, company.getCompanyId());
				//boolean googleAuthEnabled = googleAuthorizationConfiguration.enabled();
				String googleClientId = googleAuthorizationConfiguration.clientId();
				String googleClientSecret = googleAuthorizationConfiguration.clientSecret();
				GmailCredentials gmailCredentials = new GmailCredentials(PrefsPropsUtil.getString(company.getCompanyId(),PropsKeys.ADMIN_EMAIL_FROM_ADDRESS),googleClientId,googleClientSecret );
 
				gmailService.setGmailCredentials(gmailCredentials);
				gmailService.sendMessage(mailMessage.getTo(),mailMessage.getSubject(),mailMessage.getBody());
		    
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				_log.debug(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				_log.debug(e.getMessage());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				_log.debug(e.getMessage());
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				_log.debug(e.getMessage());
			}
		    
			}
        }
        catch (Exception e)
        {
        	_log.debug(e.getMessage());
        }
        
        
    }
    
    @Reference(target = "(destination.name=liferay/mail)")
    private Destination _destination;
	
}