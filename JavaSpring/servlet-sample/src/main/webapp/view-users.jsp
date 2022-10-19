<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.io.*,java.util.*,java.sql.*" %>
<%@page import="javax.servlet.http.*,javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>

<html>
<head>
    <h1>View User</h1>
</head>
<body>
<sql:setDataSource var="userdb" driver="org.apache.derby.jdbc.EmbeddedDriver"
                   url="jdbc:derby:D://Maven//servlet-sample//user_db" user="" password=""/>

<sql:query dataSource="${userdb}" var="result">
    select * from hanoi_user
</sql:query>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
    </tr>
    <c:forEach var="row" items="${result.rows}">
        <tr>
            <td>${row.username}</td>
            <td><c:out value="${row.password}"></c:out></td>
            <td>${row.email}</td>
            <td><a href="save?action=del&user=${row.username}">Del</a></td>
        </tr>
    </c:forEach>

</table>
<div><a href="register.html">Add New </a></div>
</body>
</html>
