<nav class="navbar navbar-default" role="navigation">
  <div class="inner">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle btn btn-primary" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>

        <div class="text-center">
          <p class="address">
            Tacos Locos - Atlantic Avenue 1234, New York, +66 87 65 43 21
          </p>
        </div>

        <a class="small-brand"   data-width="221" data-top="58"><img src="${pageContext.request.contextPath}/images/demo-content/logo-small.png" alt=" " ></a>

        <a href="tel:66-87-65-43-21" class="phoneIcon" style="display:none">+66 87 65 43 21</a>
        
        <ul class="smallSocials">
          <li class="fb"><a href="#"><img src="${pageContext.request.contextPath}/images/smallsocial-fb.png" alt="Facebook"></a></li>
          <li class="tw"><a href="#"><img src="${pageContext.request.contextPath}/images/smallsocial-tw.png" alt="Twitter"></a></li>
          <li class="gg"><a href="#"><img src="${pageContext.request.contextPath}/images/smallsocial-gg.png" alt="Google+"></a></li>
          <li class="rss"><a href="#"><img src="${pageContext.request.contextPath}/images/smallsocial-rss.png" alt="RSS"></a></li>
        </ul>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav text-center" data-type="margin-top" data-pos="138">
          <li class="active"><a href="${pageContext.request.contextPath}/HomePage">Home</a></li>
          <li><a href="8_menucard.html">Menu card</a></li>
          <li><a href="9_events.html">Events</a></li>
          <li><a href="10_story.html">Story</a></li>
          <li><a href="11_gallery.html">Gallery</a></li>
          
					<li><a
						href="${pageContext.request.contextPath}/Request_center.do">Request
							Center</a></li>


					<%
						if (request.getSession().getAttribute("user_name") == null) {
					%>
					<li class="dropdown active"><a class="dropdown-toggle">Sign
							up</a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/jsps/signup.jsp">Signup
									as Food - Truck</a></li>
							<li><a
								href="${pageContext.request.contextPath}/jsps/signup-user.jsp">Signup
									as User</a></li>
						</ul></li>
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