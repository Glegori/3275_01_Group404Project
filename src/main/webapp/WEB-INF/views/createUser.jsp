<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create new user form</title>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
	<div class="container">
	<h3>Create User</h3>
		<form:form action="${pageContext.request.contextPath}/submitUser/" cssClass="form-horizontal"
			method="post" modelAttribute="User">
			<div class="form-group">
				<label for="userName" class="col-md-3 control-label">Username:</label>
				<div class="col-md-9">
					<form:input path="userName" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-md-3 control-label">Password:</label>
				<div class="col-md-9">
					<form:input path="password" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="reportsFrom" class="col-md-3 control-label">Managing reports from:</label>
				<div class="col-md-9">
					<form:input path="reportsFrom" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="userType" class="col-md-3 control-label">User Type:</label>
				<div class="col-md-9">
					<form:input path="userType" cssClass="form-control" />
				</div>
			</div>
			<div class="form-group">
				<label for="total" class="col-md-3 control-label">Current Total Expenses:</label>
				<div class="col-md-9">
					<form:input path="total" cssClass="form-control" />
				</div>
			</div>
			</div>

			<div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
					<form:button cssClass="btn btn-dark btn-lg">Submit</form:button>
				</div>
			</div>

		</form:form>


		<div>
			<button onclick="location.href = '/homePage';" id="backToMain" type="button" class="btn btn-dark btn-lg">Return</button>
		</div>
			
		</div>
		


</body>
</html>