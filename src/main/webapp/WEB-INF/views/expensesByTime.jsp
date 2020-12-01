<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expense submittal form</title>
	<link href="<c:url value="/static/css/bootstrap.min.css" />"
		  rel="stylesheet">
	<script src="<c:url value="/static/js/script.js" />"></script>
<%--	<script src="<c:url value="/static/js/expenseOverTimeCharts.js"/>"></script>--%>
<%--	<script>--%>
<%--		var expenseCostArray=[];--%>
<%--		var expenseTypeArray=[];--%>
<%--		var expenseUserArray=[];--%>
<%--		var expenseDateArray=[];--%>

<%--		var expensesPersonalArray=[];--%>
<%--		var expensesServicesArray=[];--%>
<%--		var expensesFoodArray=[];--%>
<%--		var expensesTravelArray=[];--%>
<%--		var expensesSuppliesArray=[];--%>

<%--	</script>--%>
</head>
<body>
	<div class="container">
	<h3>Expenses over time</h3>
		<form:form action="${pageContext.request.contextPath}/expensesOverTime/" cssClass="form-horizontal"
			method="post" modelAttribute="Expense">

				<label for="startDate" class="col-md-3 control-label">Start Date</label>
				<div class="col-md-9">
					<input type="date" id="startDate" name="startDate" path="startDate" cssClass="form-control" />
				</div>

				<label for="endDate" class="col-md-3 control-label">End Date</label>
				<div class="col-md-9">
					<input type="date" id="endDate" name="endDate" path="endDate" cssClass="form-control" />
				</div>
			<div class="form-group">
				<!-- Button -->
		<br/>
				<div class="col-md-offset-3 col-md-9">
					<button onclick="whatever()" id="getExpensesByDate" cssClass="btn btn-dark btn-lg">Submit</button>
				</div>
			</div>

		</form:form>

<%--		<div class="col-md-offset-3 col-md-9">--%>
<%--			<button onclick="whatever()" id="wh" cssClass="btn btn-dark btn-lg">Submit2</button>--%>
<%--		</div>--%>
<%--		</div>--%>

<%--	<div>--%>
<%--		<t:if test="${listOfExpensesBetweenDates != null}">--%>



<%--			<t:forEach var="expense" items="${listOfExpensesBetweenDates}">--%>
<%--				<script>--%>
<%--					expenseCostArray.push('${expense.expenseCost}');--%>
<%--					expenseTypeArray.push('${expense.expenseType}');--%>
<%--					expenseUserArray.push('${expense.user}');--%>
<%--					expenseDateArray.push('${expense.date}');--%>
<%--				</script>--%>
<%--			</t:forEach>--%>

<%--			<t:forEach var="expense" items="${listOfExpensesBetweenDates}">--%>
<%--				<script>--%>
<%--					switch ('${expense.expenseType}'){--%>
<%--						case 'PERSONAL':--%>
<%--							expensesPersonalArray.push({--%>
<%--								expenseDate: '${expense.date}',--%>
<%--								expenseCost: '${expense.expenseCost}'--%>
<%--							})--%>
<%--							break;--%>
<%--						case 'SERVICES':--%>
<%--							expensesServicesArray.push({--%>
<%--								expenseDate: '${expense.date}',--%>
<%--								expenseCost: '${expense.expenseCost}'--%>
<%--							})--%>
<%--							break;--%>
<%--						case 'FOOD':--%>
<%--							expensesFoodArray.push({--%>
<%--								expenseDate: '${expense.date}',--%>
<%--								expenseCost: '${expense.expenseCost}'--%>
<%--							})--%>
<%--							break;--%>
<%--						case 'TRAVEL':--%>
<%--							expensesTravelArray.push({--%>
<%--								expenseDate: '${expense.date}',--%>
<%--								expenseCost: '${expense.expenseCost}'--%>
<%--							})--%>
<%--							break;--%>
<%--						case 'SUPPLIERS':--%>
<%--							expensesSuppliesArray.push({--%>
<%--								expenseDate: '${expense.date}',--%>
<%--								expenseCost: '${expense.expenseCost}'--%>
<%--							})--%>
<%--							break;--%>


<%--					}--%>


<%--				</script>--%>
<%--			</t:forEach>--%>

<%--&lt;%&ndash;			<script>&ndash;%&gt;--%>
<%--&lt;%&ndash;				console.log(expensesPersonalArray);&ndash;%&gt;--%>



<%--&lt;%&ndash;			</script>&ndash;%&gt;--%>


<%--		</t:if>--%>
<%--	</div>--%>

</body>
</html>