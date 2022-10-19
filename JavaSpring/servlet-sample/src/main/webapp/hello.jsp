<%@page isELIgnored="false" %>
<html>
<head>
    <title>Welocome to our Website</title>
</head>
<body>
<h1>${say}</h1>
<marquee>
    <font size="3" color="#dc143c">
        Hello word!!${param["j_username"]}!!
    </font>
</marquee>
<font color="blue">Host name : <%= request.getRemoteHost() %>
</font>
<br>
<font color="blue">Session id: <%= session.getId()  %>
</font>
</body>
</html>