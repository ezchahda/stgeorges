/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Siriquelle
 */
public class SendEmail {

    public static boolean run(String name,String email, String subject, String content) {

    	String recipients = "stgeorgebatroun@gmail.com";//Your Email Address//
        String fromAddress = name+" <"+email.trim()+">";
        String contentType = "text/plain";

        String smtpHost = "smtp.gmail.com";//Your Outgoing Mailbox//
        int smtpPort = 587;
        String username = "stgeorgebatroun@gmail.com";//Your Mailbox Username//
        String password = "website71770257";//Your Mailbox Password//

        try
        {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getDefaultInstance(props);

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients, false));

            message.setSubject(subject);
            message.setContent(content, contentType);
            message.setSentDate(new Date());

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpPort, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (MessagingException messagingException)
        {
            System.out.print(messagingException);
            return false;

        } catch (Exception e)
        {
            System.out.print(e);
            return false;
        }
    }
}

