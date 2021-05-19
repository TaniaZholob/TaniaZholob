<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.05.2021
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>LogIn</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,400;0,700;1,100&display=swap"
          rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/Style/css.css' />">
</head>
<body>
<div class="conteiner" align="center">

    <form class="logForm" action="controller" method="post">
        <h1 class="h1R" title="Форма аторизації">Вхід</h1>
        <input type="hidden" name="command" value="register" />

        <table style="with: 80%">
            <tr>
                <td title="Введіть свій емаіл">Email</td>
                <td><input type="email"  name="login" /></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input class="myButton" type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
