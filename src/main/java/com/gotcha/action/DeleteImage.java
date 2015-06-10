package com.gotcha.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class DeleteImage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6721104258030628319L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String imageRef = request.getParameter("imageRef");
			File deleteFile = new File(getServletContext().getRealPath("/")+"data\\"+imageRef);
			
			
			System.out.println(deleteFile.delete());
			System.out.println(deleteFile.getAbsoluteFile().delete());
			
		}catch(SecurityException se)
		{
			System.out.println("---------------------------------securityError");
			se.printStackTrace();
		} catch (Exception x) {
			System.out.println("---------------------------------Error");
			x.printStackTrace();
		} finally {
			response.setContentType("text/html");
			response.sendRedirect("WEB-ADMIN/gallery.jsp");
		}
	}
}
