<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>St Georges | Admin | Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
			
				<div class="sortable row-fluid">
					<a data-rel="tooltip" class="well span3 top-block" href="userManage.jsp?#viewUsers">
						<span class="icon32 icon-red icon-user"></span>
						<div>Total Users</div>
						<div><%=totalUsers %></div>
					</a>
					<a data-rel="tooltip" class="well span3 top-block" href="userManage.jsp?#createUser">
						<span class="icon32 icon-red icon-contacts"></span>
						<div>Add</div>
						<div>New User</div>
					</a>
					<a data-rel="tooltip" class="well span3 top-block" href="postManage.jsp">
						<span class="icon32 icon-red icon-book"></span>
						<div>Total Posts</div>
						<div><%=totalPosts%></div>
					</a>
					<a data-rel="tooltip" class="well span3 top-block" href="postOffice.jsp">
						<span class="icon32 icon-red icon-compose"></span>
						<div>Create</div>
						<div>New Post</div>
					</a>
				</div>
			
				<div class="row-fluid">
					<div class="box span12">
						<div class="box-header well">
							<h2><i class="icon-th"></i> Create Post</h2>
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
										  <label class="control-label" for="typeahead"> Title</label>
										  <div class="controls">
											<input type="text" class="span6 typeahead" id="typeahead" name="postTitle" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="typeahead"> Type</label>
										  <div class="controls">
											<input type="text" class="span6 typeahead" id="postType" name="postType" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="typeahead"> Sub Type</label>
										  <div class="controls">
											<input type="text" class="span6 typeahead" id="postSubType" name="postSubType" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="date01"> Date</label>
										  <div class="controls">
											<input type="text" class="input-xlarge datepicker" id="date01" name="postDate" required>
										  </div>
										</div>
										
										<div class="control-group">
										  <label class="control-label" for="fileInput"> Image</label>
										  <div class="controls">
											<input class="input-file uniform_on" id="fileInput" type="file" name="postImage">
										  </div>
										</div>       
	  
										<div class="control-group">
										  <label class="control-label" for="textarea2"> Content</label>
										  <div class="controls">
											<textarea class="cleditor" id="textarea2" rows="3" name="postContent" required></textarea>
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
				
				<div class="row-fluid">
					<div class="box span12">
						<div class="box-header well">
							<h2><i class="icon-user"></i> Create User</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
							<div class="box-content">
							<form class="form-horizontal" action="/SaveUser" method="post">
							  <fieldset>
								
								<div class="control-group">
									<label class="control-label" for="typeahead">User Email</label>
									<div class="controls">
									  <div class="input-append">
										<input type="text" class="span6" id="appendedInput" name="userEmail" required><span class="add-on">@digiteyez.com</span>
									  </div>
									</div>
								</div>
								
								<div class="control-group">
								  <label class="control-label" for="typeahead">Full Name</label>
								  <div class="controls">
									<input type="text" class="span6 typeahead" id="userName" name="userName" required>
								  </div>
								</div>
								
								<div class="control-group">
								  <label class="control-label" for="typeahead">Password</label>
								  <div class="controls">
									<input type="password" class="span6 typeahead" id="password" name="password" required>
								  </div>
								</div>
	
								<div class="control-group">
								  <label class="control-label" for="fileInput">Image</label>
								  <div class="controls">
									<input class="input-file uniform_on" id="userImage" name="userIamge" type="file">
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
	<script src="js/charisma.js"></script>
	<script src="js/managePosts.js"></script>
	
	<script type="text/javascript">
    $(document).ready(function() {
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
