<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show Bill Image</title>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
	<div class="container">
	<h3>Bill Image</h3>
		
		<t:forEach var="expense" items="${currentUserExpenses}">
			<img src="/src/main/uploadPhotos/${expense.billImage}" />
		</t:forEach>

		<div>
			<button onclick="location.href = '/homePage';" id="backToMain" type="button" class="btn btn-dark btn-lg">Return</button>
		</div>
			
		</div>
		


</body>
</html>