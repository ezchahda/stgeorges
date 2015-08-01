package com.gotcha.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Properties;

import com.gotcha.vo.UserVO;

public class LoginDAO {

	public int checkCredentials(String username, String password) {
		Connection connection = this.connectDB();
		try{
		
		String query0 = "SELECT echo_email, echo_name, echo_image FROM echo_user WHERE echo_email='"+username+"' AND echo_password='"+password+"'";
		ResultSet rs = connection.createStatement().executeQuery(query0);
		while (rs.next()){return 0;}
		
		String query2 = "SELECT echo_email, echo_name, echo_image FROM echo_user WHERE echo_email='"+username+"'";
		rs = connection.createStatement().executeQuery(query2);
		while (rs.next()){return 2;}
		
		return 1;
		
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		finally{
			this.closeConnection(connection);
		}
	}
	
	public UserVO getUserInfo(String username){
		Connection connection = this.connectDB();
		try{
		
			UserVO userVO = new UserVO();
			String query = "SELECT echo_name, echo_image FROM echo_user WHERE echo_email='"+username+"'";
			ResultSet rs = connection.createStatement().executeQuery(query);
			while (rs.next()){
				String fullname = rs.getString("echo_name");
				String imageSource = rs.getString("echo_image");
				userVO.setFullname(fullname);
				userVO.setUsername(username);
				userVO.setImageSource(imageSource);
			}
			return userVO;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			this.closeConnection(connection);
		}
	}
	
	public int getTotalUsers(){
		Connection connection = this.connectDB();
		try{
			int userCount = 0;
			String query = "SELECT count(echo_name) userCount FROM echo_user";
			ResultSet rs = connection.createStatement().executeQuery(query);
			while (rs.next()){
				userCount = rs.getInt("userCount");
			}
			return userCount;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		finally{
			this.closeConnection(connection);
		}
	}
	
	public int getTotalPosts(){
		UtilDAO utilDAO = new UtilDAO();
		return utilDAO.getTotalPosts();
	}

	@SuppressWarnings("unchecked")
	private Connection connectDB (){
		
		Connection connection = null;
	    try{
			InputStream input = this.getClass().getClassLoader().getResourceAsStream("db.properties");
			
			Properties properties = new Properties();
			properties.load(input);
			input.close();
			String dbname = "";
			String dbhost = "";
			String dbuser = "";
			String dbpass = "";
			
			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if(key.equals("dbname"))
					dbname = value;
				if(key.equals("dbhost"))
					dbhost = value;
				if(key.equals("dbuser"))
					dbuser = value;
				if(key.equals("dbpass"))
					dbpass = value;
			}
	    	
	    	
			System.out.println("--------------->>>>>>>>>>>>>"+dbhost);
			
	    	Class.forName("com.mysql.jdbc.Driver");
	    	connection = DriverManager.getConnection("jdbc:mysql://"+dbhost+"/"+dbname,dbuser,dbpass);
	    } catch (Exception e){
	    	
	    	
	    	e.printStackTrace();
	    	
	    }
	    return connection;
	}
	    
	private void closeConnection (Connection c) {
		try {
	      if (c != null)
	        c.close();
	    } catch (Exception e) {}
	  }
}
