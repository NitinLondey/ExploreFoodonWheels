
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck  : Sign up</title>

  
<%@include file="/jsps/header.jsp" %>

</head>
<body>


 <%@include file="/jsps/navigation.jsp" %>


<!-- ******************** -->
<!-- ** FULL WIDTH MAP ** -->




<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">Sign up</h3>

      <div class="easyBox full">

        <h4 class="hdr5"><span>Sign up User</span></h4>
		<form class="simpleForm" action="${pageContext.request.contextPath}/Signup.do" id = "signup-form">
           <fieldset>
        <div class="row nomargin">
          <div class="col-md-5">
            <h4 class="hdr2 special">User Information</h4>

            <p style="color:yellow; font-weight: 900">${FBname } <br/> ${msg }</p>
                <div class="form-group">
                  <label>User name</label>
                  <input type="text" id="user_name"  class="form-control" name="user_name" value="${user.user_name }" placeholder="enter user name">
                </div>
                <div id="message_name">
				  <p id="isRepeat" class="invalid">User name must not be repeat</p>
				</div>
                 <div class="form-group">
                  <label>First name</label>
                  <input type="text"  class="form-control" name="first_name" value="${user.firstname }" placeholder="enter your first name">
                </div>
                 <div class="form-group">
                  <label>Last name</label>
                  <input type="text"  class="form-control" name="last_name" value="${user.lastname }" placeholder="enter your last name">
                </div>
                <div class="form-group">
                  <label>Password</label>
                  <input type="password" id ="psw" class="form-control" name="password" placeholder="enter your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" >
                </div>
                <div id="message">
				  <h6 style="color: green;">Password must contain the following:</h6>
				  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
				  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
				  <p id="number" class="invalid">A <b>number</b></p>
				  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
				</div>
                <div class="form-group">
                  <label>Verify Password</label>
                  <input type="password"  class="form-control" name="password" placeholder="confirm password" id = "confirm_password">
                </div>
                
                <div class="form-group">
                  <label>Address Line 1</label>
                  <input type="text"  class="form-control" name="address1" placeholder="enter your  address 1">
                </div>
                
                
              

          </div>
          <div class="col-md-2">
          </div>
          <div class="col-md-5">
            	<div class="space50px" style="height:35px"></div>
            	<div>
					<jsp:include page="/jsps/fbsignup.jsp">
					<jsp:param value="User" name="UserType"/>
					</jsp:include>
				</div>
				<div>
					<jsp:include page="/jsps/gmailSignup.jsp">
					<jsp:param value="User" name="UserType"/>
					</jsp:include>
				</div>
				
				<div class="space25px" style="height:20px"></div>
				<div class="form-group">
                  <label>Address Line 2</label>
                  <input type="text"  class="form-control" name="address2" placeholder="enter your  address 2">
                </div>
		
           
                <div class="form-group">
                  <label>City</label>
                  <input type="text"  class="form-control" name="city" placeholder="enter your  city">
                </div>
                <div class="form-group">
                  <label>State</label>
                  <input type="text"  class="form-control" name="state" placeholder="enter your state">
                </div>
                 <div class="form-group">
                  <label>Zipcode</label>
                  <input type="text"  class="form-control" name="zipcode" placeholder="enter your Zipcode">
                </div>
                <div class="form-group">
                  <label>Phone</label>
                   <input type="tel" placeholder="1234567890"
             class="form-control" name="phone" placeholder="enter your phone">
                </div>
           	<div class="form-group">
                  <label>Email</label>
                   <input type="email"  class="form-control" name="email" value="${user.email }" placeholder="enter your email">
                </div>
           
               

            
			<input type="hidden" name ="register" value="user"/>
            

            <input class="btn btn-default" type="button" value="Submit" onclick="submitForm();">
			<div>
				Already have a profile?  <a href="${pageContext.request.contextPath}/jsps/login.jsp">Login</a>  
			</div>
          </div>
        </div>
        </fieldset>
       </form>
      </div>
      <!-- / easyBox -->

    </div>
  </div>
</div>
  
<!-- bg-1 -->


 <%@include file="/jsps/footer.jsp" %>

</body>

<script>


function submitForm(){
	document.getElementById("signup-form").submit();
}


//name repeat?
var uerName = document.getElementById("user_name");
var isRepeat = document.getElementById("isRepeat");
function getXHR(){
	var xmlHttp;
	try {
		xmlHttp=new XMLHttpRequest();
	}catch(e)
	{
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e)
		{
			try{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e)
			{
				alert("你的浏览器不支持ajax");
				return false;
			}
			
		}
		
	}
	return xmlHttp;
}

uerName.onfocus = function() { 
	document.getElementById("message_name").style.display = "none";
}
uerName.onblur = function() {
	var xhr=getXHR();
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4)
			{
			if(xhr.status==200)
				{
				/* alert(xhr.responseText); */
				if(xhr.responseText!="exist"){
					document.getElementById("message_name").style.display = "none";
				}else{
					document.getElementById("message_name").style.display = "block";
				}

				}
			}
	}
	xhr.open("POST","${pageContext.request.contextPath}/AjaxServlet.do"+"?user_name="+uerName.value);
	xhr.send();

	
}
uerName.onkeyup = function() {
	  if(uerName.value=="xxx") {  
		  isRepeat.classList.remove("invalid");
		  isRepeat.classList.add("valid");
	  } else {
		  isRepeat.classList.remove("valid");
		  isRepeat.classList.add("invalid");
	  }
}
//pwd
var myInput = document.getElementById("psw");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myInput.onfocus = function() {
    document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myInput.onblur = function() {
    document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
  // Validate lowercase letters
  var lowerCaseLetters = /[a-z]/g;
  if(myInput.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
  } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
  }
  
  // Validate capital letters
  var upperCaseLetters = /[A-Z]/g;
  if(myInput.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
  } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
  }

  // Validate numbers
  var numbers = /[0-9]/g;
  if(myInput.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
  } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
  }
  
  // Validate length
  if(myInput.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
  } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
  }
}

var password = myInput;
confirm_password = document.getElementById("confirm_password");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
</script>
<style>
#message {
    display:none;
    background: #f1f1f1;
    color: #000;
    position: relative;
    padding: 20px;
    margin-top: 10px;
}

#message p {
    padding: 10px 35px;
    font-size: 18px;
}

#message_name {
    display:none;
    background: #f1f1f1;
    color: #000;
    position: relative;
    padding: 20px;
    margin-top: 10px;
}

#message_name p {
    padding: 10px 35px;
    font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
    color: green;
}

.valid:before {
    position: relative;
    left: -35px;
    content: â;
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
    color: red;
}

.invalid:before {
    position: relative;
    left: -35px;
    content: â;
}
</style>
</html>
