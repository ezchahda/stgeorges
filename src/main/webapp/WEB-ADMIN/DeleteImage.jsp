<%@ page language="java" import="java.sql.*,java.io.*"%>
<%
	String userName = null;
	String userImage = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username"))
				userName = cookie.getValue();
			if (cookie.getName().equals("userimage"))
				userImage = cookie.getValue();
		}
	}
	if (userName == null)
		response.sendRedirect("login.html");
	else {

		String imageRef = request.getParameter("imageRef");
		
		if(imageRef.contains("/uploads"))
			imageRef.replace("/uploads","");
		
		File f = new File(System.getenv("OPENSHIFT_DATA_DIR") + imageRef);
		if (f.delete())
			out.println(1);
		else
			out.println(-1);
	}
%>