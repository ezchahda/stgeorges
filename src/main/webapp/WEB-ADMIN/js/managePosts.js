function deletePost(postId, postType, postTitle, postDate) {
	document.getElementById("postIdDelete").innerHTML = postId;
	document.getElementById("postTypeDelete").innerHTML = postType;
	document.getElementById("postTitleDelete").innerHTML = postTitle;
	document.getElementById("postDateDelete").innerHTML = postDate;
	$('#AlertPostDelete').modal('show');
}

function yesDeletePost() {
	var postId = document.getElementById("postIdDelete").innerHTML;
	$.post('/DeletePost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(function(responseJson) {
		var row = document.getElementById("TR" + postId);
		row.parentNode.removeChild(row);
	});
}

function starThePost(postId) {
	$.post('/StarPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#starOf_' + postId).switchClass("icon-star-off",
						"icon-star-on");
				$('#spanOf_' + postId).attr('onclick',
						'unStarThePost("' + postId + '")');
			});
}

function unStarThePost(postId) {
	$.post('/UnStarPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#starOf_' + postId).switchClass("icon-star-on",
						"icon-star-off");
				$('#spanOf_' + postId).attr('onclick',
						'starThePost("' + postId + '")');
			});
}

function detailThePost(postId) {
	$.post('/DetailPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#detailStarOf_' + postId).switchClass("icon-pin",
						"icon-bookmark");
				$('#detailSpanOf_' + postId).attr('onclick',
						'unDetailThePost("' + postId + '")');
			});
}

function unDetailThePost(postId) {
	$.post('/UnDetailPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#detailStarOf_' + postId).switchClass("icon-bookmark",
						"icon-pin");
				$('#detailSpanOf_' + postId).attr('onclick',
						'detailThePost("' + postId + '")');
			});
}

function pinThePost(postId) {
	$.post('/PinPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#pinStarOf_' + postId).switchClass("icon-bullet-off","icon-bullet-on");
				$('#pinSpanOf_' + postId).attr('onclick','unPinThePost("' + postId + '")');
			});
}

function unPinThePost(postId) {
	$.post('/UnPinPost', {
		postid : postId
	}, function(respopnseJson) {
	}).done(
			function(responseJson) {
				$('#pinStarOf_' + postId).switchClass("icon-bullet-on","icon-bullet-off");
				$('#pinSpanOf_' + postId).attr('onclick','pinThePost("' + postId + '")');
			});
}

function loadPostTable(){
	$.get('/GetPosts',{postId:$postId},function(responseJson){})
    .done(function(responseJson){
    	
    	$('#posts').append('<tbody>');
    	
		$(responseJson).each(function(index, element){
			var postDate = element.postYear+' / '+element.postMonth+' / '+element.postDay;
			
			if(element.postPinned == 0)
				var postPinned 	= '<span class="btn " id="pinSpanOf_'+element.postId+'"  href="#" onClick="pinThePost(\''+element.postId+'\')"><i id="pinStarOf_'+element.postId+'" class="icon32 icon-color icon-bullet-off"></i></span>&nbsp;';
			else
				var postPinned 	= '<span class="btn " id="pinSpanOf_'+element.postId+'"  href="#" onClick="unPinThePost(\''+element.postId+'\')"><i id="pinStarOf_'+element.postId+'" class="icon32 icon-color icon-bullet-on"></i></span>&nbsp;';
			
			
			if(element.postStarred == 0)
				var postStar 	= '<span class="btn " id="spanOf_'+element.postId+'"  href="#" onClick="starThePost(\''+element.postId+'\')"><i id="starOf_'+element.postId+'" class="icon32 icon-color icon-star-off"></i></span>&nbsp;';
			else
				var postStar 	= '<span class="btn " id="spanOf_'+element.postId+'"  href="#" onClick="unStarThePost(\''+element.postId+'\')"><i id="starOf_'+element.postId+'" class="icon32 icon-color icon-star-on"></i></span>&nbsp;';

			
			if(element.postDetailed == 0)
					var postDetail = '<span class="btn " id="detailSpanOf_'+element.postId+'"  href="#" onClick="detailThePost(\''+element.postId+'\')"><i id="detailStarOf_'+element.postId+'" class="icon32 icon-color icon-pin"></i></span>&nbsp;';
				else
					var postDetail = '<span class="btn " id="detailSpanOf_'+element.postId+'"  href="#" onClick="unDetailThePost(\''+element.postId+'\')"><i id="detailStarOf_'+element.postId+'" class="icon32 icon-color icon-bookmark"></i></span>&nbsp;';
					
				
			 $('#posts').append('<tr id="TR'+element.postId+'">'+
				     			'<td class="center"> '+element.postTitle+  ' </td>'+
				     			'<td class="center"> '+element.postType+   ' </td>'+
				     			'<td class="center">'+ element.postSubType+' </td>'+
				     			'<td class="center">'+ postDate +          ' </td>'+
				     			'<td class="center">'
				     			+postStar+postDetail+postPinned+	
				     			'<span class="btn btn-info"   href="#" onClick="editPost(\''+element.postId+'\',\''+element.postType+'\')"><i class="icon-edit icon-white icon32"></i></span>&nbsp;'+
				     			'<span class="btn btn-danger" href="#" onClick="deletePost(\''+element.postId+'\',\''+element.postType+'\',\''+element.postTitle+'\',\''+postDate+'\')"><i class="icon-trash icon-white icon32"></i></span></td></tr>');       
		});
		$('#posts').append('</tbody>');
		$('#posts').DataTable({"sPaginationType": "bootstrap"});
		$('#posts').DataTable({"aaSorting": [[ 3, "desc" ]]});
    });

}

function editPost(postId,postType){
	
	$.get('/GetPost',{postId:postId,postType:postType},function(responseJson){})
    
	.done(function(responseJson){

		$(responseJson).each(function(index, element){  

			document.getElementById('editedPost').innerHTML = "";
			
				$(document).ready(function() {
					  $('#edit_blog_content').cleditor();
				});
				
				$('#myBlogModal').width('auto');
				
				$('#myBlogModal').modal('show');
				
				$("#edit_blog_id").val($("<div>").html(postId).text()).blur();
				
				$("#edit_blog_title").val($("<div>").html(element.postTitle).text()).blur();
				
				$("#edit_blog_type").val($("<div>").html(element.postType).text()).blur();
				
				$("#edit_blog_subtype").val($("<div>").html(element.postSubType).text()).blur();
				
				var postDate = element.postMonth+"/"+element.postDay+"/"+element.postYear;
				
				$("#edit_blog_date").val(postDate).blur();
				
				$('#edit_blog_content').val($("<div>").html((element.postContent)).html()).blur();

				document.getElementById("edit_blog_image_name").innerHTML = element.postImage;
			
		});
    });
}

function saveEditForm(){
		document.getElementById("editPostForm").submit();
}

function closeEditForm(){
	
	$('#editPostForm').trigger("reset");
	
	$('#myBlogModal').modal('hide');
}