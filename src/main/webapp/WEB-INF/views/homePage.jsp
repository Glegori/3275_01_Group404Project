<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
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
		<a href="javascript:void(0)" onclick="location.href = '/changePassword' ">Change User<br>Password</a>
		<a href="javascript:void(0)" onclick="location.href = '/createUser'">Create New User</a>
		<a href="javascript:void(0)" onclick="location.href = '/decisionPage'">Review Expenses</a>
		<a href="javascript:void(0)" onclick="location.href = '/reports'">View Reports</a>
  	</div>

	<div id="top">
		<h2 class="header">404 Expense Tracker</h2>
		<p class="header">Keep your expenses in check</p>
		<button onclick="location.href = '/login';" id="logout" type="button"
			class="btn btn-dark btn-lg">Logout</button>

		<select onchange="location = this.value">
			<option value="-" label="-- Please Select an expense type --" />
			<option value="/filterExpense/?expenseType=FOOD" label="Food" />
			<option value="/filterExpense/?expenseType=TRAVEL" label="Travel" />
			<option value="/filterExpense/?expenseType=SUPPLIERS" label="Suppliers" />
			<option value="/filterExpense/?expenseType=SERVICES" label="Services" />
			<option value="/filterExpense/?expenseType=PERSONAL" label="Personal" />
		</select>
		
		<select onchange="location = this.value">
			<option value="-" label="-- Sort Date by --" />
			<option value="/sortExpenseDate/?sortExpense=ASC" label="Asc" />
			<option value="/sortExpenseDate/?sortExpense=DESC" label="Desc" />
		</select>
		
		<select onchange="location = this.value">
			<option value="-" label="-- Please Select an expense Status --" />
			<option value="/filterExpenseByStatus/?expenseStatus=Pending" label="Pending" />
			<option value="/filterExpenseByStatus/?expenseStatus=Approved" label="Approved" />
			<option value="/filterExpenseByStatus/?expenseStatus=Denied" label="Denied" />
		</select>
		
		<button onclick="location.href = '/homePage';" id="clean" type="button"
			class="btn btn-dark btn-lg">Clean Filter</button>

	</div>

	<div class="sideButton" onclick="openMenu()">&#x25ba; Menu</div>

	<div id="expenseForm">&nbsp;</div>
	<table class="table table-striped table-bordered">
		<td><strong>Expense Name</strong></td>
		<td><strong>Expense Cost</strong></td>
		<td><strong>Expense Date</strong></td>
		<td><strong>Expense Type</strong></td>
		<td><strong>Expense Status</strong></td>
		<td><strong>Expense Description</strong></td>
		<t:forEach var="expense" items="${currentUserExpenses}">
			<tr>

			</tr>
			<tr>
				<td>${expense.expenseName}</td>
				<td>${expense.expenseCost}</td>
				<td>${expense.date}</td>
				<td>${expense.expenseType}</td>
				<td>${expense.expenseStatus}</td>
				<td>${expense.expenseDesc}</td>
			</tr>
		</t:forEach>
	</table>


</body>
</html>
