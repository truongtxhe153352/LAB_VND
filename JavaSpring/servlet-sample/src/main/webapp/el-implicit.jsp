<%--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>

<!DOCTYPE html>

<html>
<head>
    <title>Welocome to our Website</title>
</head>
<body>
    <h1>EL Example</h1>
    <c:forEach var="reqHeader" items="${header}">
        ${reqHeader.key} = header.value <br/>
    </c:forEach>
</body>
</html>
--%>

<%--
    Document   : sum
    Created on : Sep 22, 2022, 1:47:51 PM
    Author     : truon

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>

<!DOCTYPE html>

<html>
<head>
    <title>Welocome to our Website</title>
</head>
<body>
    <h1>EL Example</h1>
    <c:forEach var="reqHeader" items="${header}">
        request.value - length = ${fn:length(reqHeader.value)} <br/>
    </c:forEach>
</body>
</html>