package com.gotcha.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gotcha.bo.LoginBO;
import com.gotcha.bo.UtilBO;

public class DeletePost extends HttpServlet{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String postId 	= request.getParameter("postid");
			LoginBO loginBO = new LoginBO();
			UtilBO utilBO = new UtilBO();
			
			utilBO.removePost(postId);
			
			Cookie postsCookie = new Cookie("totalPosts",""+loginBO.getTotalPosts());
			response.addCookie(postsCookie);
			
	        response.setContentType("text/html");
	        response.sendRedirect("WEB-ADMIN/postManage.jsp");
	    }
}
