<%--
  Created by IntelliJ IDEA.
  User: Mikhail Kuzivanov
  Date: 20.12.2019
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Auth page</title>
  </head>
  <body>
  <form action="/login" method="post">
    <p>Username: <input type="text" name="username"></p>
    <p>Password: <input type="password" name="password"></p>
    <p><input type="submit" value="SIGN IN"></p>
  </form>
  </body>
</html>