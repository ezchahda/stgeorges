<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ page  language="java" import="java.sql.*,java.io.*"  %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>St Georges | Admin | Gallery</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	  .action
        {
            width: 400px;
            height: 30px;
            margin: 10px 0;
        }
	</style>
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/charisma-app.css" rel="stylesheet">
	<link href="css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='css/fullcalendar.css' rel='stylesheet'>
	<link href='css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='css/chosen.css' rel='stylesheet'>
	<link href='css/uniform.default.css' rel='stylesheet'>
	<link href='css/colorbox.css' rel='stylesheet'>
	<link href='css/jquery.cleditor.css' rel='stylesheet'>
	<link href='css/jquery.noty.css' rel='stylesheet'>
	<link href='css/noty_theme_default.css' rel='stylesheet'>
	<link href='css/elfinder.min.css' rel='stylesheet'>
	<link href='css/elfinder.theme.css' rel='stylesheet'>
	<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='css/opa-icons.css' rel='stylesheet'>
	<link href='css/uploadify.css' rel='stylesheet'>
	<link rel="stylesheet" href="css/crop-style.css" type="text/css" />
	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>

<%@include file="topBar.jsp"%>
		<div class="container-fluid">
		<div class="row-fluid">
<%@include file="leftMenu.jsp"%>
			
		<div id="content" class="span10">
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="index.jsp">Home</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">Gallery</a>
					</li>
				</ul>
			</div>
			<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2><i class="icon-picture"></i> Image Cropper</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
							<div class="container" id="cropperContainer" style="display: none;">
							    <div class="imageBox">
							        <div class="thumbBox"></div>
							        <div class="spinner" style="display: none">Loading...</div>
							    </div>
							    <div class="action">
							    	<input type="button" id="btnCrop3" value="Cancel" style="float: right;">
							        <input type="button" id="btnCrop" value="Save" style="float: right;display: none">
							    	<input type="button" id="btnCrop1" value="Crop for big post" style="float: right">
							    	<input type="button" id="btnCrop2" value="Crop for small post" style="float: right">
							        <input type="button" id="btnZoomIn" value="+" style="float: right">
							        <input type="button" id="btnZoomOut" value="-" style="float: right">
							    </div>
							</div>
						</div>
					</div>
				</div>
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i> Gallery</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">
						<ul class="thumbnails gallery">
						<%	
								File dataFolder = new File(System.getenv("OPENSHIFT_DATA_DIR"));
						        File[] files = dataFolder.listFiles();
						        //out.println("Under construction");
								if(files!=null){
									for(int i=0;i<files.length;i++){
									%>
										<li id="image-<%=i%>" ref="<%out.println(files[i].getName());%>" class="thumbnail">
											<a	title="<%out.println(files[i]);%>" 
												href="../data/<%out.println(files[i].getName());%>">
												
												<img id="thumb-<%out.println(files[i].getName());%>" class="grayscale" src="../data/<%out.println(files[i].getName());%>" alt="<%out.println(files[i].getName());%>">
											</a>
										</li>
									<%}
								}
						 %>
						  </ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal hide fade" id="AlertImageDelete">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Delete Image?</h3>
			</div>
			<div class="modal-body">
				<p><b>Are you sure you want to delete Image</b></p>
				<p><b>ref : </b><span id="imageDelete"></span></p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" onClick="noDeleteImage()" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary" onClick="yesDeleteImage()" data-dismiss="modal">Ok Delete</a>
			</div>
		</div>
	<script src="js/jquery-1.7.2.min.js"></script>
	<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
	<script src="js/bootstrap-transition.js"></script>
	<script src="js/bootstrap-alert.js"></script>
	<script src="js/bootstrap-modal.js"></script>
	<script src="js/bootstrap-dropdown.js"></script>
	<script src="js/bootstrap-scrollspy.js"></script>
	<script src="js/bootstrap-tab.js"></script>
	<script src="js/bootstrap-tooltip.js"></script>
	<script src="js/bootstrap-popover.js"></script>
	<script src="js/bootstrap-button.js"></script>
	<script src="js/bootstrap-collapse.js"></script>
	<script src="js/bootstrap-carousel.js"></script>
	<script src="js/bootstrap-typeahead.js"></script>
	<script src="js/bootstrap-tour.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src='js/fullcalendar.min.js'></script>
	<script src='js/jquery.dataTables.min.js'></script>
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.min.js"></script>
	<script src="js/jquery.flot.pie.min.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<script src="js/jquery.chosen.min.js"></script>
	<script src="js/jquery.uniform.min.js"></script>
	<script src="js/jquery.colorbox.min.js"></script>
	<script src="js/jquery.cleditor.min.js"></script>
	<script src="js/jquery.noty.js"></script>
	<script src="js/jquery.elfinder.min.js"></script>
	<script src="js/jquery.raty.min.js"></script>
	<script src="js/jquery.iphone.toggle.js"></script>
	<script src="js/jquery.autogrow-textarea.js"></script>
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<script src="js/jquery.history.js"></script>
	<script src="js/charisma.js"></script>
	<script src="js/cropbox.js"></script>
	<script src="js/gallery.js"></script>
</body>
</html>