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

    public static String run(String name,String email, String subject, String content) {

    	String recipients = "contactus@stgeorgebatroun.com";//Your Email Address//
        String fromAddress = "stgeorgebatroun@gmail.com";
        String contentType = "text/plain";

        String smtpHost = "smtp.gmail.com";//Your Outgoing Mailbox//
        int smtpPort = 465;
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

            return "success";
        } catch (MessagingException messagingException)
        {
            return "ERROR: "+messagingException.getLocalizedMessage();

        } catch (Exception e)
        {
            return "ERROR: "+e.getLocalizedMessage();
        }
    }
}

