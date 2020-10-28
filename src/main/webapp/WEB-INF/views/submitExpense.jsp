<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expense submittal form</title>
</head>
<body>
	<div class="container">
	<h3>Submit Expense</h3>
		<form:form action="${pageContext.request.contextPath}/createExpense/" cssClass="form-horizontal"
			method="post" modelAttribute="expense">
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
				<label for="date" class="col-md-3 control-label">date</label>
				<div class="col-md-9">
					<form:input path="date" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="expenseType" class="col-md-3 control-label">Expense Type</label>
				<div class="col-md-9">
					<form:input path="expenseType" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="expenseStatus" class="col-md-3 control-label">Expense Status</label>
				<div class="col-md-9">
					<form:input path="expenseStatus" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="billImage" class="col-md-3 control-label">Upload Bill Image</label>
				<div class="col-md-9">
					<form:input type="file" path="expenseType" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="user" class="col-md-3 control-label">user</label>
				<div class="col-md-9">
					<form:input path="user" cssClass="form-control" />
				</div>
			</div>
			
			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btn-primary">Submit</form:button>
				</div>
			</div>

		</form:form>
			
		</div>
		


</body>
</html>