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
	<style>
    .galleria{ width: 500px; height: 200px; background: #fff }
	</style>
</head>
<body>

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
							<a href="#">Albums</a>
						</li>
					</ul>
				</div>
				
				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header well" data-original-title>
							<h2><i class="icon-picture"></i> All Albums</h2>
							<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
						</div>
						<div class="box-content">
						
						
						<!-- Album Start -->
							<h4>Album Name: Random 1</h4>
							<div class="galleria">
							    <a href="../data/1.jpg"><img src="../data/1.jpg" data-title="My title" data-description="My description"></a>
							    <a href="../data/2.jpg"><img src="../data/2.jpg" data-title="Another title" data-description="My <em>HTML</em> description"></a>
							    <a href="http://www.youtube.com/watch?v=GCZrz8siv4Q"><img src="../data/Tulips.jpg"></a>
							</div>
							<h2>
							<i class="icon-trash icon-red icon32"></i>
							<i class="icon-edit icon-red icon32"></i>
							</h2>
							<h4>----------------------------------------------------------------------------------------------------</h4>
							<br/><br/><br/>
						<!-- Album End -->
						
						
						
						<!-- Album Start -->
							<h4>Album Name: Random 2</h4>
							<div class="galleria">
							    <a href="../data/3.jpg"><img src="../data/3.jpg" data-title="My title" data-description="My description"></a>
							    <a href="../data/bread.jpg"><img src="../data/bread.jpg" data-title="Another title" data-description="My <em>HTML</em> description"></a>
							    <a href="http://www.youtube.com/watch?v=GCZrz8siv4Q"><img src="../data/story.jpg"></a>
							</div>
							<h2>
							<i class="icon-trash icon-red icon32"></i>
							<i class="icon-edit icon-red icon32"></i>
							</h2>
							<h4>----------------------------------------------------------------------------------------------------</h4>
							<br/><br/><br/>
						<!-- Album End -->
						
						<!-- Album Start -->
							<h4>Album Name: Random 3</h4>
							<div class="galleria">
							    <a href="../data/3.jpg"><img src="../data/3.jpg" data-title="My title" data-description="My description"></a>
							    <a href="../data/bread.jpg"><img src="../data/bread.jpg" data-title="Another title" data-description="My <em>HTML</em> description"></a>
							    <a href="http://www.youtube.com/watch?v=GCZrz8siv4Q"><img src="../data/story.jpg"></a>
							</div>
							<h2>
							<i class="icon-trash icon-red icon32"></i>
							<i class="icon-edit icon-red icon32"></i>
							</h2>
							<h4>----------------------------------------------------------------------------------------------------</h4>
							<br/><br/><br/>
						<!-- Album End -->
						
						<!-- Album Start -->
							<h4>Album Name: Random 4</h4>
							<div class="galleria">
							    <a href="../data/3.jpg"><img src="../data/3.jpg" data-title="My title" data-description="My description"></a>
							    <a href="../data/bread.jpg"><img src="../data/bread.jpg" data-title="Another title" data-description="My <em>HTML</em> description"></a>
							    <a href="http://www.youtube.com/watch?v=GCZrz8siv4Q"><img src="../data/story.jpg"></a>
							</div>
							<h2>
							<i class="icon-trash icon-red icon32"></i>
							<i class="icon-edit icon-red icon32"></i>
							</h2>
							<h4>----------------------------------------------------------------------------------------------------</h4>
							<br/><br/><br/>
						<!-- Album End -->
						
						
						
						</div>
					</div>
				</div>
				
				<div class="row-fluid sortable">
    				<div class="box span12">
						<div class="box-header well">
							<h2><i class="icon-th"></i> Create new Album</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
						<div class="box-content">
							 
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane active" id="blog">
									<form class="form-horizontal" action="/SavePost" method="POST" enctype="multipart/form-data">
									  <fieldset>
										<div class="control-group">
										  <label class="control-label" for="typeahead">Album Name</label>
										  <div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead" name="postTitle" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="typeahead"> Album Caption</label>
										  <div class="controls">
											<input type="text" class="span6 typeahead" id="postType" name="postType" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="fileInput"> Images</label>
										  <div class="controls">
											<input class="input-file uniform_on" id="fileInput" type="file" name="postImage">
										  </div>
										</div>       
	  
										<div class="form-actions">
										  <button type="submit" class="btn btn-primary">Save changes</button>
										  <button type="reset" class="btn">Cancel</button>
										</div>
										
									  </fieldset>
									</form>
								</div>
	
							</div>
						</div>
					</div>
   				</div>
				
			</div>
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
	<script src="galleria/galleria-1.4.2.js"></script>
	<script>
            Galleria.loadTheme('galleria/themes/classic/galleria.classic.min.js');
            Galleria.run('.galleria');
     </script>
</body>
</html>