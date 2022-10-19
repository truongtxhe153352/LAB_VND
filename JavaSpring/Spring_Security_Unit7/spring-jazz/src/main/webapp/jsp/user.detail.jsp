<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 10/10/2022
  Time: 3:05 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User</h1>
        <p>Account: ${user.username}</p>
        <p>Password: ${user.password}</p>
        <p>Email: ${user.email}</p>
        <p>Age: ${user.age}</p>
        <p>Group: ${user.groupId} =======> ${user.group.name}</p>
    </tiles:putAttribute>
</tiles:insertDefinition>
