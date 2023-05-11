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
	Bonjour
	<c:out value="${sessionScope.gameState.user.nom}" /><br>
	<a href="${pageContext.request.contextPath}/BestScoreServlet">Meilleurs
		scores</a>|
	<br>
	<a href="${pageContext.request.contextPath}/DeconnectServlet">Sedeconnecter</a> |
<br>
     <br>
			
     <form action="${pageContext.request.contextPath}/GameServlet" method="POST">
     <fieldset div class="container">
     	<legend>Game (:</legend>
	<label>Numero de dés </label>
	<input type="number" name="numeroScore"  max="3" min ="1" name="numeroDes">
	<br/>
	<input type="submit" value="Lancer " class="btn-primary" />
	<br/>
     </fieldset>
     </form>
<div>
<ul>
 <c:forEach items="${requestScope.messages}" var="msg">
                   
					  <li >${msg.text}</li>
					 

				</c:forEach>
			</ul>
</div>

</body>
</html>