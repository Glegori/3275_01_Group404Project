<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t"%>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Manage Users</title>
    <link href="<c:url value="/static/css/bootstrap.min.css" />"
          rel="stylesheet">
    <script src="<c:url value="/static/js/script.js" />"></script>
    <script src="<c:url value="/static/js/jquery-1.11.1.min.js" />"></script>
</head>
<body>

<div id="top">
    <h2 class="header">Manage Users</h2>
    <button onclick="location.href = '/homePage';" id="backToMain" type="button" class="btn btn-dark btn-lg">Return</button>
    <p class="header">Edit existing users</p>
</div>



<div id="editUserForm" class="sideMenu">
<%--    <t:if test="${editUser != null}">--%>
    <button onclick="closeUserForm()" id="closeSide" type="button" class="btn btn-dark btn-sm">x</button>
<%--            <h1>Edit User - ${editUser.getID()}</h1>--%>
            <form:form action="${pageContext.request.contextPath}/editUser/" cssClass="form-horizontal"
                       method="post" modelAttribute="editUser">
                    <div class="form-group">
<%--                        <label for="userName" class="col-md-3 control-label">User Name</label>--%>
                        <div class="col-md-9">
                            <p class="col-md-12 control-label" id="labelUserName">User Name: </p>
<%--                            <form:input path="userName" value="${editUser.getUserName()}" cssClass="form-control" />--%>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reportsFrom" class="col-md-3 control-label">Managing Reports From</label>
                        <div class="col-md-9">
<%--                            <p class="col-md-12 control-label" id="labelReportsFrom">Reports From: </p>--%>
                            <form:input path="reportsFrom" value="${editUser.getReportsFrom()}"  cssClass="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="userType" class="col-md-3 control-label">User Type</label>
                        <div class="col-md-9">
                            <form:select path="userType" cssClass="form-control" >
                                <form:option value="admin" label="Admin"/>
                                <form:option value="user" label="User" selected="true" />
                            </form:select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="total" class="col-md-3 control-label">Total</label>
                        <div class="col-md-9">
<%--                            <p class="col-md-12 control-label" id="labelTotal">Total:</p>--%>
                            <form:input path="total" value="${editUser.getTotal()}"  cssClass="form-control" />
                        </div>
                    </div>

<%--                    <div class="form-group">--%>
<%--                        <!-- Button -->--%>
<%--                        <div class="col-md-offset-3 col-md-9">--%>
<%--&lt;%&ndash;                            <form:button cssClass="btn btn-primary">Submit</form:button>&ndash;%&gt;--%>
<%--                        </div>--%>

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-md-offset-3 col-md-9">
                            <form:input type="hidden" path="ID" value="" id="id" cssClass="form-control"/>
                            <form:input type="hidden" path="userName" value="" id="userName" cssClass="form-control"/>
                            <form:input type="hidden" path="password" value="" id="password" cssClass="form-control"/>
<%--                            <form:input type="hidden" path="reportsFrom" value="" id="reportsFrom" cssClass="form-control"/>--%>
<%--                            <form:input type="hidden" path="total" value="" id="total" cssClass="form-control"/>--%>
                            <form:button type="submit" cssClass="btn btn-dark btn-lg" onclick="closeUserForm()">Submit</form:button>
                        </div>
                    </div>
                </form:form>

</div>
<%--    </t:if>--%>




<div id="userTable">&nbsp;
<table class="table table-striped table-bordered">
    <td><strong>ID</strong></td>
    <td><strong>User Name</strong></td>
    <td><strong>Managing Reports From</strong></td>
    <td><strong>User Type</strong></td>
    <td><strong>Total</strong></td>
    <td><strong>Edit</strong></td>
    <td><strong>Delete</strong></td>
    <t:forEach var="user" items="${currentUsers}">
        <tr>
        </tr>
        <tr>
            <td>${user.getID()}</td>
            <td>${user.getUserName()}</td>
            <td>${user.getReportsFrom()}</td>
            <td>${user.getUserType()}</td>
            <td>${user.getTotal()}</td>
            <td><a href="javascript:void(0)" onclick="openEditUserForm('${user.getID()}', '${user.getUserName()}', '${user.getPassword()}' ,
                    '${user.getUserType()}','${user.getReportsFrom()}', '${user.getTotal()}')">Edit</a></td>
            <td><a href="${pageContext.request.contextPath}/deleteUser/?id=${user.getID()}">Delete</a></td>
        </tr>
    </t:forEach>
</table>

</div>


</body>
</html>
