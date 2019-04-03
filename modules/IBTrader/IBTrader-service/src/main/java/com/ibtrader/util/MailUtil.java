package com.ibtrader.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.mail.GmailCredentials;
import com.ibtrader.mail.GmailService;
import com.ibtrader.mail.GmailServiceImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.google.configuration.GoogleAuthorizationConfiguration;



public class MailUtil {
	
	private static Log log = LogFactoryUtil.getLog(MailUtil.class);
	private static String BODY_TEMPLATE ="<html>\r\n" + 
			"<body>\r\n" + 
			"<style>\r\n" + 
			"body {font-size :22px;font-family: \"Helvetica Neue\",Helvetica,Arial,sans-serif; }\r\n" + 
			"</style>\r\n" + 
			"\r\n" + 
    		"<div><a  href=\"https://tradeable.es\"><img  src=\"https://tradeable.es/wp-content/uploads/2018/11/Logo2.png\"></a></div>\r\n" + 
			"<br>\r\n" + 
			"<br>\r\n" +         		
			"</div>\r\n" + 
			"<table align=\"center\" width=\"100%\" style=\"margin:0px auto; width:800px\" cellpadding=5 cellspacing=5>\r\n" + 
			"\r\n" + 
			"<tr><td>Order details,</td></td>\r\n" + 
			"\r\n" + 
			"<tr><td>${SYMBOL}</td></td>\r\n" + 
			"<tr><td>${TYPE}</td></td>\r\n" + 
			"<tr><td>${NUMBER}</td></td>\r\n" + 
			"<tr><td>${STRATEGYIN}</td></td>\r\n" + 
			"<tr><td>${STRATEGYOUT}</td></td>\r\n" +
			"<tr><td>${INOUT}</td></td>\r\n" + 
			"<tr><td>${DATEIN}</td></td>\r\n" + 
			"<tr><td>${DATEOUT}</td></td>\r\n" + 
			"<tr><td>${PRICEIN}</td></td>\r\n" + 
			"<tr><td>${PRICEOUT}</td></td>\r\n" + 

			"\r\n" + 
			"\r\n" + 
			"</table>\r\n" + 
			"\r\n" + 
			"</body>\r\n" + 
			"</html>";

	
	/* APERTURA O CIERRE 
	 * MANDAMOS CORREO A 
	 * 1. USUARIOS DE LA ORGANIZACION
	 * 2. CONTACTOS DE ADDRESSES
	 * 3. OMNIADMINS
	 * */
	private static String fromAddress = "";
	private static String fromName = "";
	
