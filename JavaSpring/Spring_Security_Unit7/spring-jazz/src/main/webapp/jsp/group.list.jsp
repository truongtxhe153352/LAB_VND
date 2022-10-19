<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 10/10/2022
  Time: 9:10 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Group List</h1>
        <table style="width: 100%" border="2px">
            <tr>
                <td>NAME</td>
                <td>DELETE</td>
                <td>UPDATE</td>
            </tr>

            <tr>
                <td colspan="2">
                    <form method="get" action="/group/list">
                        <input type="text" name="q">
                    </form>
                </td>
            </tr>

            <c:forEach items="${groups}" var="item" varStatus="loop">
                <tr>
                    <td><a href="/account/list?groupId=${item.id}">${item.name}</a>
                        <c:forEach items="${item.users}" varStatus="loop" var="user">
                            <li>${user.username} - ${user.age}</li>
                        </c:forEach>
                    </td>

                    <td><a href="../group/delete/${item.id}">Delete</a> </td>
                    <td><a href="../group/edit?id=${item.id}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>