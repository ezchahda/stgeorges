function loadPosts(filterQuery){
	$.get('/GetPost',{postId:$postId,postType:'Blog',postSubType:encodeURIComponent(filterQuery)},function(responseJson){})
		.done(function(responseJson){
			BigJSON = responseJson;
			loadJsonIntoContainer(responseJson);
			var scrollToId = window.location.href;
			if(scrollToId.indexOf("?")>0){
				scrollToId = scrollToId.split("?")[1];
				$('html, body').animate({
					scrollTop: $("#"+scrollToId).offset().top
				}, 1000);
			}
			var elementParent = document.getElementById("loadDiv");
			elementParent.style.display="none";
		});
}
		
function loadJsonIntoContainer(jsonObj){
	
	$(jsonObj).each(function(index, element){
	
		var rowDiv   = "";
		var hasImage = true;
		
		if(element.postImage == null || element.postImage == "null") 
			hasImage = false; 
		
		rowDiv = 	'<div class="row" id="'+element.postId+'">'+
						'<div class="col-md-12 blog-box">'+
				         	'<div class="widget-item">'+
				         		'<div class="post-small">'+
				         			'<div class="post-date">'+
				         				'<span class="time">'+element.postDay+'</span>'+
					                    '<span>'+getArabicMonth(element.postMonth)+'</span>'+
					                '</div>'+
					                '<div class="post-content">'+
					                	'<ul class="social-icons visible-lg" style="text-align: left;">'+
					                    	'<li>Share<a href="#" class="fa fa-facebook" onClick="FBShare(\''+element.postId+'\',\''+element.postTitle+'\',\''+element.postSubType+'\',\''+element.postImage+'\')"></a></li>'+
								            '<li>&nbsp;</li>'+
								        '</ul>'+
					                    '<h4 class="visible-lg blog-title-lg"><a href="#">'+element.postTitle+'</a></h4>'+
					                    '<h4 class="visible-sm visible-xs visible-md"><a href="#">'+element.postTitle+'</a></h4>'+
					                    '<h3 class="blog-type-title">'+element.postSubType+'</h3>'+
					                '</div>'+
				                '</div>';
			                            
		if(hasImage){
			rowDiv += 			'<div class="sample-thumb">'+
									'<img src="/uploads/'+element.postImage+'" />'+
								'</div>';
		}
							
		rowDiv +=				'<div id="contentOf'+element.postId+'">'+
									'<p class="consult-content">'+
								    	element.postContent+
									'</p>'+
								'</div>'+
										
								'<ul class="social-icons visible-xs visible-sm visible-md" style="text-align: left;">'+
									'<li>Share<a href="#" class="fa fa-facebook" onClick="FBShare(\''+element.postId+'\',\''+element.postTitle+'\',\''+element.postSubType+'\',\''+element.postImage+'\')"></a></li>'+
								'</ul>'+
								        
							'</div>'+
						'</div>'+
						'<div class="visible-lg visible-md col-md-1 separator"></div>'+
					'</div>';
		
		if(index %2 == 0)
			$('#leftContainer').append(rowDiv);
		else
			$('#rightContainer').append(rowDiv);
	
	});//end of each
}
		
function FBShare(id,title,subtitle,imageSource){
	var content=(strip(document.getElementById("contentOf"+id).innerHTML)).substring(0,3000);
	FB.ui(
			{
				method: 'feed',
				name: title,
				link: 'http://www.stgeorgebatroun.com/WEB-MAIN/Blog.html?'+id,
				picture: 'http://www.stgeorgebatroun.com/uploads/'+imageSource,
				caption: subtitle,
				description: content,
				message: ''
			}
	);
}

function  strip(html) {
	var tmp = document.createElement("DIV");
	tmp.innerHTML = html;
	return tmp.textContent || tmp.innerText || "";
}
			  

function filterOn(obj){

	var oldObj = document.getElementsByClassName('submit-filter-selected')[0];
	var filterVal="";
	if(oldObj != null)
		oldObj.className = 'submit-filter';
	
	obj.className = "submit-filter-selected";
	
	if(obj.attributes[0]!=null && obj.attributes[0]!=""){
		filterVal=obj.attributes[0].nodeValue;
	}
	
	document.getElementById("leftContainer").innerHTML="";
	document.getElementById("rightContainer").innerHTML="";
	loadPosts(filterVal);
	
}