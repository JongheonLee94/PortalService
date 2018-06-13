<%--
  Created by IntelliJ IDEA.
  User: eironeia
  Date: 2018-06-13
  Time: 오전 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
<h1>User</h1>
<form action="/user" method="POST">
    ID  :<input type="text" name="id" value="${user.id}"/> <br />
    Name : <input type="text" name="name" value="${user.name}"/><br />
    Password : <input type="text" name="password" value="${user.password}" /> <br />
    <input type="submit"/>
</form>
</body>
</html>