	private static void sendEmail(String emailTo, String toName, String subject, String body) throws UnsupportedEncodingException
	{
		

		
		log.debug("++++++++++++++++++++ Enviando correo a "+emailTo);
		log.debug("body:"+body);
		log.debug("subject:"+subject);

		/* MailMessage message = new MailMessage(from, to, subject, body ,true);
		MailServiceUtil.sendEmail(message);
		*/
		String[] _to = {emailTo}; 
		try {
			sendFromGMail(_to, subject, body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void sendPositionNotification(Position position)
	{
		
	try
		{
		
		fromName    = PrefsPropsUtil.getString(position.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
		fromAddress = PrefsPropsUtil.getString(position.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		
		Group group = GroupLocalServiceUtil.getGroup(position.getGroupId());
		long notification_enabled = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS, position.getCompanyId(), group.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	
		if (notification_enabled==0)
		{
			log.debug("No se envia notificaciones para position " + position.getPositionId() + " y grupo " + group.getFriendlyURL() );
			return;
		}
		
		
		Share share = ShareLocalServiceUtil.fetchShare(position.getShareId());
		
		User user = UserLocalServiceUtil.fetchUser( share.getUserCreatedId());
		
		long organizationId =0;
	    if (group.isOrganization())    	
	    	  organizationId = group.getClassPK();
        
		//Organization org = OrganizationLocalServiceUtil.getOrganization(organizationId);
	
		List<User> users =  UserLocalServiceUtil.getOrganizationUsers(organizationId);
		
		List<EmailAddress> addresslist = EmailAddressLocalServiceUtil.getEmailAddresses(position.getCompanyId(), Organization.class.getName(), organizationId);

		
		String _EmailTo = "";
		String _FullName = "";
		String _Subject = "";
		String _Body = "";
	
		_Subject = "Order " + position.getType() + StringPool.SPACE +  (position.IsPendingOut() || position.IsPendingIn() ? "IN"  : "OUT") + StringPool.SPACE + share.getSymbol();
		
		
		double price_in = position.getPrice_real_in()>0 ? Utilities.RoundPrice(position.getPrice_real_in()) :  Utilities.RoundPrice(position.getPrice_in());
		double price_out = position.getPrice_real_out()>0 ? Utilities.RoundPrice(position.getPrice_real_out()) :  Utilities.RoundPrice(position.getPrice_out());
		
		_Body = _Body.replace("${PRICEIN}", String.valueOf(price_in));
		_Body = _Body.replace("${PRICEOUT}", String.valueOf(price_out));

		/* SALIDA */ 
		double profit = 0; 
		if (price_out>0) profit = ( price_out - price_in) / price_in * 100;
	    String _symbolProfitLoss = "";
	    if (profit>0.0)
	    	_symbolProfitLoss = "\u2B06"; // flecha arriba
	    if (profit<0.0)
	    	_symbolProfitLoss = "\u2B07"; // flecha abajo	    
		profit = Utilities.RoundPrice(profit);
		if (!_symbolProfitLoss.equals(""))
		{
			_Subject += StringPool.SPACE +  profit + StringPool.PERCENT  + StringPool.SPACE + _symbolProfitLoss;	 	 	
		}
		
		_Body = BODY_TEMPLATE;
		_Body = _Body.replace("${SYMBOL}", share.getSymbol());
		_Body = _Body.replace("${TYPE}", position.getType());
		_Body = _Body.replace("${NUMBER}",String.valueOf(position.getShare_number()));
		_Body = _Body.replace("${STRATEGYIN}", position.getStrategy_in());
		_Body = _Body.replace("${STRATEGYOUT}", Validator.isNull(position.getStrategy_out()) ? "" : position.getStrategy_out());
		_Body = _Body.replace("${INOUT}", position.IsPendingOut() || position.IsPendingIn() ? "IN"  : "OUT");
		
		/* puede ser nulo porque se queden inactivas por alguna razon */
		
		_Body = _Body.replace("${DATEIN}", Validator.isNull(position.getDate_real_in()) ? Utilities.getWebFormattedDate(position.getDate_in(), user) :  Utilities.getWebFormattedDate(position.getDate_real_in(), user));
		
		
		Date outDate = null;
		if (Validator.isNull(position.getDate_real_out()))
				outDate = position.getDate_real_out();
		else
			if (Validator.isNull(position.getDate_real_out())) 
				outDate = position.getDate_out();
		
		_Body = _Body.replace("${DATEOUT}", Validator.isNull(outDate) ? "" : Utilities.getWebFormattedDate(outDate, user));
		_Body = _Body.replace("${PRICEIN}", String.valueOf(price_in));
		_Body = _Body.replace("${PRICEOUT}", String.valueOf(price_out));

		
		/* USUARIOS ORGANIZACION */
		for (User notification_user : users)
		{
			_EmailTo =  notification_user.getEmailAddress();
			_FullName = notification_user.getFullName();
			
			sendEmail(_EmailTo,_FullName, _Subject,_Body);
			
		}
		/* USUARIOS ADICIONALES EN ORGANIZACION  */
		for (EmailAddress address : addresslist)
		{
			_EmailTo =  address.getAddress();
			
			sendEmail(_EmailTo,_FullName, _Subject,_Body);
			
		}
		
		/* OMNI ADMIN */
		sendEmail(fromAddress,fromName, _Subject,_Body );
		
	}catch(Exception e){
		log.debug("Error, no se envia notificaciones para position " + String.valueOf(position.getPositionId()) + ":" + e.getMessage());		
		
	}
		
	}
	
	private static void sendFromGMail(String[] to, String subject, String body) throws IOException {
		
	try 
	{
		
		/* NOTA :
		 * 
		 * EL ACCESS_TOKEN Y REFRESH_TOKEN SE ESTAN GUARDANDO EUN UN DIRECTORIO tokens, necesario la primera vez
		 * NO SE COGEN DE LOS PARAMETROS ENVIADOS POR AQUI 
		 * TIENE QUE ESTAR UN FICHERO client_secret_.json en el classloader de las CLASES 
		 *
		 * TRADEABLA FROM JAVA
		 */

		GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
		Company company  = CompanyLocalServiceUtil.fetchCompany(PortalUtil.getDefaultCompanyId());
		GoogleAuthorizationConfiguration googleAuthorizationConfiguration = ConfigurationProviderUtil.getCompanyConfiguration(GoogleAuthorizationConfiguration.class, company.getCompanyId());
		//boolean googleAuthEnabled = googleAuthorizationConfiguration.enabled();
		String googleClientId = googleAuthorizationConfiguration.clientId();
		String googleClientSecret = googleAuthorizationConfiguration.clientSecret();
		/* access token y refresj token se generan en el directorio token */
		
		/* GmailCredentials gmailCredentials = new GmailCredentials("tradeable.info@gmail.com", "18671109478-o7bqs2b3i5voo15k032nl9rpeje8kd9o.apps.googleusercontent.com",
         		"4zaPAIN6Pk79HCJk5zIEbqUz", "ya29.Glt9BuQ-gn8_Jgxg_Z8yv8fG2u0LZP3IjDaDNdbrG700jysQ4J--Xtcj_5T_se4SI225GRRy4dk8xKQra98QTVF1WAoAjeIi54IK62J10fBjlqTGcMM0vkZgaWrY", 
         		"1/t7WnvBvwcT-r3_laS4fiagnXxdHVwKUPkxSumSIcijs");
		*/
		
		

		GmailCredentials gmailCredentials = new GmailCredentials(PrefsPropsUtil.getString(company.getCompanyId(),PropsKeys.ADMIN_EMAIL_FROM_ADDRESS),googleClientId,googleClientSecret );
        gmailService.setGmailCredentials(gmailCredentials);
        
        for  (int j=0;j<to.length;j++)
        {
        	gmailService.sendMessage(to[j], subject,body);	
        }
  
       
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
	
	
	public static void main(String[] args) throws IOException 
	{
		
		
		String[] _to = {"david_nevado@yahoo.es"}; 
		sendFromGMail(_to, "hola", "hola");

	}
			
}
