<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="/jsps/header.jsp" %>
</head>
<body>

 
   
     <%@include file="/jsps/navigation.jsp" %>
    
    <div class="container">
    <h2>${truckname }</h2>
        <div class="commentsList">
        
        <c:forEach items = "${reviewList}" var = "UserInput">
  
         
                     <p> ${UserInput.comments}</p>
                                
           <p>- ${UserInput.commented_by}</p>
   
        
        </c:forEach>
        
       
            <form action="${pageContext.request.contextPath}/UpdateReview.do" method ="post">
                Write a review<br><br>
               <textarea name="comments" >  </textarea>
  <br>
  <input type="submit" value="Submit"/>
 
            </form>
        </div>
        
    </div>
        

</body>
</html>