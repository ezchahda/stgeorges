<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html lang="en">  
<head>  
	<title>St Georges | Admin | Post Office</title>
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
						<a href="#">Forms</a>
					</li>
				</ul>
			</div>
			
    		<div class="row-fluid sortable">
    		<div class="box span12">
					<div class="box-header well">
						<h2><i class="icon-th"></i> Create new Post</h2>
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
			</div><!--/#content.span10-->
		</div><!--/fluid-row-->
				
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.min.js"></script>
	<script src="js/jquery.flot.pie.min.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>
	
	<script type="text/javascript">
    $(document).ready(function() {
        $('#logout_btn').click(function(event) {
            $.post('/Logout',{},function(responseJson){})
            .done(function(responseJson){
                document.location.href="login.html";
            });
        });
    }); 

    function fillBlogType(obj){
		document.getElementById("postSubType").value=obj.value;	
    }
</script>			
</body>
</html>