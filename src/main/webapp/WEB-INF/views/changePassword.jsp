<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>


<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Change User password</title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/static/js/script.js" />"></script>
</head>
<body>
<div class="container">
    <h3>Change User Password</h3>
    <h6>Note: You are only able to change your account's password.</h6>

    <t:if test="${ message != null }" >
        <div class="alert alert-success" role="alert">${message}</div>
    </t:if>

    <t:if test="${ error != null }" >
        <div class="alert alert-danger" role="alert">${error}</div>
    </t:if>

<form:form action="${pageContext.request.contextPath}/submitPassword/" cssClass="form-horizontal"
               method="post" modelAttribute="User" >

    <div class="form-group">
        <label for="oldPassword" class="col-md-3 control-label">Old Password:</label>
        <div class="col-md-9">
            <input name="oldPassword" type = "password" id="oldPassword" cssClass="form-control" />
        </div>
    </div>

    <div class="form-group">
        <label for="newPassword" class="col-md-3 control-label">New Password:</label>
        <div class="col-md-9">
            <input name = "newPassword" type = "password" id="newPassword" cssClass="form-control" />
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

</div>



</body>
</html>