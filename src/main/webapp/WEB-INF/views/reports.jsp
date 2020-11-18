
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>404 Expense Tracker</title>
<link href="<c:url value="/static/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/static/js/highcharts.js" />"></script>
<script src="<c:url value="/static/js/script.js" />"></script>
<script src="<c:url value="/static/js/charts.js" />"></script>
<script src="<c:url value="/static/js/charts2.js" />"></script>
<script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
<script>var expenseCostCategory = [];
		var expenseTypeCategory = [];
		totalCostCategory = 0;
		
		</script>
	
<t:forEach var="expense" items="${expenseCategory}">
<script> 
expenseTypeCategory.push('${expense.EXPENSETYPE}');
expenseCostCategory.push(${expense.TOTALCOST});
totalCostCategory+= ${expense.TOTALCOST};

		</script>
</t:forEach>
		

</head>
<body>
<h1>Expense Reports</h1>
<div id="containerPieCategory"></div>
<div id="containerCategory"></div>
<div>
<table class="table table-striped table-bordered">
		<td><strong>Expense Type</strong></td>
		<td><strong>Average Cost</strong></td>
		<td><strong>Total Expenses</strong></td>
		<t:forEach var="expense" items="${expenseCategory}">
			<tr>

			</tr>
			<tr>
				<td>${expense.EXPENSETYPE}</td>
				<td>${expense.AVERAGECOST}</td>
				<td>${expense.TOTALCOUNT }</td>
			</tr>
		</t:forEach>
	</table>
	</div>
	<script>var expenseCostUser = [];
		var expenseTypeUser = [];
		</script>
<h1>User Reports</h1>
<t:forEach var="expense" items="${expenseUser}">
<script> 
expenseCostUser.push(${expense.TOTALCOST});
expenseTypeUser.push('${expense.USER}');
		</script>
</t:forEach>
		

</head>
<body>

<div id="containerUser"></div>
<div>
<table class="table table-striped table-bordered">
		<td><strong>User</strong></td>
		<td><strong>Average Cost</strong></td>
		<td><strong>Total Expenses</strong></td>
		<t:forEach var="expense" items="${expenseUser}">
			<tr>

			</tr>
			<tr>
				<td>${expense.USER}</td>
				<td>${expense.AVERAGECOST}</td>
				<td>${expense.TOTALCOUNT}</td>
			</tr>
		</t:forEach>
	</table>
	</div>
	<button onclick="location.href = '/homePage';" class="btn btn-dark btn-lg"> Back to Home </button>
</body>
</html>
