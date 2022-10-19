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

<c:if test ="${param.person != null}"></c:if>
<c:out value="hello ${param.person}" escapeXml="false"></c:out>
</body>
</html>