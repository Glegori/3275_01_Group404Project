<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import Expenses using CSV File</title>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
	<div class="container">
	<h3>Import Expenses</h3>
		<form:form action="${pageContext.request.contextPath}/uploadExpensesFile/" cssClass="form-horizontal"
			method="post" modelAttribute="Expense" enctype="multipart/form-data" >
			
			<div class="form-group">
				<label for="billImage" class="col-md-3 control-label">Upload Bill Image</label>
				<div class="col-md-9">
					<form:input type="file" path="billImage" name="billImage" id="billImage" maxlength="240" accept=".csv"  cssClass="form-control" />
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