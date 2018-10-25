


<html>


<head>
  <meta charset="utf-8">
  <meta name="description" content=" ">
  <meta name="author" content=" ">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1, shrink-to-fit=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Foodtruck</title>

  <%@include file="/jsps/header.jsp" %>

</head>
<body>
 <%@include file="/jsps/navigation.jsp" %>



<!-- ***************** -->
<!-- ** THREE BOXES ** -->

<%if((request.getAttribute("message")!=null))
      {
 %>
    <div class="errorMsg">
              <%= request.getAttribute("message") %>
	</div>
      <%
		request.setAttribute("message", null);
	} %>
<div class="row nomargin threeBoxes" id="location">
  <div class="col-md-4 col-sm-4">

    <div class="googleMap" data-location="Marine Ave. New York, NY" data-text="Tacos Locos, Atlantic Avenue 1234, NY"></div>

  </div>
  <div class="col-md-4 col-sm-4">
    <div class="full-width-photo" data-image="${pageContext.request.contextPath}/images/demo-content/photo-1.jpg"></div>
  </div>
  <div class="col-md-4 col-sm-4">
    <div class="blackboard custom-scrollbar">

      <h3 class="big">Recent Tweets</h3>

      <div class="tweets">
        <div style="display:none">here will be displayed live tweets, configuration is placed in /twitter/config.php</div>
      </div>
    </div>
  </div>
</div>

<div class="bg-1 section" >
  <div class="inner" data-image="${pageContext.request.contextPath}/images/demo-content/background-1.jpg">
    <div class="container">
      <h3 class="hdr1">Nos tacos tienen musica!</h3>
    </div>

<!-- ******************* -->
<!-- ** CIRCLE SLIDER ** -->
<div class="container">
  <div class="row">
    <div class="col-md-8">
      <div class="flexFade flexslider loading-slider text-right">
        <ul class="slides">
          <li>
            <div class="descArea">
              <h4>Our daily Specials</h4>
              <hr>
              <h5>Beef Taco with guacamole</h5>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                tempor incididunt
              </p>
              <span class="price"><em>$</em>6<span>99</span></span>

            </div>
            <div class="roundedImg" data-size="320">
              <img class="media-object" src="${pageContext.request.contextPath}/images/demo-content/photo-3.jpg" alt=" ">
            </div>
          </li>
          <li>
            <div class="descArea">
              <h4>Our Starters</h4>
              <hr>
              <h5>Burritos with cheese & guacamole</h5>
              <p>
                Pellentesque eget augue et ipsum laoreet ultrices eget ut est. Donec tincidunt justo nec lobortis molestie. Duis adipiscing libero ut auctor lacinia.  Quisque a erat vitae libero facilisis pretium.
              </p>
              <span class="price"><em>$</em>8<span>99</span></span>

            </div>
            <div class="roundedImg" data-size="320">
              <img class="media-object" src="${pageContext.request.contextPath}/images/demo-content/photo-4.jpg" alt=" ">
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div class="col-md-4">
      <h4 class="hdr7">About Us</h4>

      <div class="roundedImg pull-right" data-size="160">
        <img class="media-object" src="${pageContext.request.contextPath}/images/demo-content/photo-5.jpg" alt=" ">
      </div>
      <p class="bigger">
        Hello Foody! We have traveled countless miles, hundreds of sailing through storms to find out where is the best and freshest food in the world.
        <br>
        Ultimately we have brought them to our restaurant to let you enjoy this priceless treasure.<br><br>
        Please consider yourself. Good Appetite!<br>
        Your Chef Team<br>
      </p>

    </div>
  </div>
</div>

</div>
</div>


<div class="bg-2 section topOrnament">
<div class="inner" >
    <div class="container">

      <hr class="line1">

      <h3 class="hdr4">Our recomendations!</h3>

      <div class="row">
        <div class="col-md-4 col-sm-6">
          <div class="prodBox pull-left">
            <div class="frameImg"><img src="${pageContext.request.contextPath}/images/demo-content/product-1.jpg" alt=" "></div>

            <div class="inner">
              <h4>Quesadillas with guacamole</h4>

              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius
                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiu.
              </p>
              <span class="price"><small>just</small><em>$</em>6</span>
              <a href="#" class="btn btn-primary btn-sm">order now!</a>
            </div>
          </div>
          <!-- / prodBox -->
        </div>
        <div class="col-md-4 col-sm-6">
          <div class="prodBox type2">
            <div class="frameImg"><img src="${pageContext.request.contextPath}/images/demo-content/product-2.jpg" alt=" "></div>

            <div class="inner">
              <h4>Burritos with cheese & guacamole</h4>

              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius Lorem, consectetur adipisicing elit, sed do eiu.
              </p>
              <span class="price"><small>just</small><em>$</em>8<span>99</span></span>
              <a href="#" class="btn btn-primary btn-sm">order now!</a>
            </div>
          </div>
          <!-- / prodBox -->
        </div>
        <div class="clearfix visible-sm"></div>
        <div class="col-md-4 col-sm-12">
          <div class="prodBox type3 pull-right">
            <div class="frameImg"><img src="${pageContext.request.contextPath}/images/demo-content/product-3.jpg" alt=" "></div>

            <div class="inner">
              <h4>Tortialla wraps with chicken</h4>

              <p>Lorem ipsum dolor sit amet, consectetudo eius Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiu.</p>
              <span class="price"><small>just</small><em>$</em>8<span>99</span></span>
              <span class="info">* Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod</span>
              <a href="#" class="btn btn-primary btn-sm">order now!</a>
            </div>
          </div>
          <!-- / prodBox -->
        </div>
      </div>
      <!-- / row -->

      <h3 class="hdr2">Whatever you choose!</h3>
      <h4 class="hdr3">Be 100% sure, that our Food is entirely made of biological ingredients</h4>
      <hr class="line1">

    </div>

  </div>
</div>

 <%@include file="/jsps/footer.jsp" %>
</body>

</html>