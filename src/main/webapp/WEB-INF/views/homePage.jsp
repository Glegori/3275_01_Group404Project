<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>404 Expense Tracker</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/script.js" />"></script>
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
</head>
<body>

	<div id="idSideMenu" class="sideMenu">
  		<a href="javascript:void(0)" id="closeButton" class="sideButton" onclick="closeMenu()">&#9668;</a>
		<a href="javascript:void(0)" onclick="openExpenseForm()">Add An Expense</a>
		
  	</div>

	<div id="top">
		<h2 class="header">404 Expense Tracker </h2>
		<p class="header">Keep your expenses in check</p>
		<button onclick="location.href = '/login';" id="logout" type="button" class="btn btn-dark btn-lg"> Logout</button>
	</div>

	<div class="sideButton" onclick="openMenu()">&#x25ba; Menu</div>
	<div id="expenseForm">&nbsp;</div>
	<table class="table table-striped table-bordered">
	<td>Expense Name</td>
	<td>Expense Cost</td>
	<td>Expense Date</td>
	<td>Expense Expense Type</td>
	<td>Expense Status</td>
	<t:forEach var="expense" items="${currentUserExpenses}">
	<tr>

	</tr>
                        <tr>
                            <td>${expense.expenseName}</td>
                            <td>${expense.expenseCost}</td>
                            <td>${expense.date}</td>
                            <td>${expense.expenseType}</td>
                            <td>${expense.expenseStatus}</td>      
                        </tr>
                    </t:forEach>
                    </table>


</body>
</html>
