package com.gotcha.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gotcha.bo.UtilBO;
import com.gotcha.vo.PostVO;

public class GetPost extends HttpServlet{

	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			ArrayList<PostVO> resultMap = new ArrayList<PostVO>();
			
			String json 	= null ;
			
			String postId = null;
			String postType = null;
			
			if(request.getParameter("postId")!=null && request.getParameter("postId").length()>1)
				postId = request.getParameter("postId");
			
			if(request.getParameter("postType")!=null && request.getParameter("postType").length()>1)
				postType = request.getParameter("postType");
			
			UtilBO utilBO 	= new UtilBO();
			
			resultMap 		= utilBO.getPost(postId,postType);
			
			 Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			 
			 json = gson.toJson(resultMap);
			
			 response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
	    }
}
