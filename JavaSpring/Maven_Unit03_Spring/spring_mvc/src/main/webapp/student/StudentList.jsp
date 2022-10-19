<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="studentTemplate">
    <tiles:putAttribute name="body">
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
                    document.getElementById('content').innerHTML = 'Name : '+ student.name+" <img src='../student/avatar/"+student.id+"'/>";
                    var dialog = document.getElementById('viewStudent');
                    dialog.show();
                };
                xmlHttp.send();
            }
        </script>
        <body>
        <h2>List of Students</h2>
        <div>
            <script>
                function view(id) {
                    var xmlHttp = new XMLHttpRequest();

                    xmlHttp.open("GET", "json/" + id, true);
                    xmlHttp.onload = function () {
                        if (this.status != 200) return;
                        // console.log(this.responseText)
                        var student = JSON.parse(this.responseText);
                        document.getElementById('content').innerHTML = 'Name : '+ student.name+
                            '<img src="../student/avatar/'+student.id+'"/>"';
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
        </div>
        <script>
            function showPos(event, text) {
                var el = document.getElementById('PopUp');
                var x, y;
                if (window.event) {
                    x = window.event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;
                    y = window.event.clientY + document.documentElement.scrollTop + document.body.scrollTop;
                } else {
                    x = event.clientX + window.scrollX;
                    y = event.clientY + window.scrollY;
                }
                x -= 2;
                y -= 2;
                y = y + 15
                el.style.left = x + "px";
                el.style.top = y + "px";
                el.style.display = "block";
                document.getElementById('PopUpText').innerHTML = text;
            }
        </script>

        <DIV id='PopUp'
             style='display: none; position: absolute; left: 100px; top: 50px; border: solid black 1px; padding: 10px; background-color: rgb(200,100,100); text-align: justify; font-size: 12px; width: 135px;'
             onmouseover="document.getElementById('PopUp').style.display = 'none' ">
            <SPAN id='PopUpText'>TEXT</SPAN>
        </DIV>
        </body>
    </tiles:putAttribute>
</tiles:insertDefinition>






<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>--%>


<%--<html>--%>
<%--<head>--%>
<%--    <title>Student List</title>--%>
<%--    <script>--%>
<%--        function showPos(event, text) {--%>
<%--            var el = document.getElementById('PopUp');--%>
<%--            var x, y;--%>
<%--            if (window.event) {--%>
<%--                x = window.event.clientX + document.documentElement.scrollLeft + document.body.scrollLeft;--%>
<%--                y = window.event.clientY + document.documentElement.scrollTop + document.body.scrollTop;--%>
<%--            } else {--%>
<%--                x = event.clientX + window.scrollX;--%>
<%--                y = event.clientY + window.scrollY;--%>
<%--            }--%>
<%--            x -= 2; y -= 2;--%>
<%--            y = y+15--%>
<%--            el.style.left = x + "px";--%>
<%--            el.style.top = y + "px";--%>
<%--            el.style.display = "block";--%>
<%--            document.getElementById('PopUpText').innerHTML = text;--%>
<%--        }--%>
<%--    </script>--%>


<%--</head>--%>
<%--<tiles:insertDefinition name="studentTemplate">--%>

<%--    <tiles:putAttribute name="body">--%>
<%--        <script>--%>
<%--            function view(id) {--%>
<%--                var xmlHttp = new XMLHttpRequest();--%>
<%--                xmlHttp.open("GET","json/"+id,true);--%>
<%--                xmlHttp.onload = function () {--%>
<%--                    if (this.status != 200) return;--%>
<%--                    //console.log(this.responseText);--%>
<%--                    var student = JSON.parse(this.responseText);--%>
<%--                    document.getElementById('content').innerHTML = 'Name : '+ student.name+" <img src='../student/avatar/"+student.id+"'/>";--%>
<%--                    var dialog = document.getElementById('viewStudent');--%>
<%--                    dialog.show();--%>
<%--                }--%>
<%--                xmlHttp.send();--%>
<%--            }--%>
<%--        </script>--%>

<%--        <h2>List of Students</h2>--%>
<%--        <form method="get" action="list">--%>


<%--            <table border="1">--%>
<%--                <tr>--%>
<%--                    <td colspan="3"><input type="text" name="q" size="30" value="${q}"></td>--%>
<%--                    <td><input type="submit" value="Submit"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>Id</td>--%>
<%--                    <td>Name</td>--%>
<%--                    <td>Age</td>--%>
<%--                    <td>Action</td>--%>
<%--                </tr>--%>
<%--                <c:forEach items="${students}" var="student">--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                                ${student.id}--%>
<%--                        </td>--%>
<%--                        <td><a href="javascript:view(${student.id})">${student.name}</a></td>--%>
<%--                        <td>${student.age}</td>--%>
<%--                        <td><a href="delete/${student.id}">delete</a>  <a href="edit/${student.id}">edit</a> </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </form>--%>
<%--        <dialog id="viewStudent" style="width: 50%;border: 1px dotted black;">--%>
<%--            <div id="content"> </div>--%>
<%--            <button id="hide">Close</button>--%>
<%--        </dialog>--%>
<%--        <script>--%>
<%--            (function (){--%>
<%--                var dialog = document.getElementById("viewStudent");--%>
<%--                document.getElementById('hide').onclick = function (){--%>
<%--                    dialog.close();--%>
<%--                }--%>
<%--            })();--%>
<%--        </script>--%>


<%--        <DIV id='PopUp' style='display: none; position: absolute; left: 100px; top: 50px; border: solid black 1px; padding: 10px; background-color: rgb(200,100,100); text-align: justify; font-size: 12px; width: 135px;' onmouseover="document.getElementById('PopUp').style.display = 'none' ">--%>
<%--            <SPAN id='PopUpText'>TEXT</SPAN>--%>
<%--        </DIV>--%>
<%--    </tiles:putAttribute>--%>

<%--</tiles:insertDefinition>--%>
<%--</html>--%>

