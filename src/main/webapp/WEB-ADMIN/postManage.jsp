<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;">
	<title>St Georges | Admin | Post Management</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- The styles -->
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
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
	<link href='css/jquery.dataTables.css' rel='stylesheet'>
	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
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
						<a href="#">Post Managenent</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable" id="viewPosts">		
				<div class="box span12">
					<div class="box-header well">
						<h2><i class="icon-th"></i> Posts</h2>
						<div class="box-icon">
							<!--<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>-->
						</div>
					</div>
					<div class="box-content">
						<table id="posts" class="table table-bordered stripe">
						  <thead>
							  <tr>
								  <th>Post Title</th>
								  <th>Post Type</th>
								  <th>Post Sub-Type</th>
								  <th>Post Date</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
					  </table>            
					</div>
				</div>
			</div>
			
			</div>
		</div>
		
		<hr>

		<div class="modal hide fade" id="myBlogModal" >
			<div class="modal-header">
				<button type="button" onclick="closeEditForm()" class="close" data-dismiss="modal">X</button>
				<h3>Edit Post: <span id="editedPost" ></span></h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="editPostForm" action="/SaveEditPost" method="POST" enctype="multipart/form-data">
								  <fieldset>
								  	<div class="control-group">
									  <label class="control-label">Post Id</label>
									  <div class="controls">
										<input type="text"  class="span6" id="edit_blog_id" name="postId" readonly="readonly" required >
									  </div>
									</div>
									<div class="control-group">
									  <label class="control-label">Post Title</label>
									  <div class="controls">
										<input type="text"  class="span6" id="edit_blog_title" name="postTitle" required>
									  </div>
									</div>
									<div class="control-group">
									  <label class="control-label">Post Type</label>
									  <div class="controls">
										<input type="text" class="span6" id="edit_blog_type" name="postType" required>
									  </div>
									</div>
									<div class="control-group">
									  <label class="control-label">Post Sub Type</label>
									  <div class="controls">
										<input type="text" class="span6" id="edit_blog_subtype" name="postSubType" required>
									  </div>
									</div>
									<div class="control-group">
									  <label class="control-label" for="date01">Post Date</label>
									  <div class="controls">
										<input type="text" class="input-xlarge datepicker" id="edit_blog_date" name="postDate" required>
									  </div>
									</div>
									
									<div class="control-group">
									  <label class="control-label" for="fileInput">Current Image</label>
									  <div class="controls">
										<span  id="edit_blog_image_name"></span>
									  </div>
									</div>
									
									<div class="control-group">
									  <label class="control-label" for="fileInput">Change Image</label>
									  <div class="controls">
										<input class="input-file uniform_on" id="edit_blog_image" type="file" name="postImage">
									  </div>
									</div> 
									          
									<div class="control-group">
									  <label class="control-label" for="textarea2">Post Content</label>
									  <div class="controls">
										<textarea class="cleditor" id="edit_blog_content" rows="10" name="postContent" required></textarea>
									  </div>
									</div>
								  </fieldset>
								</form>
			</div>
			<div class="modal-footer">
			<a href="#" onclick="closeEditForm()" class="btn" data-dismiss="modal">Close</a>
			<a href="#" onclick="saveEditForm()" class="btn btn-primary">Save changes</a>
			</div>
		</div>
		
		<div class="modal hide fade" id="AlertPostDelete">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Delete Post?</h3>
			</div>
			<div class="modal-body">
				
				<p><b>Are you sure you want to remove post</b></p>
				<p><b>Post Id : </b><span id="postIdDelete"></span></p>
				<p><b>Post Type : </b><span id="postTypeDelete"></span></p>
				<p><b>Post Title: </b><span id="postTitleDelete"></span></p>
				<p><b>Post Date: </b><span id="postDateDelete"></span></p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary" onClick="yesDeletePost()" data-dismiss="modal">Ok Delete</a>
			</div>
		</div>
		
	</div><!--/.fluid-container-->

	<!-- jQuery -->
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

	<!-- chart libraries start -->
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.min.js"></script>
	<script src="js/jquery.flot.pie.min.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

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
	<script src="js/managePosts.js"></script>
	<script src="js/charisma.js"></script>

	
	<script type="text/javascript">
		var $postId="";

		$(document).ready( function(){

			loadPostTable();
		
	        $('#logout_btn').click(function(event) {
	            $.post('/Logout',{},function(responseJson){})
	            .done(function(responseJson){
	                document.location.href="login.html";
	            });
	        });

	    });
</script>	
</body>
</html>