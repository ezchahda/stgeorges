jQuery(document).ready(function($) {

	'use strict';

		$('.flexslider').flexslider({
		    animation: "fade",
		    controlNav: false,
		    prevText: "&larr;",
		    nextText: "&rarr;"
		});



		$("#footerContent").load("footer.html");
		
		$("#headerContent").load("header.html", function(){
			
			var liToActivate = window.location.href.slice(window.location.href.indexOf('/WEB-MAIN/') + 10);
			
			if(liToActivate != null && liToActivate.split(".html")){
				liToActivate = liToActivate.split(".html")[0];
				
				var li = document.getElementById('li_'+liToActivate);
				li.className= li.className+" active";
				
				li = document.getElementById('li_mob_'+liToActivate);
				li.className= li.className+" active";
				
			}
			$('.toggle-menu').click(function(){
				$('.menu-responsive').slideToggle();
			});
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			var secondaryNav 		= $('.cd-secondary-nav'),
			secondaryNavTopPosition = secondaryNav.offset().top,
			contentSections 		= $('.cd-section');
		
			$(window).on('scroll', function(){
			
			//on desktop - fix secondary navigation on scrolling
			if($(window).scrollTop() > secondaryNavTopPosition ) {
				//fix secondary navigation
				secondaryNav.addClass('is-fixed');
				//push the .cd-main-content giving it a top-margin
				$('.cd-main-content').addClass('has-top-margin');	
				//on Firefox CSS transition/animation fails when parent element changes position attribute
				//so we to change secondary navigation childrens attributes after having changed its position value
				setTimeout(function() {
		            secondaryNav.addClass('animate-children');
		            $('#cd-logo').addClass('slide-in');
					$('.cd-btn').addClass('slide-in');
		        }, 50);
			} else {
				secondaryNav.removeClass('is-fixed');
				$('.cd-main-content').removeClass('has-top-margin');
				setTimeout(function() {
		            secondaryNav.removeClass('animate-children');
		            $('#cd-logo').removeClass('slide-in');
					$('.cd-btn').removeClass('slide-in');
		        }, 50);
			}

//on desktop - update the active link in the secondary fixed navigation
			updateSecondaryNavigation();
			});

			function updateSecondaryNavigation() {
				contentSections.each(function(){
					var actual = $(this),
						actualHeight = actual.height() + parseInt(actual.css('paddingTop').replace('px', '')) + parseInt(actual.css('paddingBottom').replace('px', '')),
						actualAnchor = secondaryNav.find('a[href="#'+actual.attr('id')+'"]');
					if ( ( actual.offset().top - secondaryNav.height() <= $(window).scrollTop() ) && ( actual.offset().top +  actualHeight - secondaryNav.height() > $(window).scrollTop() ) ) {
						actualAnchor.addClass('active');
					}else {
						actualAnchor.removeClass('active');
					}
			});
			}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		});
});

function getArabicMonth(monthNumber){
		switch(monthNumber){
		case 1:
			return "كانون الثاني";
		case 2:
			return "شباط";
		case 3:
			return "آذار";
		case 4:
			return "نيسان";
		case 5:
			return "أيار";
		case 6:
			return "حزيران";
		case 7:
			return "تموز";
		case 8:
			return "آب";
		case 9:
			return "أيلول";
		case 10:
			return "تشرين الأول";
		case 11:
			return "تشرين الثاني";
		case 12:
			return "كانون الأول";
		}
}