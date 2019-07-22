package com.ibtrader.mail;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.liferay.portal.kernel.util.Validator;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/* 
 * 
 * https://developers.google.com/oauthplayground/
 * https://github.com/pablo127/gmail-api-sample
 *//* 
 * 
 * https://developers.google.com/oauthplayground/
 * https://github.com/pablo127/gmail-api-sample
 * 
 * https://medium.com/@pablo127/google-api-authentication-with-oauth-2-on-the-example-of-gmail-a103c897fd98
 * 
 */

public final class GmailServiceImpl implements GmailService {

    private static final String APPLICATION_NAME = "TRADEABLA FROM JAVA";

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private final HttpTransport httpTransport;
    private GmailCredentials gmailCredentials;
    
   // private static final String TOKENS_DIRECTORY_PATH = "tokens";
    
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);

    
    public GmailServiceImpl(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    @Override
    public void setGmailCredentials(GmailCredentials gmailCredentials) {
        this.gmailCredentials = gmailCredentials;
    }

    @Override
    public boolean sendMessage(String recipientAddress, String subject, String body) throws MessagingException,
            IOException {
        Message message = createMessageWithEmail(
                createEmail(recipientAddress, gmailCredentials.getUserEmail(), subject, body));

        return createGmail().users()
                .messages()
                .send(gmailCredentials.getUserEmail(), message)
                .execute()
                .getLabelIds().contains("SENT");
    }
    @Override
    public void  sendMessage(InternetAddress[] recipientAddress, String subject, String body) throws MessagingException,
            IOException {
    	
    	@SuppressWarnings("unused")
		boolean returnValue=Boolean.FALSE;
    	for  (int j=0;j<recipientAddress.length;j++)
    	{
    		returnValue = this.sendMessage(String.valueOf(recipientAddress[j]),subject, body);
    	}
    }

    private Gmail createGmail()  {
		    Credential credential = null;
			try {
				credential = authorize();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
         return new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build(); 
        
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
        try {
			email.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));// https://tools.ietf.org/html/rfc2047
		} catch (UnsupportedEncodingException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // email.setSubject(subject,"UTF-8");
        //email.setText(bodyText);        
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(bodyText, "text/html; charset=UTF-8");         
        mp.addBodyPart(htmlPart);
        email.setContent(mp);
        return email;
    }

    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);

        return new Message()
                .setRaw(Base64.encodeBase64URLSafeString(buffer.toByteArray()));
    }


    
    
    private Credential authorize() throws IOException {
    	
    	
    	  // Load client secrets.
    	
    	/* OJO A LA RUTAS WINDOWS / LUNUX  ,/tokens*/
    	java.io.File TOKENS_DIRECTORY_PATH = new java.io.File((Validator.isNotNull(System.getProperty("catalina.base")) ? System.getProperty("catalina.base") : "") + "/tokens");
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = null;
       try {
			flow = new GoogleAuthorizationCodeFlow.Builder(
			        httpTransport, JSON_FACTORY, gmailCredentials.getClientId(), gmailCredentials.getClientSecret(), SCOPES)
			        .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH))
			        .setAccessType("offline")
			        .setApprovalPrompt("force")                    
			        .build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(-1).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    	
    
    	
   
    }
}