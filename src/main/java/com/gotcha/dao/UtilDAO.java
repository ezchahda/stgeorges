package com.gotcha.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

import com.gotcha.vo.PostVO;
import com.gotcha.vo.UserVO;

public class UtilDAO {

	public ArrayList<UserVO> getUser(String username) {

		Connection connection = null;
		try {

			connection = this.connectDB();

			ArrayList<UserVO> user = new ArrayList<UserVO>();
			String query = "SELECT echo_name, echo_email, echo_password FROM echo_user";
			if (username != "" && username != null)
				query += " WHERE echo_email='" + username + "'";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {
				UserVO userVO = new UserVO();
				String echo_email = rs.getString("echo_email");
				String echo_name = rs.getString("echo_name");
				String echo_password = rs.getString("echo_password");

				userVO.setFullname(echo_name);
				userVO.setUsername(echo_email);
				userVO.setPassword(echo_password);

				user.add(userVO);
			}
			return user;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public void addUser(String email, String name, String image, String password) {
		Connection connection = null;
		try {

			connection = this.connectDB();
			Statement stmt = connection.createStatement();

			String sql = "INSERT INTO echo_user (echo_email, echo_name, echo_image, echo_password)"
					+ "VALUES ('"
					+ email
					+ "','"
					+ name
					+ "','"
					+ image
					+ "','" + password + "')";

			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void editUser(String email, String name, String image,
			String password) {
		Connection connection = null;
		try {

			connection = this.connectDB();
			Statement stmt = connection.createStatement();

			String sql = "UPDATE echo_user SET echo_name = '" + name
					+ "' , echo_image = '" + image + "' , echo_password = '"
					+ password + "' WHERE echo_email = '" + email + "';";

			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void addPost(String postId, String postType, String postSubType,
			String postTitle, String postContent, String postImage,
			int postYear, int postMonth, int postDay) {
		Connection connection = null;
		try {

			connection = this.connectDB();

			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(
					calendar.getTime().getTime());

			String sql = "INSERT INTO echo_post (post_time_stamp, post_id, post_type, post_starred, post_detailed, post_pinned, post_sub_type, post_title, post_content, post_image, post_year, post_month, post_day) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setTimestamp(1, currentTimeStamp);

			stmt.setString(2, postId);
			stmt.setString(3, postType);

			stmt.setInt(4, 0);
			stmt.setInt(5, 0);
			stmt.setInt(6, 0);

			stmt.setString(7, postSubType);
			stmt.setString(8, postTitle);
			stmt.setString(9, postContent);
			stmt.setString(10, postImage);

			stmt.setInt(11, postYear);
			stmt.setInt(12, postMonth);
			stmt.setInt(13, postDay);

			stmt.executeUpdate();

		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void addEditPost(String postId, String postType, String postSubType,
			String postTitle, String postContent, String postImage,
			int postYear, int postMonth, int postDay) {
		Connection connection = null;
		try {

			connection = this.connectDB();

			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp currentTimeStamp = new java.sql.Timestamp(
					calendar.getTime().getTime());

			Boolean yesImage = true;

			String checkImage = ", post_image=? ";

			if (postImage == null) {
				checkImage = "";
				yesImage = false;
			}

			String sql = "UPDATE echo_post set " + "post_time_stamp=?, " + // 1
					"post_type=?, " + // 2
					"post_sub_type=?, " + // 3
					"post_title=?, " + // 4
					"post_content=?, " + // 5
					"post_year=?, " + // 6
					"post_month=?, " + // 7
					"post_day=? " // 8
					+ checkImage + // 9
					"  WHERE post_id=?;"; // 9 - 10

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setTimestamp(1, currentTimeStamp);
			stmt.setString(2, postType);
			stmt.setString(3, postSubType);
			stmt.setString(4, postTitle);
			stmt.setString(5, postContent);
			stmt.setInt(6, postYear);
			stmt.setInt(7, postMonth);
			stmt.setInt(8, postDay);

			if (yesImage) {
				stmt.setString(9, postImage);
				stmt.setString(10, postId);
			} else {
				stmt.setString(9, postId);
			}
			stmt.executeUpdate();

		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		}finally {
			this.closeConnection(connection);
		}
	}

	private Connection connectDB() {
		Connection connection = null;
		try {
			InputStream input = this.getClass().getClassLoader()
					.getResourceAsStream("db.properties");
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
				if (key.equals("dbname"))
					dbname = value;
				if (key.equals("dbhost"))
					dbhost = value;
				if (key.equals("dbuser"))
					dbuser = value;
				if (key.equals("dbpass"))
					dbpass = value;
			}

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + dbhost
					+ "/" + dbname, dbuser, dbpass);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		}
		return connection;
	}

	private void closeConnection(Connection c) {
		try {
			if (c != null)
				c.close();
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		}
	}

	public ArrayList<PostVO> getPost(String postId, String postType,String postSubType,int startingPoint) {

		Connection connection = null;
		try {

			connection = this.connectDB();

			ArrayList<PostVO> post = new ArrayList<PostVO>();
			String query = "SELECT * FROM echo_post where 1 = 1";

			if (postId != "" && postId != null)
				query += " AND post_id='" + postId + "'";

			if (postType != "" && postType != null)
				query += " AND post_type='" + postType + "'";

			if (postSubType != "" && postSubType != null)
				query += " AND post_sub_type='" + postSubType + "'";
			
			query += " ORDER BY post_time_stamp desc ";
			
			query += " LIMIT 5 OFFSET "+startingPoint;

			System.out.println("query is:" +query);
			
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				String postTitle = rs.getString("post_title");
				String postImage = rs.getString("post_image");
				String postContent = rs.getString("post_content");
				
				postSubType = rs.getString("post_sub_type");
				postType = rs.getString("post_type");
				postId = rs.getString("post_id");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostImage(postImage);
				postVO.setPostSubType(postSubType);
				postVO.setPostType(postType);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public ArrayList<PostVO> getPosts() {

		Connection connection = null;
		try {
			connection = this.connectDB();

			ArrayList<PostVO> post = new ArrayList<PostVO>();
			String query = "SELECT post_id, post_title, post_year, post_month, post_day, post_image, post_content, post_sub_type, post_type, post_starred, post_detailed, post_pinned FROM echo_post";
			query += " ORDER BY post_time_stamp desc";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				String postTitle = rs.getString("post_title");
				String postImage = rs.getString("post_image");
				String postContent = rs.getString("post_content");
				String postSubType = rs.getString("post_sub_type");
				String postType = rs.getString("post_type");
				String postId = rs.getString("post_id");

				int postStarred = rs.getInt("post_starred");
				int postDetailed = rs.getInt("post_detailed");
				int postPinned = rs.getInt("post_pinned");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostImage(postImage);
				postVO.setPostSubType(postSubType);
				postVO.setPostType(postType);
				postVO.setPostStarred(postStarred);
				postVO.setPostDetailed(postDetailed);
				postVO.setPostPinned(postPinned);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public ArrayList<PostVO> getStarredPosts() {

		Connection connection = null;
		try {
			connection = this.connectDB();

			ArrayList<PostVO> post = new ArrayList<PostVO>();

			String query = "SELECT post_id, post_title, post_year, post_month, post_day, post_image, post_content, post_sub_type, post_type FROM echo_post WHERE post_starred=1";
			query += " ORDER BY post_time_stamp desc";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				String postTitle = rs.getString("post_title");
				String postImage = rs.getString("post_image");
				String postContent = rs.getString("post_content");
				String postSubType = rs.getString("post_sub_type");
				String postType = rs.getString("post_type");
				String postId = rs.getString("post_id");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostImage(postImage);
				postVO.setPostSubType(postSubType);
				postVO.setPostType(postType);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public ArrayList<PostVO> getDetailedPosts() {

		Connection connection = null;
		try {
			connection = this.connectDB();

			ArrayList<PostVO> post = new ArrayList<PostVO>();

			String query = "SELECT post_id, post_title, post_year, post_month, post_day, post_image, post_content, post_sub_type, post_type FROM echo_post WHERE post_detailed=1";
			query += " ORDER BY post_time_stamp desc";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				String postTitle = rs.getString("post_title");
				String postImage = rs.getString("post_image");
				String postContent = rs.getString("post_content");
				String postSubType = rs.getString("post_sub_type");
				String postType = rs.getString("post_type");
				String postId = rs.getString("post_id");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostImage(postImage);
				postVO.setPostSubType(postSubType);
				postVO.setPostType(postType);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public ArrayList<PostVO> getPinnedPosts() {

		Connection connection = null;
		try {
			connection = this.connectDB();

			ArrayList<PostVO> post = new ArrayList<PostVO>();

			String query = "SELECT post_id, post_title, post_year, post_month, post_day, post_image, post_content, post_sub_type, post_type FROM echo_post WHERE post_pinned=1";
			query += " ORDER BY post_time_stamp desc";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				String postTitle = rs.getString("post_title");
				String postImage = rs.getString("post_image");
				String postContent = rs.getString("post_content");
				String postSubType = rs.getString("post_sub_type");
				String postType = rs.getString("post_type");
				String postId = rs.getString("post_id");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostImage(postImage);
				postVO.setPostSubType(postSubType);
				postVO.setPostType(postType);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public ArrayList<PostVO> getNews(String postId) {

		Connection connection = null;
		try {
			connection = this.connectDB();
			ArrayList<PostVO> post = new ArrayList<PostVO>();
			String query = "SELECT post_id, post_title, post_year, post_month, post_day, post_content, post_sub_type FROM echo_post where post_type='news'";
			if (postId != "" && postId != null)
				query += " AND post_id='" + postId + "'";
			query += " ORDER BY post_time_stamp desc";

			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {

				PostVO postVO = new PostVO();
				postId = rs.getString("post_id");
				String postTitle = rs.getString("post_title");
				String postContent = rs.getString("post_content");
				String postSubType = rs.getString("post_sub_type");

				int postYear = rs.getInt("post_year");
				int postMonth = rs.getInt("post_month");
				int postDay = rs.getInt("post_day");

				postVO.setPostContent(postContent);
				postVO.setPostDay(postDay);
				postVO.setPostMonth(postMonth);
				postVO.setPostYear(postYear);
				postVO.setPostTitle(postTitle);
				postVO.setPostId(postId);
				postVO.setPostSubType(postSubType);

				post.add(postVO);
			}
			return post;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return null;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return null;
		} finally {
			this.closeConnection(connection);
		}
	}

	public int getTotalPosts() {
		Connection connection = null;
		int postCount = 0;
		try {
			connection = this.connectDB();
			String query = "SELECT count(post_id) postCount FROM echo_post";
			ResultSet rs = connection.createStatement().executeQuery(query);
			while (rs.next()) {
				postCount = rs.getInt("postCount");
			}
			return postCount;
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
			return 0;
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
			return 0;
		} finally {
			this.closeConnection(connection);
		}
	}

	public void removeUser(String email) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "DELETE FROM echo_user WHERE echo_email='" + email
					+ "';";

			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void removePost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "DELETE FROM echo_post WHERE post_id='" + postId
					+ "';";

			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void starPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_starred` =  '1' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void unStarPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_starred` =  '0' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void detailPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_detailed` =  '1' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void unDetailPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_detailed` =  '0' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void pinPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_pinned` =  '1' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

	public void unPinPost(String postId) {
		Connection connection = null;
		try {
			connection = this.connectDB();
			Statement stmt = connection.createStatement();
			String sql = "UPDATE  `echo_post` SET  `post_pinned` =  '0' WHERE  `echo_post`.`post_id` ='"
					+ postId + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException ce) {
			System.out.println("SQL Connection error");
		} catch (Exception e) {
			System.out.println("Unknown Data Access error");
		} finally {
			this.closeConnection(connection);
		}
	}

}
