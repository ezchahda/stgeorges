jQuery(document).ready(function($) {

	'use strict';

		$('.flexslider').flexslider({
		    animation: "fade",
		    controlNav: false,
		    prevText: "&larr;",
		    nextText: "&rarr;"
		});


		$('.toggle-menu').click(function(){
	        $('.menu-responsive').slideToggle();
	        return false;
	    });

		$("#footerContent").load("footer.html");
		
		$("#headerContent").load("header.html", function(){
			var liToActivate = window.location.href.slice(window.location.href.indexOf('/WEB-MAIN/') + 10);
			if(liToActivate != null && liToActivate.split(".html")){
				liToActivate = liToActivate.split(".html")[0];
				document.getElementById('li_'+liToActivate).className="active";
				document.getElementById('li_mob_'+liToActivate).className="active";
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