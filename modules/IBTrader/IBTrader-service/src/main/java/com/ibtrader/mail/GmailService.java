package com.ibtrader.mail;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import java.io.IOException;

public interface GmailService {
    void setGmailCredentials(GmailCredentials gmailCredentials);

    boolean sendMessage(String recipientAddress, String subject, String body) throws MessagingException, IOException;
    void  sendMessage(InternetAddress[] recipientAddress, String subject, String body) throws MessagingException, IOException;

}