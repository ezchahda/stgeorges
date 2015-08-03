package main.java;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static String run(String name,String email, String subject, String content) {

    	final String username = "stgeorgebatroun@gmail.com";
		final String password = "website71770257";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		
		String emailmessage = "Email from: "+name+" <"+email+">\n\n Topic: "+subject+"\n\n"+content;

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("stgeorgebatroun@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("contactus@stgeorgebatroun.com"));
			message.setSubject("Message from St George Website");
			message.setText(emailmessage);
 
			Transport.send(message);
 
			return "Your Query has been received, Thank you.";
 
		} catch (MessagingException e) {
			return "Message not sent, we apologize we will fix this soon";
		}
	}
}

