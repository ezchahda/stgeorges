var $username="";

		$(document).ready(loadUserTable());
		
		function loadUserTable(){
			$.get('/stgeorge/GetUser',{username:$username},function(responseJson){})
		    .done(function(responseJson){
			
				$(responseJson).each(function(index, element){  
				     $('#users').append('<tr id="TR'+element.username+'"><td class="center"> '+element.username+' </td> <td class="center"> '+element.fullname+' </td><td class="center">'+ 
						     			'<a class="btn btn-info" href="#" onClick="editUser(\''+element.username+'\',\''+element.fullname+'\',\''+element.password+'\')"><i class="icon-edit icon-white"></i></a>&nbsp;<a class="btn btn-danger" href="#" onClick="deleteUser(\''+element.username+'\',\''+element.fullname+'\')"  ><i class="icon-trash icon-white"></i></a></td></tr>');       
				});
		    });
		}

		function editUser(userEmail, userName,  userPassword){
			$('#myModal').modal('show');
			document.getElementById('editedUser').innerHTML 	= userName;
			document.getElementById('userNameEditId').value 	= userName;
			document.getElementById('userEmailEditId').value	= userEmail;
			document.getElementById('userPasswordEditId').value	= userPassword;
			var passwordWarningMessage = document.getElementById("closePasswordWarning");
			setTimeout(function(){passwordWarningMessage.style.display="none";},4000);
		}

		function resetWarningMessage(){
			var passwordWarningMessage = document.getElementById("closePasswordWarning");
			passwordWarningMessage.style.display="block";
		}

		function errorUserDelete(){
			$('#AlertModal').modal('show');
		}
		
	    $(document).ready(function() {
	        $('#logout_btn').click(function(event) {
	            $.post('/stgeorge/Logout',{},function(responseJson){})
	            .done(function(responseJson){
	                document.location.href="login.html";
	            });
	        });
	    });

	    function saveUser(){
				$.post('/stgeorge/SaveUser',{username:$username},function(responseJson){})
			    .done(function(responseJson){
			    	refreshUserTable(responseJson);
			});
	    }

		function deleteUser(userName, userFullName){
			var loggedInUser = '<%=userEmail%>';
			
			if(loggedInUser == userName){
				errorUserDelete();
				return 0;
			}

			document.getElementById("userEmailDelete").innerHTML = userName;
			document.getElementById("userNameDelete").innerHTML  = userFullName;

			$('#AlertUserDelete').modal('show');
		}

		function yesDeleteUser(userName, userFullName){
			var userName = document.getElementById("userEmailDelete").innerHTML;
			var userFullName = document.getElementById("userNameDelete").innerHTML;

			okDeleteUser(userName, userFullName);
		}
	    
	    function okDeleteUser(userName, userFullName){

			$.post('/stgeorge/DeleteUser',{username:userName}, function(respopnseJson){});
			var row = document.getElementById("TR"+userName);
			row.parentNode.removeChild(row);
		}

		function refreshUserTable(responseJson){
			$(responseJson).each(function(index, element){  
			     $('#users').append('<tr id="TR'+element.username+'"><td class="center"> '+element.username+' </td> <td class="center"> '+element.fullname+' </td><td class="center">'+ 
					     			'<a class="btn btn-info" href="#" onClick="editUser(\''+element.fullname+'\')"><i class="icon-edit icon-white"></i></a>&nbsp;<a class="btn btn-danger" href="#"><i class="icon-trash icon-white"></i></a></td></tr>');       
			});
		}

		function saveForm(){
			document.getElementById("editUserForm").submit();
		}