var currentThumb;

$(document).ready(function() {
		    
	        $('#logout_btn').click(function(event) {
	            $.post('/Logout',{},function(responseJson){})
	            .done(function(responseJson){
	                document.location.href="login.html";
	            });
	        });

        var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: 'img/white.png',
            imgRef: ''    
        };
        
        var cropper = $('.imageBox').cropbox(options);

         $('#btnCrop').on('click', function(){
            var img = cropper.getDataURL();
			var imgSrc = options.imgRef;
            saveImage(img,imgSrc);
        });
         
         $('#btnCrop3').on('click', function(){
        	 clearCropper();
         });
         
         $('#btnCrop1').on('click', function(){
             //change box dimension to 800x390
        	 var width=800;
        	 var height=390;
        	 
        	 $('.imageBox').css("width",width*1.1);
        	 $('.imageBox').css("height",height*1.1);
        	 $('.thumbBox').css("width",width);
        	 $('.thumbBox').css("height",height);
        	 
        	 $('.imageBox').css("margin-top",0);
        	 $('.imageBox').css("margin-left",0);
        	 $('.thumbBox').css("margin-top",-1*height*0.5);
        	 $('.thumbBox').css("margin-left",-1*width*0.5);
        	 
        	 $('#btnCrop').css("display","inline");
        	 
         });
         
         $('#btnCrop2').on('click', function(){
        	//change box dimension to 270x200
        	 var width=270;
        	 var height=200;
        	 
        	 $('.imageBox').css("width",width*1.1);
        	 $('.imageBox').css("height",height*1.1);
        	 $('.thumbBox').css("width",width);
        	 $('.thumbBox').css("height",height);
        	 
        	 $('.imageBox').css("margin-top",0);
        	 $('.imageBox').css("margin-left",0);
        	 $('.thumbBox').css("margin-top",-1*height*0.5);
        	 $('.thumbBox').css("margin-left",-1*width*0.5);
        	 
        	 $('#btnCrop').css("display","inline");
         });
         
        
        $('#btnZoomIn').on('click', function(){
            cropper.zoomIn();
        });
        $('#btnZoomOut').on('click', function(){
            cropper.zoomOut();
        });


		$('.thumbnails').on('click','.gallery-edit',function(e){
			$('#cropperContainer').css('display','inline');
			var ref = $(this).parents('.thumbnail').attr('ref');
			var imageThumb = $(this).parents('.grayscale');
			currentThumb = imageThumb;
            options.imgSrc = "/uploads/"+ref;
            options.imgRef = ref;
            cropper = $('.imageBox').cropbox(options);
		});

		
    });

	$('.thumbnails').on('click','.gallery-delete',function(e){
		var ref = $(this).parents('.thumbnail').attr('ref');
		var imageThumb = $(this).parents('.thumbnail');
		currentThumb = imageThumb;
		deleteImage(ref);
		
	});

	function deleteImage(ref){
		document.getElementById("imageDelete").innerHTML  = ref;
		$('#AlertImageDelete').modal('show');
	}

	function yesDeleteImage(){
		var imageRef = document.getElementById("imageDelete").innerHTML;

		$.ajax({
               type: "POST",
               url: "DeleteImage.jsp?imageRef="+imageRef,
               success: function(msg){
                   if(msg == 1){
					currentThumb.fadeOut();
					currentThumb = null;
                   }
                   else
					return;                       
               }
            });
		//$.post('/DeleteImage',{imageRef:imageRef}, function(respopnseJson){});
	}

	function noDeleteImage(){
		return;
	}

	function saveImage(img,imgSrc){
		$.ajax({
            type: "POST",
            url: "SaveEditImage.jsp?imgSrc="+imgSrc,
            data: {
                encodeimg: img
            },
            success: function(msg){
            		document.getElementById('thumb-'+imgSrc).src = "/uploads/"+imgSrc+"?random="+new Date().getTime();;
					currentThumb = null;
					clearCropper();
            }
         });
	}
	
	function clearCropper(){
		var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: 'img/white.png',
            imgRef: ''    
        };
        var cropper = $('.imageBox').cropbox(options);
        $('#btnCrop').css('display','none');
        $('#cropperContainer').css('display','none');
	}
	