
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck Profile</title>

  
<%@include file="/jsps/header.jsp" %>

</head>
<body>


 <%@include file="/jsps/navigation.jsp" %>


<!-- ******************** -->
<!-- ** FULL WIDTH MAP ** -->




<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">Add Food Festival</h3>
		
      <div class="easyBox full">

        <h4 class="hdr5"><span>Food Festival Information</span></h4>
		<form class="formdata"
					action="${pageContext.request.contextPath}/AddEventsServlet.do"
					method="post" enctype="multipart/form-data" id="imageadd">
           <fieldset>
        
       	<div class="row nomargin">
          <div class="col-md-5">
                <div class="form-group">
                  <label>Food festival name</label>
                  <input type="text"  class="form-control" name="festival_name"  placeholder="enter food festival name">
                </div>
                 <div class="form-group">
                  <label>Start date</label>
                  <input type="date"  class="form-control" name="start_date"  placeholder="enter the start date">
                </div>
                 <div class="form-group">
                  <label>End date</label>
                  <input type="date"  class="form-control" name="end_date"  placeholder="enter the end date">
                </div>
                
                <div class="form-group">
                  <label>Address</label>
                  <input type="text"  class="form-control" name="address"  placeholder="enter the food festival address">
                </div>
                
          	</div>
        
        	<div class="col-md-2">
          	</div>
          
            <div class="col-md-5"> 
            	<div class="form-group">
                  <label>City</label>
                  <input type="text"  class="form-control" name="city" placeholder="enter the city">
                </div>
                <div class="form-group">
                  <label>State</label>
                  <input type="text"  class="form-control" name="state" placeholder="enter the state">
                </div>
                <div class="form-group">
                  <label>Zipcode</label>
                  <input type="text"  class="form-control" name="zipcode" placeholder="enter the Zipcode">
                </div>
                <div class="form-group">
                  <label>Hours</label>
                  <input type="text"  class="form-control" name="hours" placeholder="enter the timing">
                </div>
                
				<input type="hidden" name ="register" value="truck"/>
            	
            </div>
           
        </div>
        <div class="row nomargin">
         
          		
				<!-- <div class="frameImg">
					<img id="imgs" class="menuimages"
						src="">
				</div> -->
				<%-- <h6>Upload a image for your food festival here!</h6>
				
					<input id="hidden" type="hidden" name="eventId" value="${eventId}" />

					<input class="choosefilebtn" id="file" type="file" name="file"
						size="50" onchange="onSubmit(event);" /> --%> 
			  <input class="btn btn-default" type="submit" value="Submit"/>

			
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
	
	function onSubmit(event) {
		$("#imgs").attr('src', URL.createObjectURL(event.target.files[0]));
	}
	function onSubmitimage(event) {
		$("#imgs1").attr('src', URL.createObjectURL(event.target.files[0]));
	}
	function submitForm() {
		form = document.getElementById("imageadd");
		form.submit();
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
