package com.gotcha.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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
			String postSubType = null;
			
			
			if(request.getParameter("postId")!=null && request.getParameter("postId").length()>1)
				postId = request.getParameter("postId");
			
			if(request.getParameter("postType")!=null && request.getParameter("postType").length()>1)
				postType = request.getParameter("postType");
			
			if(request.getParameter("postSubType")!=null && request.getParameter("postSubType").length()>1){
				postSubType = request.getParameter("postSubType");
				System.out.println("b4 decoding:"+postSubType);
				postSubType = URLDecoder.decode(postSubType.replace("+", "%2B"), "UTF-8").replace("%2B", "+");
				
				Charset.forName("UTF-8").encode(postSubType);
			}
			
			System.out.println("after decoding:"+postSubType);
			
			UtilBO utilBO 	= new UtilBO();
			
			resultMap 		= utilBO.getPost(postId,postType,postSubType);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			 
			json = gson.toJson(resultMap);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
	    }
}
