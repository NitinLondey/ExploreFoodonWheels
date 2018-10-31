
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>User Profile</title>

  
<%@include file="/jsps/header.jsp" %>

</head>
<body>


 <%@include file="/jsps/navigation.jsp" %>


<!-- ******************** -->
<!-- ** FULL WIDTH MAP ** -->




<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">User Profile</h3>

      <div class="easyBox full">

        <h4 class="hdr5"><span>User Information</span></h4>
		<form class="simpleForm" action="${pageContext.request.contextPath}/EditProfileServlet.do" id = "signup-form">
           <fieldset>
        <div class="row nomargin">
        	<p style="color:yellow; font-weight: 1000">${msg }</p>
       		</div>
       		
       	<div class="row nomargin">
          <div class="col-md-5">
                <div class="form-group">
                  <label>User name: ${user.user_name }</label>
                </div>
                
                 <div class="form-group">
                  <label>First name</label>
                  <input type="text" class="form-control" name="first_name" value="${user.firstname }" placeholder="enter your first name">
                </div>
                 <div class="form-group">
                  <label>Last name</label>
                  <input type="text" class="form-control" name="last_name" value="${user.lastname }" placeholder="enter your last name">
                </div>
                <div class="form-group">
                  <label>Address Line 1</label>
                  <input type="text"  class="form-control" name="address1" value="${user.address_line1 }" placeholder="enter your  address 1">
                </div>
                <div class="form-group">
                  <label>Address Line 2</label>
                  <input type="text"  class="form-control" name="address2" value="${user.address_line_2 }" placeholder="enter your  address 2">
                </div>
                
                
              

          </div>
          <div class="col-md-2">
          </div>
          <div class="col-md-5">
          		<div class="form-group">
                  <label>Email: ${user.email}</label>
                </div>
            	<div class="form-group">
                  <label>City</label>
                  <input type="text" class="form-control" name="city" value="${user.city }" placeholder="enter your  city">
                </div>
                <div class="form-group">
                  <label>State</label>
                  <input type="text" class="form-control" name="state"  value="${user.state }" placeholder="enter your state">
                </div>
                 <div class="form-group">
                  <label>Zipcode</label>
                  <input type="text" class="form-control" name="zipcode" value="${user.zipcode }" placeholder="enter your Zipcode">
                </div>
                <div class="form-group">
                  <label>Phone</label>
                   <input type="tel" placeholder="1234567890"
            required class="form-control" name="phone" value="${user.phone }" placeholder="enter your phone">
                </div>
           
               

            
			<input type="hidden" name ="register" value="user"/>
            

            <input class="btn btn-default" type="button" value="Save Change" onclick="submitForm();">
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

/* Add a green text color and a checkmark when the requirements are right */
.valid {
    color: green;
}

.valid:before {
    position: relative;
    left: -35px;
    content: ✔;
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
    color: red;
}

.invalid:before {
    position: relative;
    left: -35px;
    content: ✖;
}
</style>
</html>
