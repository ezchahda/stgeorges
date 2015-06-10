package com.gotcha.bo;

import java.util.LinkedHashMap;
import java.util.Map;

import com.gotcha.dao.LoginDAO;
import com.gotcha.vo.UserVO;

public class LoginBO {

	public Map<String, String> checkCredentials(String username, String password){
		
		LoginDAO loginDAO = new LoginDAO();

		int result = loginDAO.checkCredentials(username, password);
		
		Map<String, String> resultMap = this.getResultMap(result);
		
		return resultMap; 
	}
	
	private Map<String, String> getResultMap(int result){
		
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		UtilBO utilBO = new UtilBO();
		
		switch (result) {
		case 0:
			resultMap.put("Success", "Login Successful, redirecting...");
			String sessionId = utilBO.generateSessionId();
			resultMap.put("session_id", sessionId);
			break;
		case 1:
			resultMap.put("Error","Email Not Found");
			break;
		case 2:
			resultMap.put("Error", "Wrong Password");
			break;
		default:
			resultMap.put("Error", "Connection to Database timed out");
		}
		
		return resultMap;
	}
	
	public UserVO getUserInfo(String username){
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.getUserInfo(username);
	}
	
	public int getTotalUsers(){
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.getTotalUsers();
	}
	
	public int getTotalPosts(){
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.getTotalPosts();
	}
}
