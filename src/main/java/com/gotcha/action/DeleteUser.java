package com.gotcha.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gotcha.bo.LoginBO;
import com.gotcha.bo.UtilBO;

public class DeleteUser extends HttpServlet{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String echo_email 	= request.getParameter("username");
			LoginBO loginBO = new LoginBO();
			
			UtilBO utilBO = new UtilBO();
			
			utilBO.removeUser(echo_email);
			
			Cookie usersCookie = new Cookie("totalUsers",""+loginBO.getTotalUsers());
			response.addCookie(usersCookie);
			
	        response.setContentType("text/html");
	        response.sendRedirect("WEB-ADMIN/userManage.jsp");
	    }
}
