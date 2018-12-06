<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

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



      
<div class="bg-2 section" id="contact">
  <div class="inner" data-topspace="45" data-bottomspace="35" data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

    <div class="container">

      <h3 class="hdr1">Search Food Truck</h3>

      <div class="easyBox full">
		<form class="simpleForm" id="searchForm" action="${pageContext.request.contextPath}/SearchCuisine.do">
           <fieldset>
           <label>Search for cuisines</label>
          <div class="col-md-8"> <input type="text" name="search-cuisine" placeholder="American, Mexican Thai, Indian .. "/></div>
          <div class="col-md-4"> <input class="btn btn-default" type="button" value="Submit" onclick="submitForm();"></div>
           </fieldset>
      	</form>
      	
      	<%if((request.getAttribute("message")!=null))
      {
    	  %>
      <div>
       <h6> <%= request.getAttribute("message") %></h6> 
	  </div>
      <%
		request.setAttribute("message", null);
	} %>
<%if((request.getAttribute("search_message")!=null))
      {
    	  %>
      <div>
       
      <h6> <%= request.getAttribute("search_message") %></h6>
	</div>
      <%
		request.setAttribute("search_message", null);
	} %>
        <c:forEach var="foodTruck" items="${foodTruckList}">
        <div class = "row">
		
        </div>
        	<div class="row nomargin">
          	<div class="col-md-4">
          		<div class="frameImg">
          		<c:choose>
          		<c:when test="${foodTruck.image_path == null}">
          			<img src="${pageContext.request.contextPath}/images/demo-content/product-1.jpg" alt=" ">
          		</c:when>
          		<c:otherwise>
          		<img src="${pageContext.request.contextPath}/uploads/${foodTruck.image_path}" alt=" ">
          		</c:otherwise>
          		</c:choose>
          		</div>
			</div>
			<div class="col-md-8">
			<p>
			<h6><c:out value="${foodTruck.truck_name}" /></h6>
			
			<c:out value="${foodTruck.phone}" /><br/>
			<c:out value="${foodTruck.address_line_1}" /><br/>
			<c:out value="${foodTruck.address_line_2}" /><br/>
			<c:out value="${foodTruck.city}" />, <c:out value="${foodTruck.state}" />
			<c:out value="${foodTruck.zip_code}" /><br/>
			Serves : <c:out value="${foodTruck.cuisine}" /><br/>
			Open:  <c:out value="${foodTruck.days}" /><br/>
			WeekDays :  <c:out value="${foodTruck.weekday_time}" /><br/>
			WeekEnds  : <c:out value="${foodTruck.weekend_time}" /><br/>
			
			
			Open Menu <input type ="button" value="View Truck Menu" onclick="location.href='${pageContext.request.contextPath}/AddMenu.do?truckname=${foodTruck.truck_name}'"/>
			Open Events <input type ="button" value="View Truck Events" onclick="location.href='${pageContext.request.contextPath}/showFoodFestivals?truckname=${foodTruck.truck_name}'"/>
						Open Reviews <input type ="button" value="Reviews" onclick="location.href='${pageContext.request.contextPath}/AddReview.do?truckname=${foodTruck.truck_name}'"/>
			
			</p>
			</div>
			
			
			</div>
        
       </c:forEach>
       
      </div>
      

    </div>
  </div>
</div>



 <%@include file="/jsps/footer.jsp" %>

</body>
<script>

function submitForm(){
	document.getElementById("searchForm").submit();
}
</script>
</html>
