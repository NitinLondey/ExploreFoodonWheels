<nav class="navbar navbar-default" role="navigation">
	<div class="inner">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle btn btn-primary"
					data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<h3>Explore FoodOnWheels</h3>
				<a class="small-brand" data-width="221" data-top="58"><img
					src="${pageContext.request.contextPath}/images/demo-content/logo-small.png"
					alt=" "></a> <a href="tel:66-87-65-43-21" class="phoneIcon"
					style="display: none">+66 87 65 43 21</a>

				
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav text-center" data-type="margin-top"
					data-pos="138">
					<%
						if (request.getSession().getAttribute("user_name") != null && request.getSession().getAttribute("role").equals("user")) {
					%>
					<li><a	href="${pageContext.request.contextPath}/SearchFoodTruck.do">Search Food Truck</a></li>
					<li><a
						href="${pageContext.request.contextPath}/DisplayList.do">Offers</a></li>
					
					<li><a href="${pageContext.request.contextPath}/profile">My Profile</a></li>
					<%
						}
					%>
					<%
						if (request.getSession().getAttribute("user_name") != null && request.getSession().getAttribute("role").equals("truck_owner")) {
					%>
					<li class="active"><a
						href="${pageContext.request.contextPath}/HomePage">Home</a></li>
					<li> <a href="${pageContext.request.contextPath}/AddMenu.do">Menu card</a></li>
						
					<li><a href="${pageContext.request.contextPath}/AddOffer.do">Offers</a></li>
						
					<li class="dropdown active"><a class="dropdown-toggle">Food festival</a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/showFoodFestivals">My Food Festivals</a></li>
							<li><a href="${pageContext.request.contextPath}/jsps/events.jsp">Add new Festival</a></li>
						</ul></li>
					
					<li class="dropdown active"><a class="dropdown-toggle">Account</a>
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/profile">My Profile</a></li>
							<li><a href="${pageContext.request.contextPath}/Zipcode">Update zipcode</a></li>
							<li><a href="${pageContext.request.contextPath}/MovingStatus">Moving status</a></li>
						</ul></li>
					
					<%
						}
					%>
					
					<%
						if (request.getSession().getAttribute("user_name") != null && request.getSession().getAttribute("role").equals("admin") ){
					%>
				<li><a	href="${pageContext.request.contextPath}/Request_center.do">Request
							Center</a></li>
							<li><a	href="${pageContext.request.contextPath}/ViewEventsServlet.do">Event
							Center</a></li>
					<%
						}
					%>
					
					
					
					<%
						if (request.getSession().getAttribute("user_name") == null) {
					%>
					<li class="dropdown active"><a class="dropdown-toggle">Sign	up</a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/jsps/signup.jsp">Signup
									as Food - Truck</a></li>
							<li><a
								href="${pageContext.request.contextPath}/jsps/signup-user.jsp">Signup
									as User</a></li>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/jsps/login.jsp">Login</a></li>
					<%
						} else {
					%>
					<li><a href="${pageContext.request.contextPath}/Logout.do">Logout</a></li>
					<%
						}
					%>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- / container -->

		<div class="btm"></div>
	</div>

</nav>