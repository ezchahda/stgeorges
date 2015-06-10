<%@ page language="java" import="java.sql.*,java.io.*,org.apache.commons.*"%>
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
		String img 		= request.getParameter("encodeimg");
		String imgSrc 	= request.getParameter("imgSrc");
		
		String encodingPrefix = "base64,";
		
		int contentStartIndex = img.indexOf(encodingPrefix) + encodingPrefix.length();
		
		byte[] imageData = org.apache.commons.codec.binary.Base64.decodeBase64(img.substring(contentStartIndex));

		imgSrc = getServletContext().getRealPath("/")+"data\\" + imgSrc;
		
		FileOutputStream fos=new FileOutputStream(imgSrc);
		
		fos.write(imageData);
	}
%>