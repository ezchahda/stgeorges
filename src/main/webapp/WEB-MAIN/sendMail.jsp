<%@ page import="main.java.SendEmail"%>
<%
	if (request.getParameter("name") != null) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject= request.getParameter("subject");
		String message = request.getParameter("message");

		if(main.java.SendEmail.run(name, email, subject, message) 
		out.println("success");
		else
			out.println("error");
	}
%>