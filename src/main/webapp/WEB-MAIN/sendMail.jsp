<%@ page import="sun.net.smtp.SmtpClient, java.io.*" %> 
<%@ page import="java.io.*"%> 
<%@ page import="java.io.ByteArrayInputStream" %> 
<%@ page import="java.io.IOException" %> 
<%@ page import="java.security.cert.CertificateFactory" %> 
<%@ page import="java.security.cert.X509Certificate" %> 
<%@ page import="sun.net.smtp.SmtpClient, java.io.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%@ page import="javax.mail.*" %> 
<%@ page import="javax.mail.internet.*" %> 
<%@ page import="java.security.Security.*" %> 
<%@ page import="javax.activation.*" %> 

<%! 
boolean WasEmailSent = false; 
%> 

<% 


String host="", user="", pass=""; 
host = "smtp.gmail.com"; 
user = "stgeorgebatroun@gmail.com";//just username no need to mention the domail ex:sravani 
pass = "website71770257";//enter ur password 
String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
String to = "stgeorgebatroun@gmail.com"; 
String from = "stgeorgebatroun@gmail.com"; 
String subject = "Welcome"; 
String messageText = "Message From the Page Mail"; 
boolean sessionDebug = true; 

Properties props = new Properties(); 

props.put("mail.smtp.user", user); 
props.put("mail.smtp.host", host); 
props.put("mail.transport.protocol.", "smtp"); 
props.put("mail.smtp.auth", "true"); 
props.put("mail.smtp.starttls.enable","true"); 
props.put("mail.smtp.debug", "true"); 
props.put("mail.debug", "true"); 
props.put("mail.smtp.port", "587"); 
//props.put("mail.smtp.socketFactory.p... "25"); 
//props.put("mail.smtp.socketFactory.fal... "false"); 
//props.put("mail.smtp.socketFactory.cla... SSL_FACTORY); 

Authenticator auth = new javax.mail.Authenticator() { 
protected PasswordAuthentication getPasswordAuthentication() { 
return new PasswordAuthentication("stgeorgebatroun@gmail.com", "website71770257"); 
} 
}; 



Session mailSession = Session.getInstance(props); 
mailSession.setDebug(sessionDebug); 

MimeMessage msg = new MimeMessage(mailSession); 
msg.setFrom(new InternetAddress(from)); 
InternetAddress[] address = {new InternetAddress(to)}; 
msg.addRecipients(Message.RecipientType.TO,to); 
msg.setSubject(subject); 
msg.setText(messageText); 
Transport transport = mailSession.getTransport("smtp"); 

transport.connect(host, 587, user, pass); 
transport.sendMessage(msg, msg.getAllRecipients()); 
transport.close(); 
//transport.sendMessage(msg, msg.getAllRecipients()); 
/*try { 
transport.sendMessage(msg, msg.getAllRecipients()); 
boolean WasEmailSent = true; // assume it was sent 
} 
catch (Exception err) { 
WasEmailSent = false; // assume it's a fail 
}*/ 
//transport.close(); 
//out.println(WasEmailSent); 
out.println("Mail Sent....."); 

%>