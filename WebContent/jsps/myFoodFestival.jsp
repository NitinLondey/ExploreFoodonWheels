<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck Profile</title>

  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
		<style>
body {
	font-family: Arial;
}

/* Style the tab */
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}



/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	border: 1px solid #ccc;
	border-top: none;
}

#requesttable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#requesttable td, requesttable th {
	border: 1px solid #ddd;
	padding: 8px;
}



/* #requesttable tr:hover {
	background-color: #ddd;
	color: black;
} */

#requesttable th {
	padding-top: 12px;
	padding-bottom: 12px;
	padding-left: 8px;
	padding-right: 8px;
	text-align: center;
	background-color: #ef6c04;
	color: black;
}

h2 {
    text-align: center;
}


</style>
<%@include file="/jsps/header.jsp" %>


</head>
<body>


 <%@include file="/jsps/navigation.jsp" %>


<%if((request.getAttribute("message")!=null))
      {
    	  %>
      <div class="errorMsg">
              <%= request.getAttribute("message") %>
	</div>
      <%
		request.setAttribute("message", null);
	} %>




<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">My Food Festival</h3>
		
      <div class="easyBox full">
      <div class="row nomargin">
		<table class="table-bordered" id="requesttable">
			<tr>
				<th>Festival Name</th>
				<th>Start Date</th>
				<th>End Date</th>				
				<th>Hours</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>																		
				<th>Zipcode</th>
				<%
					if ( request.getSession().getAttribute("role").equals("truck_owner")) {
				%>
				<th>Status</th>
				<th>Action</th>
				<% } %>
			</tr>

			<c:forEach var="list" items="${list}">
				<tr>
					<td><c:out value="${list.festival_name}" /></td>
					<td><c:out value="${list.startDate}" /></td>
					<td><c:out value="${list.endDate}" /></td>
					<td><c:out value="${list.hours}" /></td>
					<td><c:out value="${list.address}" /></td>
					<td><c:out value="${list.city}" /></td>
					<td><c:out value="${list.state}" /></td>
					<td><c:out value="${list.zip_code}" /></td>
					<%
					if ( request.getSession().getAttribute("role").equals("truck_owner")) {
					%>
					
					<td><c:out value="${list.approved}" /></td>
					<td><input type="button" value="Delete" style="background:red" onclick="location.href='${pageContext.request.contextPath}/deleteFestival.do?eventId=<c:out value="${list.id}"/>'"></td>
				<% } %>
				</tr>
			</c:forEach>
		</table>
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
