
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

      <h3 class="hdr1">Truck Owner Profile</h3>
		
      <div class="easyBox full">

        <h4 class="hdr5"><span>Food Truck Information</span></h4>
		<form class="simpleForm" action="${pageContext.request.contextPath}/EditProfileServlet.do" id = "signup-form" >
           <fieldset>
        <div class="row nomargin">
        	 <p style="color:yellow; font-weight: 1000">${msg }</p>
       		</div>
       	<div class="row nomargin">
          <div class="col-md-5">
                <div class="form-group">
                  <label>Food Truck name: ${user.user_name }</label>
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
                  <label>Address Line 1</label>
                  <input type="text"  class="form-control" name="address1" value="${user.address_line1 }" placeholder="enter your  address 1">
                </div>
                <div class="form-group">
                  <label>Address Line 2</label>
                  <input type="text"  class="form-control" name="address2" value="${user.address_line_2 }" placeholder="enter your  address 2">
                </div>
                <div class="form-group">
                  <label>City</label>
                  <input type="text"  class="form-control" name="city" value="${user.city }" placeholder="enter your  city">
                </div>
                <div class="form-group">
                  <label>State</label>
                  <input type="text"  class="form-control" name="state" value="${user.state }" placeholder="enter your state">
                </div>
               
                <div class="form-group">
                  <label>Phone</label>
                   <input type="tel" placeholder="123-456-7890" value="${user.phone }" class="form-control" name="phone" >
                </div>
          </div>
          
          <div class="col-md-2">
          </div>
          
          <div class="col-md-5">
          
			<div class="form-group">
                  <label>Email: ${user.email}</label>
                </div>
           
                <div class="form-group">
                	<label>Cuisine</label>
	                <input type="checkbox" name="cuisine" value="Mexican">Mexican<br>      
	                <input type="checkbox" name="cuisine" value="Chinese">Chinese<br>      
	                <input type="checkbox" name="cuisine" value="Japanese">Japanese<br> 
	                <input type="checkbox" name="cuisine" value="Thai">Thai<br> 
	                <input type="checkbox" name="cuisine" value="American">American<br> 
	                <input type="checkbox" name="cuisine" value="Fast_food">Fast Food<br> 
	                <input type="checkbox" name="cuisine" value="Indian">Indian<br>  
	             </div>
                
                <div class="form-group">
                	<label>Days</label>
	                <input type="checkbox" name="days" value="Monday">Monday<br>      
	                <input type="checkbox" name="days" value="Tuesday">Tuesday<br>      
	                <input type="checkbox" name="days" value="Wednesday">Wednesday<br> 
	                <input type="checkbox" name="days" value="Thursday">Thursday<br> 
	                <input type="checkbox" name="days" value="Friday">Friday<br> 
	                <input type="checkbox" name="days" value="Saturday">Saturday<br> 
	                <input type="checkbox" name="days" value="Sunday">Sunday<br>  
	             </div>
                <div class="form-group">
                  <label>Week Day Time</label>
                  <input type="text"  class="form-control" name="week_day" value="${truck.weekday_time }" placeholder="enter your weekday timings">
                </div>
                <div class="form-group">
                  <label>Week End Time</label>
                  <input type="text"  class="form-control" name="week_end" value="${truck.weekend_time }" placeholder="enter your weekend timings">
                </div>
                <div class="form-group">
                  <label>Accepted Payments</label>
                  <input type="checkbox" name="payment" value="Cash">Cash<br>      
	                <input type="checkbox" name="payment" value="Debit Card">Debit Card<br>      
	                <input type="checkbox" name="payment" value="Credit Card">Credit Card<br> 
                </div>
                
            
			<input type="hidden" name ="register" value="truck"/>
            

            <input class="btn btn-default" type="button" value="Save Change" onclick="submitForm();">
           
           
           
          </div>
        </div>
        </fieldset>
       </form>
       <div>
           			<p>
						<img id ="imgs" class="menudetails" src="${pageContext.request.contextPath}/uploads/${fileName}"
							/><br>
							
					</p>
					<h6>Upload the image of food truck here!</h6>
					<form class="formdata"
						action="${pageContext.request.contextPath}/ProfileImageUploaderServlet.do"
						method="post" enctype="multipart/form-data">

						
						<input id="hidden" type="hidden" name="cuisinename" value="${list}"/>
						
						<input class="choosefilebtn" id="file" type="file" name="file" size="50" onchange="onSubmitimage(event);"/>
						<input class="submitfilebtn" type="submit" value="Upload File" />
					</form>
           </div>
      </div>
      <!-- / easyBox -->

    </div>
  </div>
</div>


<!-- bg-1 -->


 <%@include file="/jsps/footer.jsp" %>

</body>

<script>
	
function onSubmitimage(event){
	
	   $("#imgs").attr('src',URL.createObjectURL(event.target.files[0])); 
	}
	
	
window.onload = function(){
	var cuisine = '<%=request.getAttribute("cuisine")%>';
	var day = '<%=request.getAttribute("days")%>';
	var payment = '<%=request.getAttribute("payments")%>';
	
	var cuisines = cuisine.split(",");
	var boxes = document.getElementsByName("cuisine");
    for(i=0;i<boxes.length;i++){
        for(j=0;j<cuisines.length;j++){
            if(boxes[i].value == cuisines[j]){
                boxes[i].checked = true;
                break
            }
        }
    }
    
    var days = day.split(",");
	boxes = document.getElementsByName("days");
    for(i=0;i<boxes.length;i++){
        for(j=0;j<days.length;j++){
            if(boxes[i].value == days[j]){
                boxes[i].checked = true;
                break
            }
        }
    }
    
    var payments = payment.split(",");
	boxes = document.getElementsByName("payment");
    for(i=0;i<boxes.length;i++){
        for(j=0;j<payments.length;j++){
            if(boxes[i].value == payments[j]){
                boxes[i].checked = true;
                break
            }
        }
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
