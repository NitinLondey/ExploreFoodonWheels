<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<head>
<meta charset="utf-8">
<meta name="description" content=" ">
<meta name="author" content=" ">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Menu</title>

<%@include file="/jsps/header.jsp"%>

</head>
<body>


	<style>
.menudetails {
	display: block;
	padding-bottom: 4px;
	padding-left: 6px;
}

.menuimages {
	padding-top: 48px;
	display: block;
}

.title {
	font-size: 30px;
	color: white;
	display: block;
	width: 100%;
	font-family: 'Fjalla One', sans-serif;
	text-align: center;
	padding-bottom: 4px;
}

.formdata {
	float: left;
	width: 408px;
	margin-right: 20px;
	display: inline-block;
}

.menuheader {
	color: #ffcb0b;
	font-size: 20px;
	background-color: #d1242a;
	padding-top: 5px;
	padding-bottom: 2px;
	display: block;
	font-family: 'Fjalla One', sans-serif;
	text-align: center;
	margin-bottom: 20px;
	text-transform: uppercase;
	margin-top: 5px;
}

.menuheader1 {
	padding-top: 5px;
	padding-bottom: 2px;
	margin-bottom: 20px;
}

.choosefilebtn {
	padding-bottom: 4px;
	padding-left: 6px;
}

.submitfilebtn {
	margin-bottom: 4px;
	margin-left: 6px;
}

.mainrow {
	background-color: rgba(255, 94, 0, 0.7);
	border-color: #061551;
	margin-left: 53px;
	margin-right: 53px;
}
</style>


	<%@include file="/jsps/navigation.jsp"%>

	<%
		if ((request.getAttribute("message") != null)) {
	%>
	<div class="errorMsg">
		<%=request.getAttribute("message")%>
	</div>
	<%
		request.setAttribute("message", null);
		}
	%>

	<div class="bg-1 section">
		<div class="inner" data-topspace="45" data-bottomspace="35"
			data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

			<div class="container">
				<h3 class="hdr1">Add a Menu</h3>

				<div class="row mainrow">
					<div class="col-lg-5 col-md-5 ">
						<c:forEach var="list" items="${list}">
							<p>
								<span class="menuheader"><c:out value="${list}" /></span>
							</p>

						</c:forEach>
						<p>
							<img id="imgs" class="menuimages"
								src="${pageContext.request.contextPath}/uploads/${fileName}">
						</p>

	<%
	if ( request.getSession().getAttribute("role").equals("truck_owner")) {
			
			
		
	%>
	
	
						<form class="formdata"
							action="${pageContext.request.contextPath}/ImageUploaderServlet.do"
							method="post" enctype="multipart/form-data" id="menuadd">

							<input id="hidden" type="hidden" name="cuisinename"
								value="${list}" /> 
							<input class="choosefilebtn" id="file"
								type="file" name="file" size="50" onchange="onSubmit(event);" />
							<input class="submitfilebtn" type="submit" value="Upload File" />
						</form>
<% } %>
					</div>

				</div>

			</div>
		</div>

	</div>
	<%@include file="/jsps/footer.jsp"%>
</body>

<script>
	function onSubmit(event) {
		$("#imgs").attr('src', URL.createObjectURL(event.target.files[0]));
	}
	function onSubmitimage(event) {
		$("#imgs1").attr('src', URL.createObjectURL(event.target.files[0]));
	}
	function submitForm() {
		form = document.getElementById("menuadd");
		form.submit();
	}
</script>

</html>