package com.gotcha.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gotcha.bo.UtilBO;

public class SavePost extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DATA_DIRECTORY = "data";
	private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 4;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String post_title = "";
		String post_date = "";
		String post_content = "";
		String post_image = null;
		String post_type = "";
		String post_sub_type = "";

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MAX_MEMORY_SIZE);
			factory
					.setRepository(new File(System
							.getProperty("java.io.tmpdir")));

			String uploadFolder = getServletContext().getRealPath("")
					+ File.separator + DATA_DIRECTORY;

			ServletFileUpload upload = new ServletFileUpload(factory);

			upload.setSizeMax(MAX_REQUEST_SIZE);

			try {
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();

				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();

						if (fileName != null && fileName.length() > 0) {
							String filePath = uploadFolder + File.separator
									+ fileName;
							post_image = fileName;

							File uploadedFile = new File(filePath);
							item.write(uploadedFile);
						}
					}

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
					}

				}

			} catch (Exception XX) {
				System.out.println("error uploading file");
				response.getWriter().write("error");
				response.setContentType("text/html");
				response.sendRedirect("WEB-ADMIN/postOffice.jsp");
				XX.printStackTrace();
			}
		} else {
			post_title = request.getParameter("postTitle");
			post_date = request.getParameter("postDate");
			post_content = request.getParameter("postContent");
			post_type = request.getParameter("postType");
			post_sub_type = request.getParameter("postSubType");
		}
		UtilBO utilBO = new UtilBO();
		utilBO.addPost(post_title, post_type, post_sub_type, post_date,
				post_content, post_image);

		Cookie postsCookie = new Cookie("totalPosts", ""
				+ utilBO.getTotalPosts());
		response.addCookie(postsCookie);
		response.getWriter().write("success");
		response.setContentType("text/html");
		response.sendRedirect("WEB-ADMIN/postManage.jsp");
	}
}
