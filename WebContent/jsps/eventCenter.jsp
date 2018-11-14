<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
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
<meta charset="ISO-8859-1">
<title>Request Center</title>
</head>
<body>
	<!-- body bgcolor="#52BE80"-->

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

/* Style the buttons inside the tab */
.tab button {
	background-color: inherit;
	float: left;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
	font-size: 17px;
	color: red;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
	color: red;
}

/* Create an active/current tablink class */
.tab button.active {
	background-color: #ccc;
	color: green;
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

#requesttable tr:nth-child(even) {
	background-color: #f2f2f2;
	color: black;
}

/* #requesttable tr:hover {
	background-color: #ddd;
	color: black;
} */

#requesttable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: black;
}

h2 {
    text-align: center;
}
a{
	color: (internal value);
    text-decoration: underline;
}

</style>

	<%@include file="/jsps/header.jsp" %>

</head>
<body>
		 <%@include file="/jsps/navigation.jsp" %>
		
	<h2>Event Center</h2>

	<div class="">

		<table class="table-bordered" id="requesttable">
			<tr>
				<th>TruckName</th>
				<th>FestivalName</th>
				
				<th>Start Date</th>				
				<th>End Date</th>
				<th>Hours</th>
				<th>Address</th>
				<th>City</th>
				<th>State</th>
				<th>ZipCode</th>
				<th>Status</th>																		
				<th>Approve</th>
				<th>Reject</th>
				
			</tr>

			<c:forEach var="list" items="${list}">
				<tr>
					<td><c:out value="${list.truck_name}" /></td>
					<td><c:out value="${list.festival_name}" /></td>
					
					<td><c:out value="${list.startDate}" /></td>
					<td><c:out value="${list.endDate}" /></td>
					<td><c:out value="${list.hours}" /></td>
					<td><c:out value="${list.address}" /></td>
					<td><c:out value="${list.city}" /></td>
					<td><c:out value="${list.state}" /></td>
					<td><c:out value="${list.zip_code}" /></td>
					<td><c:out value="${list.approved}" /></td>
					<!-- <td><input type="button" value="Approve" onclick="getParam();"></td> -->


				<td><a
						href="${pageContext.request.contextPath}//Eventmail?event_id=<c:out value="${list.id}"/>&approval=approved ">Approve</a></td>


					<td><a
						href="${pageContext.request.contextPath}//Eventmail?event_id=<c:out value="${list.id}"/>&reject=rejected ">Reject</a></td>
					
				</tr>
			</c:forEach>
		</table>
	</div>



</body>
</html>