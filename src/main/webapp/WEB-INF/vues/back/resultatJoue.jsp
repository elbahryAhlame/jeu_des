<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.bo.GameState"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Game App</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/style/bootstrap.min.css" 
rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/style/signin.css"
	rel="stylesheet">
</head>
<body>
     <form action="${pageContext.request.contextPath}/GameServlet" method="POST">
     <fieldset div class="container">
				<legend>Partie terminée </legend>
	<label>Numero de dés </label>
	<div>
<ul>
 <c:forEach items="${requestScope.messages}" var="msg">
                   <c:choose>
					  <c:when test="${msg.type == Message.WARN}">
					   <li style="color: yellow">${msg.text}</li>
					  </c:when>
					  <c:when test="${msg.type == Message.INFO}">
					   <li style="color: green">${msg.text}</li>
					  </c:when>
					   <c:when test="${msg.type == Message.ERROR}">
					   <li style="color: red">${msg.text}</li>
					  </c:when>
					  <c:otherwise>
					  <li >${msg.text}</li>
					  </c:otherwise>
					</c:choose>

				</c:forEach>
			</ul>
		
</div>
     </fieldset>
     </form>


</body>
</html>