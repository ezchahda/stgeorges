<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
    language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>email</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<%@page import="java.util.Properties"%>
<%@page import="javax.mail.*"%>
<%@page import="javax.mail.internet.*"%>
 
 
<%
String to="stgeorgebatroun@gmail.com";//change accordingly  

Properties props = new Properties();  
props.put("mail.smtp.host", "smtp.gmail.com");  
props.put("mail.smtp.socketFactory.port", "465");  
props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
props.put("mail.smtp.auth", "true");  
props.put("mail.smtp.port", "465");  
 
Session s = Session.getDefaultInstance(props,  
 new javax.mail.Authenticator() {  
 protected PasswordAuthentication getPasswordAuthentication() {  
 return new PasswordAuthentication("stgeorgebatroun@gmail.com","website71770257");//change accordingly  
 }  
});  

try {  
	   MimeMessage message = new MimeMessage(s);  
	   message.setFrom(new InternetAddress("stgeorgebatroun@gmail.com"));//change accordingly  
	   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	   message.setSubject("Hello");  
	   message.setText("Testing.......");  
	     
	   //send message  
	   Transport.send(message);  
	  
	   System.out.println("message sent successfully");  
	   
	  } catch (MessagingException e) {throw new RuntimeException(e);}  


%>  
</body>
</html>