<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head> 
<meta charset="UTF-8">
</head>


<body>
	<% 
		String UserType = request.getParameter("UserType");
	%>
	
	<script>
	var userType = "<%=UserType %>";

  	// This is called with the results from from FB.getLoginStatus().
  	function statusChangeCallback(response) {
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    	if (response.status === 'connected') {
      // Logged into your app and Facebook.
      	checkAccountStatus(userType);
   	 } else if (response.status === 'not_authorized') {
	      // The person is logged into Facebook, but not your app.
	      document.getElementById('status').innerHTML = 'Sign up with Facebook ';
	    } else {
	      // The person is not logged into Facebook, so we're not sure if
	      // they are logged into this app or not.
	      document.getElementById('status').innerHTML = 'Sign up with Facebook ';
	    }
	  }
	  // This function is called when someone finishes with the Login
	  // Button.  See the onlogin handler attached to it in the sample
	  // code below.
	  function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	      statusChangeCallback(response);
	    });
	  }
	  
	  function logout(){ 
	   	FB.logout(function(response) {
  		// user is now logged out
		});
		}
		
	  window.fbAsyncInit = function() {
	  FB.init({ 
	    appId      : '310514082866121',
	    cookie     : true,  // enable cookies to allow the server to access 
	                        // the session
	    xfbml      : true,  // parse social plugins on this page
	    version    : 'v2.2' // use version 2.2
	  });
	  // Now that we've initialized the JavaScript SDK, we call 
	  // FB.getLoginStatus().  This function gets the state of the
	  // person visiting this page and can return one of three states to
	  // the callback you provide.  They can be:
	  //
	  // 1. Logged into your app ('connected')
	  // 2. Logged into Facebook, but not your app ('not_authorized')
	  // 3. Not logged into Facebook and can't tell if they are logged into
	  //    your app or not.
	  //
	  // These three cases are handled in the callback function.

	  FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
	  });
	  };
	  // Load the SDK asynchronously
	  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "//connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk')); 
	
	  // Here we run a very simple test of the Graph API after login is
	  // successful.  See statusChangeCallback() for when this call is made.
	  function checkAccountStatus(userType) { 
	    FB.api('/me?fields=name,first_name,last_name,email', function(response) { 
	     	window.location.href = '/ExploreFoodonWheels/FbSignup?user_name='+ response.name + '&user_first_name='
	      	+ response.first_name +'&user_last_name='+ response.last_name+'&user_email='+ response.email +'&type=Fb'+'&user_type='+userType;
	      	logout();
	    });
	  }  
	
	 window.addEventListener("beforeunload", logout());
	</script> 
		<!--
		  Below we include the Login Button social plugin. This button uses
		  the JavaScript SDK to present a graphical Login button that triggers
		  the FB.login() function when clicked.
		-->
		<div id="status">
		</div> 
		<fb:login-button auto_logout_link="true" size="large" scope="public_profile,email" onlogin="checkLoginState();">
		</fb:login-button>
		
		 <script type="text/javascript">
		</script>
	</body>
</html> 