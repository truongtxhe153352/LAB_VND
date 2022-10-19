<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 30/09/2022
  Time: 11:20 SA
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student List</title>
</head>
<script>
    function view(id) {
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.open("GET", "json/" + id, true);
        xmlHttp.onload = function () {
            if (this.status != 200) return;
           // console.log(this.responseText)
            var student = JSON.parse(this.responseText);
            document.getElementById('content').innerHTML='Name: ' + student.name;
            var dialog = document.getElementById('viewStudent');
            dialog.show();
        };
        xmlHttp.send();
    }
</script>
<body>
<form method="GET" action="list">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Age</td>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                    <%--            <td>${student.name}</td>--%>
                <td><a href="javascript:view(${student.id})">${student.name}</a></td>
                <td>${student.age}</td>
                <td><a href="delete/${student.id}">delete</a></td>
                <td><a href="edit/${student.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
    <tr>
        <td colspan="4"><input type="text" name="q" size="30"/></td>
        <td><input type="submit" value="Submit"/></td>
    </tr>
</form>
<dialog id="viewStudent" style="width: 50%; border: 1px dotted black;">
    <div id="content"></div>
    <button id="hide">Close</button>
</dialog>
<script>
    (function (){
        var dialog = document.getElementById('viewStudent');
        document.getElementById('hide').onclick = function (){
            dialog.close();
        };
    })();
</script>
</body>
</html>
