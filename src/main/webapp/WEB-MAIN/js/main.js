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
				
				li = document.getElementById('li_mob_nav_'+liToActivate);
				li.firstChild.style.color= "green";
				
			}
			$('.toggle-menu').click(function(){
				$('.menu-responsive').slideToggle();
			});
			
////here starts settings for the desktop top menu fixed////
			
			var secondaryNav 		= $('.cd-secondary-nav'),
			secondaryNavTopPosition = secondaryNav.offset().top,
			contentSections 		= $('.cd-section');
			$(window).on('scroll', function(){
			if($(window).scrollTop() > secondaryNavTopPosition ) {
				secondaryNav.addClass('is-fixed');
				$('.cd-main-content').addClass('has-top-margin');	
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
			
////here starts settings for the mobile bottom menu button//// 
			
			var offset = 150;

			var navigationContainer = $('#cd-nav'),
				mainNavigation = navigationContainer.find('#cd-main-nav ul');

			checkMenu();
			$(window).scroll(function(){
				checkMenu();
			});

			$('.cd-nav-trigger').on('click', function(){
				$(this).toggleClass('menu-is-open');
				mainNavigation.off('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend').toggleClass('is-visible');

			});

			function checkMenu() {
				if( $(window).scrollTop() > offset && !navigationContainer.hasClass('is-fixed')) {
					if($(window).width()<1000)
						navigationContainer.css("display","inline");
					
					navigationContainer.addClass('is-fixed').find('.cd-nav-trigger').one('webkitAnimationEnd oanimationend msAnimationEnd animationend', function(){
						mainNavigation.addClass('has-transitions');
					});
				} else if ($(window).scrollTop() <= offset) {
					if( mainNavigation.hasClass('is-visible')  && !$('html').hasClass('no-csstransitions') ) {
						mainNavigation.addClass('is-hidden').one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
							mainNavigation.removeClass('is-visible is-hidden has-transitions');
							navigationContainer.removeClass('is-fixed');
							$('.cd-nav-trigger').removeClass('menu-is-open');
							navigationContainer.css("display","none");
						});
					} else if( mainNavigation.hasClass('is-visible')  && $('html').hasClass('no-csstransitions') ) {
							mainNavigation.removeClass('is-visible has-transitions');
							navigationContainer.removeClass('is-fixed');
							navigationContainer.css("display","none");
							$('.cd-nav-trigger').removeClass('menu-is-open');
					} else {
						navigationContainer.removeClass('is-fixed');
						navigationContainer.css("display","none");
						mainNavigation.removeClass('has-transitions');
					}
				}
			}
			
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