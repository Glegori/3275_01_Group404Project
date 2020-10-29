<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>404 Expense Tracker</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>

	<div id="idSideMenu" class="sideMenu">
  		<a href="javascript:void(0)" id="closeButton" class="sideButton" onclick="closeMenu()">&#9668;</a>
		<a href="${pageContext.request.contextPath}/submitExpense}" onclick="openExpenseForm()">Add An Expense</a>
  	</div>

	<div id="top">
		<h2 class="header">404 Expense Tracker </h2>
		<p class="header">Keep your expenses in check</p>
		<button id="logout" type="button" class="btn btn-dark btn-lg"> Logout</button>
	</div>

	<div class="sideButton" onclick="openMenu()">&#x25ba; Menu</div>
	<div id="expenseForm">&nbsp;</div>


</body>
</html>
