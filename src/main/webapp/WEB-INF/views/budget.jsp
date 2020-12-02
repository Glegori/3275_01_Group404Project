<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View budgets</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
	<h1 style="background-color: #f2f2f2; margin: 0 auto;">Please
		select the expense type that you'd like to change the budget for</h1>
	<form:select onchange="changeBudget()" id="type" path="expenseBudget"
		cssClass="form-control">
		<script> var budgetTypes = [];
		var budgetAmounts = [];</script>
		<form:option value="Expense" label="Expense" />
		<t:forEach var="type" items="${expenseBudget}">
			<script> budgetTypes.push('${type.EXPENSETYPE}'); 
		budgetAmounts.push(${type.BUDGET});</script>
			<form:option value="${type.EXPENSETYPE}" label="${type.EXPENSETYPE}" />
		</t:forEach>
	</form:select>

	<div style="width: 50%; margin: 0 auto; display: none;"
		id="selectedChoiceDiv">
		<form:form action="${pageContext.request.contextPath}/saveBudget/"
			method="post" modelAttribute="Expense">
			<h1 id="selectedChoice"></h1>
			<h2 id="currentBudget"></h2>
			<input type=hidden name="expenseType" id="expenseType" value="">
			<input onchange="changeSlider(this.value)" type="range" min="0"
				max="10000000" value="" id="slider" style="width: 30%;">
			<input type="text" id="budget" name="budget"
				onchange="updateSlider()" value="">
			<p id="error" style="color: red;" name="error"></p>
			<h1 id="output"></h1>
			<form:button type="submit" name="save" class="btn btn-dark btn-lg">Save Budget</form:button>
		</form:form>
		<form:form action="${pageContext.request.contextPath}/removeBudget/"
			method="post" modelAttribute="Expense">
			<input type=hidden name="expenseTypeRemove" id="expenseTypeRemove"
				value="">
			<form:button type="submit" name="remove" class="btn btn-dark btn-lg">Remove Current Budget</form:button>
		</form:form>
	</div>
	<button style="margin-top: 350px;"
		onclick="location.href = '/homePage';" class="btn btn-dark btn-lg">
		Back to Home</button>
</body>
</html>