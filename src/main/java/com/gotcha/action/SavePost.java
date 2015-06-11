package com.gotcha.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.gotcha.bo.UtilBO;

@MultipartConfig
public class SavePost extends HttpServlet {

	private static final long serialVersionUID = 2857847752169838915L;
	int BUFFER_LENGTH = 4096;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String post_title = "";
		String post_date = "";
		String post_content = "";
		String post_image = null;
		String post_type = "";
		String post_sub_type = "";

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					String paramName = item.getFieldName();
					String paramValue = item.getString();

					if (paramName != null) {

						if (paramName.equals("postTitle"))
							post_title = paramValue;

						if (paramName.equals("postDate"))
							post_date = paramValue;

						if (paramName.equals("postContent"))
							post_content = paramValue;

						if (paramName.equals("postType"))
							post_type = paramValue;

						if (paramName.equals("postSubType"))
							post_sub_type = paramValue;
					}
				} else {

					String fileName = FilenameUtils.getName(item.getName());
					InputStream is = item.getInputStream();
					FileOutputStream os = new FileOutputStream(System
							.getenv("OPENSHIFT_DATA_DIR")
							+ fileName);
					byte[] bytes = new byte[BUFFER_LENGTH];
					int read = 0;
					while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					is.close();
					os.close();
					post_image = fileName;
				}

			}

		} catch (Exception XX) {
			System.out.println("error uploading file");
			response.getWriter().write("error");
			response.setContentType("text/html");
			response.sendRedirect("WEB-ADMIN/postOffice.jsp");
			XX.printStackTrace();
		}
		// } else {
		// post_title = request.getParameter("postTitle");
		// post_date = request.getParameter("postDate");
		// post_content = request.getParameter("postContent");
		// post_type = request.getParameter("postType");
		// post_sub_type = request.getParameter("postSubType");
		// }
		
		UtilBO utilBO = new UtilBO();
		utilBO.addPost(post_title, post_type, post_sub_type, post_date,
				post_content, post_image);

		Cookie postsCookie = new Cookie("totalPosts", utilBO.getTotalPosts()+"");
		response.addCookie(postsCookie);
		response.getWriter().write("success");
		response.setContentType("text/html");
		response.sendRedirect("WEB-ADMIN/postManage.jsp");
	}
}
