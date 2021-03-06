package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "uploads", urlPatterns = { "/uploads/*" })
@MultipartConfig
public class Uploads extends HttpServlet {

	private static final long serialVersionUID = 2857847752169838915L;
	int BUFFER_LENGTH = 4096;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		try {

			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					// Process regular form field (input
					// type="text|radio|checkbox|etc", select, etc).
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					out.println("name=" + fieldName);
					out.println("value=" + fieldValue);
				} else {
					// Process form file field (input type="file").
					// String fieldName = item.getFieldName();

					String fileName = FilenameUtils.getName(item.getName());
					InputStream is = item.getInputStream();

					String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

					if (dataDirectory == null) {
						dataDirectory = "/data/";
					}

					FileOutputStream os = new FileOutputStream(dataDirectory
							+ fileName);
					byte[] bytes = new byte[BUFFER_LENGTH];
					int read = 0;
					while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					is.close();
					os.close();
					out.println(fileName + " was uploaded to "
							+ System.getenv("OPENSHIFT_DATA_DIR"));
				}
			}
		} catch (Exception x) {
		}
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		String filePath = request.getRequestURI();
		String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");

		if (dataDirectory == null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String path = getServletContext().getRealPath("/");
			path += "/data/"+ filePath.replace("/uploads/", "");
			File file = new File(path);
			InputStream input = new FileInputStream(file);
			response.setContentLength((int) file.length());
			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			OutputStream output = response.getOutputStream();
			byte[] bytes = new byte[BUFFER_LENGTH];
			int read = 0;
			while ((read = input.read(bytes, 0, BUFFER_LENGTH)) != -1) {
				output.write(bytes, 0, read);
				output.flush();
			}
			input.close();
			output.close();
		} else {
			File file = new File(dataDirectory + filePath.replace("/uploads/", ""));
			InputStream input = new FileInputStream(file);
			response.setContentLength((int) file.length());
			response.setContentType(new MimetypesFileTypeMap().getContentType(file));
			OutputStream output = response.getOutputStream();
			byte[] bytes = new byte[BUFFER_LENGTH];
			int read = 0;
			while ((read = input.read(bytes, 0, BUFFER_LENGTH)) != -1) {
				output.write(bytes, 0, read);
				output.flush();
			}
			input.close();
			output.close();
		}
	}

	private String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"",
						"");
			}
		}
		return null;
	}
}