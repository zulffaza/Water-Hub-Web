<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Faza Zulfika P P
  Date: 12/14/2017
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Waterhub - Login</title>
    </head>
    <body>
        <h5>${message}</h5>

        <form:form name="loginForm" action="/login" method="post" enctype="application/x-www-form-urlencoded">
            Email : <input name="email" type="text" placeholder="Masukkan email anda..." required="required">
            Password : <input name="password" type="password" placeholder="Masukkan password anda..." required="required">
            <input type="submit" value="Login">
        </form:form>
    </body>
</html>
