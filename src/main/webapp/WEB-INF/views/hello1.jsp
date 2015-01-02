<%-- 
    Document   : hello
    Created on : Jan 1, 2015, 4:30:14 PM
    Author     : sunil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello PAGE!</h1>
        <h3>Message: ${username}</h3>
        <h3>Username: ${message}</h3>
      <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
    </body>
</html>
