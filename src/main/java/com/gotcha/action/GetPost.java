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
			String startAt = "0";
			
			if(request.getParameter("postId")!=null && request.getParameter("postId").length()>1)
				postId = request.getParameter("postId");
			
			if(request.getParameter("postType")!=null && request.getParameter("postType").length()>1)
				postType = request.getParameter("postType");
			
			if(request.getParameter("postSubType")!=null && request.getParameter("postSubType").length()>1 && !request.getParameter("postSubType").equals("undefined")){
				postSubType = request.getParameter("postSubType");
				
				if(postSubType.equals("icons"))
					postSubType = "&#1571;&#1610;&#1602;&#1608;&#1606;&#1575;&#1578;";
				if(postSubType.equals("stories"))
					postSubType = "&#1602;&#1589;&#1589; &#1608; &#1581;&#1603;&#1575;&#1610;&#1575;&#1578;";
				if(postSubType.equals("saints"))
					postSubType = "&#1587;&#1610;&#1585; &#1602;&#1583;&#1610;&#1587;&#1610;&#1606;";
				if(postSubType.equals("info"))
					postSubType = "&#1605;&#1593;&#1604;&#1608;&#1605;&#1575;&#1578; &#1608; &#1605;&#1587;&#1578;&#1606;&#1583;&#1575;&#1578;";
			
			}
			
			if(request.getParameter("startAt")!=null && request.getParameter("startAt").length()>0){
				startAt = request.getParameter("startAt");
			}
			
			UtilBO utilBO 	= new UtilBO();
			
			resultMap 		= utilBO.getPost(postId,postType,postSubType,startAt);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			 
			json = gson.toJson(resultMap);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
	    }
}
