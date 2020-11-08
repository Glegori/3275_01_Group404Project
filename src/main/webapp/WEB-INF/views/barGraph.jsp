<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>404 Expense Tracker</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/highcharts.js" />"></script>
<script src="<c:url value="/static/js/script.js" />"></script>
<script src="<c:url value="/static/js/charts.js" />"></script>
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script>var expenseCost = [];
		var expenseType = [] ;</script>
<t:forEach var="expense" items="${expenseCost}">
<script> expenseCost.push(${expense.TOTALCOST});
expenseType.push(${expense.EXPENSETYPE});
		</script>
</t:forEach>
		

</head>
<body>

<div id="container"></div>
</body>
</html>
