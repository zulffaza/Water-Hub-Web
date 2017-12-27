<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.waterhub.web.model.User" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 12/14/2017
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Waterhub</title>

    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap-theme.min.css">
</head>
<body>
    <h2 class="text-primary">Welcome To Waterhub</h2>

    <c:if test="${not empty user}">
        <h5 class="text-info">Menu</h5>

            <div class="list-group">
            <c:forEach items="${user.roles}" var="role" varStatus="each">

                <c:choose>
                    <c:when test="${role.name.equals('Admin')}">
                        <a href="/admin" class="list-group-item">Admin Page</a>
                    </c:when>
                    <c:when test="${role.name.equals('Customer')}">
                        <a href="/customer" class="list-group-item">Customer Page</a>
                    </c:when>
                    <c:when test="${role.name.equals('Seller')}">
                        <a href="/seller" class="list-group-item">Seller Page</a>
                    </c:when>
                </c:choose>
            </c:forEach>

        </div>
    </c:if>

    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>
