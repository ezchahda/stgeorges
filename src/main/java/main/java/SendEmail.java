package main.java;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {

    public static boolean run(String name,String email, String subject, String content) {

    	String recipients = "contactus@stgeorgebatroun.com";//Your Email Address//
        String fromAddress = "contactus@stgeorgebatroun.com";
        String contentType = "text/plain";

        String smtpHost = "smtp.zoho.com";//Your Outgoing Mailbox//
        int smtpPort = 465;
        String username = "contactus@stgeorgebatroun.com";//Your Mailbox Username//
        String password = "website71770257";//Your Mailbox Password//
        
        
        content = "message from: "+email+" in the website contact form:\n\n\n"+content;
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.user", username);
            props.put("mail.smtp.host", "smtp.zoho.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallbac k", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            
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
            messagingException.printStackTrace();
            return false;

        } catch (Exception e)
        {
            System.out.print(e);
            return false;
        }
    }
}

