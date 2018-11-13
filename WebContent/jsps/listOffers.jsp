<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>


<head>
<meta charset="utf-8">
<meta name="description" content=" ">
<meta name="author" content=" ">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>List Offers</title>


<%@include file="/jsps/header.jsp"%>

</head>
<body>
	<style>
.truckheader {
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
</style>

	<%@include file="/jsps/navigation.jsp"%>




	<div class="bg-2 section" id="contact">
		<div class="inner" data-topspace="45" data-bottomspace="35"
			data-image="assets/flavours/tacos/images/demo-content/background-6.jpg">

			<div class="container">

				<h3 class="hdr1">Food Truck Offers</h3>

				<div class="easyBox full">


					<c:forEach var="offers" items="${listofoffers}">
						<div class="row nomargin">
							<div class="col-md-8">


								<span class="truckheader"><c:out
										value="${offers.truck_name}" /></span>


								<div class="frameImg">
									<c:choose>
										<c:when test="${offers.image_path == null}">
											<img
												src="${pageContext.request.contextPath}/uploads/American1.jpg"
												alt=" ">
										</c:when>
										<c:otherwise>
											<img
												src="${pageContext.request.contextPath}/uploads/${offers.image_path}"
												alt=" ">
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>


			</div>
		</div>
	</div>



	<%@include file="/jsps/footer.jsp"%>

</body>
<script>
	function submitForm() {
		document.getElementById("searchForm").submit();
	}
</script>
</html>
