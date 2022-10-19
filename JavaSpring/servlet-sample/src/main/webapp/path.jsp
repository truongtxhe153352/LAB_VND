<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example</title>
    </head>
    <body>
        <% response.setHeader("Refresh", "6");%>
    <LI>The path of this JSP file is: <%= request.getRequestURI() %>
    <LI>The Hostname is: <%= request.getRemoteHost() %>
    </body>
</html>