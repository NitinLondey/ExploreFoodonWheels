
<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck  : Login</title>

  
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

      <h3 class="hdr1">Login</h3>

      <div class="easyBox full">

        
		<form class="simpleForm" action="${pageContext.request.contextPath}/Login.do" method="post">
           <fieldset>
        <div class="row nomargin">
          <div class="col-md-12">
            <h4 class="hdr2 special">Login</h4>

            
                <div class="form-group">
                  <label>User name</label>
                  <input type="text" required class="form-control" name="user_name" placeholder="enter food truck name">
                </div>
               
                <div class="form-group">
                  <label>Password</label>
                  <input type="password" id ="psw" class="form-control" name="password" placeholder="enter your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
                </div>
                <input class="btn btn-default" type="submit" value="Submit">
          </div>
          
          </div>

        </fieldset>
       </form>
      </div>
      

    </div>
  </div>
</div>


<!-- bg-1 -->


 <%@include file="/jsps/footer.jsp" %>

</body>

</html>