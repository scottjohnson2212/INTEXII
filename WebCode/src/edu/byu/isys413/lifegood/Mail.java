
package edu.byu.isys413.lifegood;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * The BatchEmail object is responsible for sending an email to a customer.
 * This will be called by the LateItemBatch object for each customer that has a
 * late rental item to inform them that their credit card has been charged for
 * the replacement price of the late item.
 */
public class Mail {

    /**
     * Sends an email to a given customer
     */
    public static void send(String fromAddress, String fromName, String toAddress, String subject, String msg) throws UnsupportedEncodingException, MessagingException {
            Properties props = new Properties();
            props.put("mail.smtp.host", MailSettings.smtpHost);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", 465);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Session session = Session.getDefaultInstance(props, new ForcedAuthenticator());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress, fromName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(subject);
            message.setText(msg);
            message.setSentDate(new Date());
            Transport.send(message);
    }

    /**
     * Prepares the SMTP user to authenticate against the server
     */
    static class ForcedAuthenticator extends Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(MailSettings.smtpUserName, MailSettings.smtpPassword);
        }
    }
}
    
//    /**
//     * Testing
//     */
//    public static void main(String args[]) {
//      try {
//        Mail.send("scottbrasil21@gmail.com", "Scott Johnson", "scott.johnson2212@gmail.com", "This was sent from my Program :)", "Hello World");
//        System.out.println("Sent!");
//      } catch (Exception e) {
//        e.printStackTrace();
//      }//try
//    }//public
//}//class
