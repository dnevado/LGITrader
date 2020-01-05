package com.ibtrader.mail;




import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.liferay.portal.kernel.util.StringPool;


/* 
 * 
 * https://developers.google.com/oauthplayground/
 * https://github.com/pablo127/gmail-api-sample
 * https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=ya29.Glt9Bqu4EJzRAQmL4a2o4LcxuwmbvU5JBv2zOuss3RgaC7Lu6V4Ejj-NgI6kN8PgGq2vX7wjDg5b_RmPS0oavDAraxaBbpSSCCOh6x0CvHS93_IH2tBm72sjGa8e
 
 *
 *
 *	1. ejecutar en eclipse el codigo de abajo
 *	2. se abre un navegadpr autorizando la cuenta de tradeable
 *	3. copiar el token generado qe esta a nivel de modulo en la ruta del tomcat cuando hay un servidor 
 */
public class GmailTradeable {

    public static void main(String[] args) {
        try {
            GmailService gmailService = new GmailServiceImpl(GoogleNetHttpTransport.newTrustedTransport());
            
            
           /* YA CON EL ALMACENAMIENTO UNA VEZ, NECESITAMOS ALMANCEANR EL ACCESS_TOKEN QUE NO EXPIRA */
            /* TRADEABLA FROM JAVA */
            GmailCredentials gmailCredentials = new GmailCredentials("tradeable.info@gmail.com", "18671109478-o7bqs2b3i5voo15k032nl9rpeje8kd9o.apps.googleusercontent.com",
            		"4zaPAIN6Pk79HCJk5zIEbqUz", "ya29.Glt9BuQ-gn8_Jgxg_Z8yv8fG2u0LZP3IjDaDNdbrG700jysQ4J--Xtcj_5T_se4SI225GRRy4dk8xKQra98QTVF1WAoAjeIi54IK62J10fBjlqTGcMM0vkZgaWrY", 
            		"1/t7WnvBvwcT-r3_laS4fiagnXxdHVwKUPkxSumSIcijs");
                                    
            gmailService.setGmailCredentials(gmailCredentials);
           
            String BODY_TEMPLATE ="<html>\r\n" + 
        			"<body>\r\n" + 
        			"<style>\r\n" + 
        			"body { font-family: \"Helvetica Neue\",Helvetica,Arial,sans-serif; }\r\n" + 
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
        			"\r\n" + 
        			"\r\n" + 
        			"</table>\r\n" + 
        			"\r\n" + 
        			"</body>\r\n" + 
        			"</html>";
            
          //  U+2B06 // UP  
           // U+2B07 // DOWN 
            String _symbolProfitLoss = "⬆ \u2B06\u2B07"; 
        	String Subject = StringPool.SPACE +   StringPool.PERCENT  + StringPool.SPACE + _symbolProfitLoss;	 	 	
            gmailService.sendMessage("refundable.tech@gmail.com", Subject, BODY_TEMPLATE);
            System.out.println("menssage sent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}