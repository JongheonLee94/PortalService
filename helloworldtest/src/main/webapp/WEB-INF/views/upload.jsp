<%--
  Created by IntelliJ IDEA.
  User: eironeia
  Date: 2018-06-11
  Time: 오후 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<h1>file upload</h1>
<form action="/helloworld/upload" method="POST" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
<img src="${url}"/>
</body>
</html>
