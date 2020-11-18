<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expense submittal form</title>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
	<div class="container">
	<h3>Submit Expense</h3>
		<form:form action="${pageContext.request.contextPath}/createExpense/" cssClass="form-horizontal"
			method="post" modelAttribute="Expense">
			<div class="form-group">
				<label for="expenseName" class="col-md-3 control-label">Name of Expense</label>
				<div class="col-md-9">
					<form:input path="expenseName" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="expenseCost" class="col-md-3 control-label">Cost</label>
				<div class="col-md-9">
					<form:input path="expenseCost" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="date" class="col-md-3 control-label">Date</label>
				<div class="col-md-9">
					<form:input type="date" path="date" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="expenseType" class="col-md-3 control-label">Expense Type</label>
				<div class="col-md-9">
					<form:select path="expenseType" cssClass="form-control" >
						<option value="">Select an Expense type</option>
						<option value="SERVICES">Services</option>
						<option value="FOOD">Food</option>
						<option value="SUPPLIERS">Suppliers</option>
						<option value="TRAVEL">Travel</option>
						<option value="PERSONAL">Personal</option>
					</form:select>
				</div>
			</div>

			<div class="form-group">
				<label for="billImage" class="col-md-3 control-label">Upload Bill Image</label>
				<div class="col-md-9">
					<form:input type="file" path="billImage" cssClass="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:input type="hidden" path="expenseStatus" value="Pending" cssClass="form-control" />
					<form:button cssClass="btn btn-dark btn-lg">Submit</form:button>
				</div>
			</div>

		</form:form>
		</div>
</body>
</html>