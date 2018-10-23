<%@ page language="java" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="ISO-8859-1">
<title>Sign in with Google</title>
<script src="https://apis.google.com/js/platform.js" async defer></script>
 <meta name="google-signin-client_id" content="722563070549-vj8orqkopliavr3ja3sjum674nf7v7mp.apps.googleusercontent.com">
</head>
<body>
<% 
		String UserType = request.getParameter("UserType");
	%>
	
	<script>
	var userType = "<%=UserType %>";
	</script>
	<div>Sign up with Gmail</div>
<div class="g-signin2" data-onsuccess="onSignIn" id="myP"></div>
      <img id="myImg"><br>
      <p id="name"></p>
      <div id="status">
   </div>
   <script type="text/javascript">
      function onSignIn(googleUser) {
      // window.location.href='success.jsp';
      var profile = googleUser.getBasicProfile();
      
      var imagurl=profile.getImageUrl();
      var name=profile.getName();
      var first_name = profile.getGivenName();
      var last_name = profile.getFamilyName();
      var email=profile.getEmail();
      document.getElementById("myP").style.visibility = "hidden"; 
      window.location.href = '/ExploreFoodonWheels/FbSignup?user_name='+ name + '&user_first_name='
  	      	+ first_name +'&user_last_name='+ last_name+'&user_email='+ email + '&type=Gmail'+'&user_type='+userType;
     signOut();
      auth2.disconnect();
   }
   </script>
  
   
 
   <script>
   function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	      console.log('User signed out.');
	    });
	    auth2.disconnect();
	  }
      </script>
</body>
</html>