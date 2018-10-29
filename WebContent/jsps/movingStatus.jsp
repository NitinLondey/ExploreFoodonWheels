
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck : Moving Status</title>

  
<%@include file="/jsps/header.jsp" %>

</head>
<body>


 <%@include file="/jsps/navigation.jsp" %>


<!-- ******************** -->
<!-- ** FULL WIDTH MAP ** -->




<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">Moving Status</h3>

      <div class="easyBox full">

        <h4>${currentStatus }</h4>
        <p style="color:red; font-weight: 900; font-size:20" >(Caution: In moving status, your truck will not be searched by users)</p>
		<form class="simpleForm" action="${pageContext.request.contextPath}/MovingStatusServlet.do" id = "signup-form">
           <fieldset>
        <div class="row nomargin">
         
            	<p style="color:yellow; font-weight: 900">${msg }</p>
                 <div class="form-group"> 
                	<div class="col-md-5" style="width:400px">
	                <input type="radio" name="movingStatus" value="true"><font size="5">Switch status to <strong>Is Moving</strong></font>  
	                </div>
	                <div class="col-md-5" style="width:400px">
	                <input type="radio" name="movingStatus" value="false"><font size="5">Switch status to <strong>Not Moving</strong></font>
	                 </div>
	             </div>
                
			<input type="hidden" name ="truckName" value="${truckName}"/>
        </div>
        <div class="space25px"></div>
		<div class="row nomargin">
            <input class="btn btn-default" type="button" value="Save Change" onclick="submitForm();">
         
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
window.onload = function(){
	var status = '<%=request.getAttribute("movingStatus")%>';
	
	var boxes = document.getElementsByName("movingStatus");
    if(status=="true"){
    	boxes[0].checked = true;     
    }else{
    	boxes[1].checked = true;
    }
}
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
