package com.gotcha.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gotcha.bo.UtilBO;
import com.gotcha.vo.UserVO;

public class GetUser extends HttpServlet{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			ArrayList<UserVO> resultMap = new ArrayList<UserVO>();
			
			String json 	= null ;
			
			String username = request.getParameter("username")!=null && request.getParameter("username").length()>1 ? request.getParameter("username") : null;
			
			UtilBO utilBO 	= new UtilBO();
			
			resultMap 		= utilBO.getUser(username);
			
			json			= new Gson().toJson(resultMap);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
	    }
}
