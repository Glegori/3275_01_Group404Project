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
		<button onclick="location.href = '/homePage';" id="backToMain" type="button" class="btn btn-dark btn-lg">Return</button>
	</div>
	<div id="modifyForm" class="sideMenu">
		<button onclick="closeSide()" id="closeSide" type="button" class="btn btn-dark btn-sm">x</button>
		<form:form action="${pageContext.request.contextPath}/modifyStatus/" cssClass="form-horizontal"
				   method="post" modelAttribute="modExpense">
			<div class="form-group">
				<div class="col-md-12">
					<p class="col-md-12 control-label" id="labelExpenseUser">Expense Submitter: </p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<p class="col-md-12 control-label" id="labelExpenseName">Name of Expense: </p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<p class="col-md-10 control-label" id="labelExpenseCost">Expense Cost: </p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<p class="col-md-11 control-label" id="labelDate">Date of Submit: </p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<p class="col-md-12 control-label" id="labelExpenseType">Expense Type: </p>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-9">
					<p class="col-md-3 control-label" id="labelBillImage">*bill image*</p>
				</div>
			</div>
			<div class="form-group">
				<label for="expenseStatus" class="col-md-3 control-label">Expense Status</label>
				<div class="col-md-9">
					<form:select path="expenseStatus" cssClass="form-control">
						<form:option value="Pending" label="Pending"/>
						<form:option value="Approved" label="Approved"/>
						<form:option value="Denied" label="Denied"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="expenseDesc" class="col-md-3 control-label">Decision Explanation</label>
				<div class="col-md-9">
					<form:textarea path="expenseDesc" id="expenseDesc" rows="5" cols="20" maxlength="255" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:input type="hidden" path="id" value="" id="id" cssClass="form-control"/>
					<form:input type="hidden" path="expenseName" value="" id="expenseName" cssClass="form-control"/>
					<form:input type="hidden" path="expenseCost" value="" id="expenseCost" cssClass="form-control"/>
					<form:input type="hidden" path="date" value="" id="date" cssClass="form-control"/>
					<form:input type="hidden" path="expenseType" value="" id="expenseType" cssClass="form-control"/>
					<form:input type="hidden" path="billImage" value="" id="billImage" cssClass="form-control" />
					<form:input type="hidden" path="user" value="" id="user" cssClass="form-control" />
					<form:button type="submit" cssClass="btn btn-dark btn-lg" onclick="closeSide()">Submit</form:button>
				</div>
			</div>

		</form:form>
	</div>
	<table class="table table-striped table-bordered">
	<td><strong>ID</strong></td>
	<td><strong>Submitting User</strong></td>
	<td><strong>Expense Name</strong></td>
	<td><strong>Expense Cost</strong></td>
	<td><strong>Expense Date</strong></td>
	<td><strong>Expense Type</strong></td>
	<td><strong>Expense Status</strong></td>
	<td><strong>Expense Description</strong></td>
	<td><strong>Modify Expense Status</strong></td>
	<t:forEach var="expense" items="${reportingUserExpenses}">
	<tr>
	</tr>
                  <tr>
					  		<td>${expense.id}</td>
                            <td>${expense.user}</td>
                            <td>${expense.expenseName}</td>
                            <td>${expense.expenseCost}</td>
                            <td>${expense.date}</td>
                            <td>${expense.expenseType}</td>
                            <td>${expense.expenseStatus}</td>
					  		<td>${expense.expenseDesc}</td>
					  <td><a href="javascript:void(0)" onclick="openModifyForm('${expense.id}','${expense.user}', '${expense.expenseName}', '${expense.expenseCost}', '${expense.date}',
								'${expense.expenseType}', '${expense.expenseStatus}', '${expense.billImage}', '${expense.expenseDesc}')">Modify Status</a></td>
                        </tr>
                    </t:forEach>
                    </table>
</body>
</html>
