<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>St Georges | Admin | User Management</title>
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
						<a href="#">User Managenent</a>
					</li>
				</ul>
			</div>
			
			<div class="row-fluid sortable" id="viewUsers">		
				<div class="box span12">
					<div class="box-header well">
						<h2><i class="icon-user"></i> Users</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table id="users" class="table table-striped table-bordered bootstrap-datatable">
						  <thead>
							  <tr>
								  <th>Username</th>
								  <th>Full Name</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
						  </tbody>
					  </table>            
					</div>
				</div>
			</div>
			
			<div class="row-fluid sortable" id="createUser">
				<div class="box span12">
						
						<div class="box-header well">
							<h2><i class="icon-edit"></i> Create New User</h2>
							<div class="box-icon">
								<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							</div>
						</div>
						
						<div class="box-content">
							<form class="form-horizontal" action="/stgeorge/SaveUser" method="post">
							  <fieldset>
								<div class="control-group">
									<label class="control-label" for="typeahead">User Email</label>
									<div class="controls">
										<input type="text" class="span6 typeahead" id="userEmail" name="userEmail" required>
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
		
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Edit User: <span id="editedUser" ></span></h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="/stgeorge/EditUser" method="post" name="editUserForm" id="editUserForm">
				  <fieldset>
					<div class="control-group">
						<label class="control-label" for="typeahead">User Email</label>
						<div class="controls">
						  <div class="input-append">
							<input type="text" class="span3 uneditable-input" id="userEmailEditId" name="userEmailEdit" required >
						  </div>
						</div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">Full Name</label>
					  <div class="controls">
						<input type="text" class="span3 typeahead" id="userNameEditId" name="userNameEdit" required>
					  </div>
					</div>
					
					<div class="alert alert-block " id="closePasswordWarning">
						<h4 class="alert-heading">Be careful!</h4>
						<p>Changing the Password will be directly affected without the user knowledge</p>
					</div>
					<div class="control-group">
					  <label class="control-label" for="typeahead">Password</label>
					  <div class="controls">
						<input type="password" class="span3 typeahead" id="userPasswordEditId" name="userPasswordEdit" required>
					  </div>
					</div>
	
					<div class="control-group">
					  <label class="control-label" for="fileInput">Image</label>
					  <div class="controls">
						<input class="input-file uniform_on" id="userImageEditId" name="userIamgeEdit" type="file">
					  </div>
					</div>          
				  </fieldset>
				</form>   
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal" onClick="resetWarningMessage();">Close</a>
				 <a href="#" class="btn btn-primary" onClick="saveForm()">Save changes</a> 
			</div>
		</div>
		
		<div class="modal hide fade" id="AlertModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Error:</h3>
			</div>
			<div class="modal-body">
				<p>you cannot delete your own user</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
			</div>
		</div>
		
		<div class="modal hide fade" id="AlertUserDelete">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Delete User?</h3>
			</div>
			<div class="modal-body">
				<p><b>Are you sure you want to delete user</b></p>
				<p><b>UserName : </b><span id="userEmailDelete"></span></p>
				<p><b>Full Name: </b><span id="userNameDelete"></span></p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Close</a>
				<a href="#" class="btn btn-primary" onClick="yesDeleteUser()" data-dismiss="modal">Ok Delete</a>
			</div>
		</div>
		
	</div><!--/.fluid-container-->
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
	<script src="js/manageUsers.js">
		
</script>	
</body>
</html>