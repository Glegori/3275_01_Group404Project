<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Review Form</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/script.js" />"></script>
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
</head>
<body>
	<div id="top">
		<h2 class="header">Expense Review Form </h2>
		<button onclick="location.href = '/homePage';" id="backToMain" type="button" class="btn btn-dark btn-lg"> Return</button>
	</div>
	<div id="expenseForm">&nbsp;</div>
	<table class="table table-striped table-bordered">
	<td><strong>Submitting User</strong></td>
	<td><strong>Expense Name</strong></td>
	<td><strong>Expense Cost</strong></td>
	<td><strong>Expense Date</strong></td>
	<td><strong>Expense Type</strong></td>
	<td><strong>Expense Status</strong></td>
	<td><strong>Modify Expense Status</strong></td>
	<t:forEach var="expense" items="${reportingUserExpenses}">
	<tr>
	</tr>
                  <tr>
                            <td>${expense.user}</td>
                            <td>${expense.expenseName}</td>
                            <td>${expense.expenseCost}</td>
                            <td>${expense.date}</td>
                            <td>${expense.expenseType}</td>
                            <td>${expense.expenseStatus}</td>   
                            <td>place button here that will open a form</td>   
                        </tr>
                    </t:forEach>
                    </table>
</body>
</html>
