<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Example</title>
    </head>
    <body>
        <h1>Displaying sum</h1>
        <%
           int sum = 0;
            for (int i = 0; i < 25; i++) {
                    sum += i;
                }
            out.println("Sum of number is: " + sum);
            %>
    </body>
</html>