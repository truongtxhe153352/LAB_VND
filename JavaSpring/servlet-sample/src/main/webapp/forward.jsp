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
        <%
            String agent = request.getHeader("User-Agent");
        %>

        <% if (agent.indexOf("Firefox") > 1) {%>
        <jsp:forward page="path.jsp"/>
        <% } else { %>
        <jsp:forward page="include.jsp"/>
        <% }%>
    </body>
</html>
