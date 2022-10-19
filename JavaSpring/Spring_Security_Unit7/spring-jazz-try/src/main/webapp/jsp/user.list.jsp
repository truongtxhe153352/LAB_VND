<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 10/10/2022
  Time: 11:26 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>

</<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User List</h1>
        <table style="width: 100%" border="2px">
            <tr>
                <td style="color: green">Account</td>
                <td style="color: green">Email</td>
                <td style="color: green">Age</td>
                <td style="color: green">Group</td>
            </tr>
            <c:forEach items="${users}" var="item" varStatus="loop">
                <tr>
                    <td><a href="/account/detail/${item.username}">${item.username}</a></td>
                    <td>${item.password}</td>
                    <td>${item.email}</td>
                    <td>${item.age}</td>
                    <td>${item.groupId}</td>
                    <td><a href="/account/delete-${item.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <div><h1 style="color: darkgreen">Age average: ${average}</h1></div>
    </tiles:putAttribute>
</tiles:insertDefinition>
