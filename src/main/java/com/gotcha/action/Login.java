package com.gotcha.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gotcha.bo.LoginBO;
import com.gotcha.vo.UserVO;

public class Login extends HttpServlet{

	
	protected void doGet(HttpServletRequest request,   HttpServletResponse response) throws ServletException, IOException {		 
		  
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		String json = null ;
		
		String username 	= request.getParameter("username");
		String password 	= request.getParameter("password"); 
		
		LoginBO loginBO = new LoginBO();
		
		resultMap = loginBO.checkCredentials(username, password);
		
		json= new Gson().toJson(resultMap);
		
		if(resultMap.containsKey("session_id")){
			UserVO userVO = loginBO.getUserInfo(username);
			Cookie loginCookie = new Cookie("username",userVO.getFullname());
			Cookie imageCookie = new Cookie("userimage",userVO.getImageSource());
			Cookie emailCookie = new Cookie("useremail",userVO.getUsername());
			Cookie usersCookie = new Cookie("totalUsers",""+loginBO.getTotalUsers());
			Cookie postsCookie = new Cookie("totalPosts",""+loginBO.getTotalPosts());
			
			//setting cookie to expiry in 120 mins
	        loginCookie.setMaxAge(120*60);
	        imageCookie.setMaxAge(120*60);
	        emailCookie.setMaxAge(120*60);
	        usersCookie.setMaxAge(120*60);
	        postsCookie.setMaxAge(120*60);
	        
	        response.addCookie(loginCookie);
	        response.addCookie(emailCookie);
	        response.addCookie(usersCookie);
	        response.addCookie(postsCookie);
	        response.addCookie(imageCookie);
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
