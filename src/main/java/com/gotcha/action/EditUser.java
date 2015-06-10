package com.gotcha.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gotcha.bo.LoginBO;
import com.gotcha.bo.UtilBO;

public class EditUser extends HttpServlet{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String echo_email 	= request.getParameter("userEmailEdit");
			String echo_name 	= request.getParameter("userNameEdit");
			String echo_password= request.getParameter("userPasswordEdit");
			String echo_image 	= request.getParameter("userImageEdit");
			
			UtilBO utilBO = new UtilBO();
			utilBO.editUser(echo_email, echo_name, echo_image, echo_password);
			
	        response.setContentType("text/html");
	        response.sendRedirect("WEB-ADMIN/userManage.jsp");
	    }
}
