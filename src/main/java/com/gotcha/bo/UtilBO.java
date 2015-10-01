package com.gotcha.bo;

import java.util.ArrayList;
import java.util.UUID;

import com.gotcha.dao.UtilDAO;
import com.gotcha.vo.PostVO;
import com.gotcha.vo.UserVO;

public class UtilBO {

	
	public String generateSessionId(){
		return "";
	}
	
	public ArrayList<UserVO> getUser(String username){
		UtilDAO utilDAO = new UtilDAO();

		ArrayList<UserVO> result = utilDAO.getUser(username);
		
		return result; 
	}
	
	public ArrayList<PostVO> getPost(String postId, String postType,String postSubType,String startAt){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getPost(postId,postType,postSubType,startAt);
		
		return result; 
	}

	public ArrayList<PostVO> getPosts(){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getPosts();
		
		return result; 
	}
	
	public ArrayList<PostVO> getNews(String postId){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getNews(postId);
		
		return result; 
	}
	
	public void addUser(String email, String name, String image, String password){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.addUser(email,name,image,password);
	}
	
	public void editUser(String email, String name, String image, String password){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.editUser(email,name,image,password);
	}
	
	public int getTotalPosts(){
		UtilDAO utilDAO = new UtilDAO();
		return utilDAO.getTotalPosts();
	}
	
	public void addPost(String post_title, String post_type, String post_sub_type, String post_date, String post_content,String post_image){
		UtilDAO utilDAO  = new UtilDAO();
		
		int postYear	= 1980;
		int postMonth	= 1;
		int postDay		= 1;
		
		if(post_date != null){
		String[] postDateArray 	= post_date.split("/");
		postYear	= Integer.parseInt(postDateArray[2]);
		postMonth	= Integer.parseInt(postDateArray[0]);
		postDay		= Integer.parseInt(postDateArray[1]);
		}
		
		if(post_image == null)
			post_image = "null";
		
		try{
			
			String postId = generatePostId();
			utilDAO.addPost(postId, post_type, post_sub_type, post_title, post_content, post_image, postYear, postMonth, postDay);
		}catch(Exception a){
			a.printStackTrace();
		}
	}
	
	public void addEditPost(String post_id, String post_title, String post_type, String post_sub_type, String post_date, String post_content,String post_image){
		UtilDAO utilDAO  = new UtilDAO();
		
		String[] postDateArray 	= post_date.split("/");
		int postYear	= Integer.parseInt(postDateArray[2]);
		int postMonth	= Integer.parseInt(postDateArray[0]);
		int postDay		= Integer.parseInt(postDateArray[1]);
		
		try{
			
			utilDAO.addEditPost(post_id, post_type, post_sub_type, post_title, post_content, post_image, postYear, postMonth, postDay);
		}catch(Exception a){
			a.printStackTrace();
		}
	}
	
	public String generatePostId(){
		UUID id = UUID.randomUUID();
		String idOne = id.toString().split("-")[0];
		return idOne;
	}
	
	public void removeUser(String username){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.removeUser(username);
	}
	
	public void removePost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.removePost(postId);
	}
	
	public ArrayList<PostVO> getStarredPosts(){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getStarredPosts();
		
		return result; 
	}
	
	public ArrayList<PostVO> getDetailedPosts(){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getDetailedPosts();
		
		return result; 
	}
	
	public ArrayList<PostVO> getPinnedPosts(){
		UtilDAO utilDAO = new UtilDAO();
		
		ArrayList<PostVO> result = utilDAO.getPinnedPosts();
		
		return result; 
	}
	
	public void starPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.starPost(postId);
	}
	
	public void unStarPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.unStarPost(postId);
	}
	
	public void pinPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.pinPost(postId);
	}
	
	public void unPinPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.unPinPost(postId);
	}
	
	public void detailPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.detailPost(postId);
	}
	
	public void unDetailPost(String postId){
		UtilDAO utilDAO = new UtilDAO();
		utilDAO.unDetailPost(postId);
	}
}
