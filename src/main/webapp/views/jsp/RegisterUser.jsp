<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,200;0,400;0,700;1,100&display=swap"
          rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/Style/css.css' />">
</head>
<body>
<div class="conteiner" align="center">

    <form class="logForm" action="controller" method="post">
        <h1 class="h1R" title="Форма реєстрації на сайті">Реєстрація</h1>
        <input type="hidden" name="command" value="register" />

        <table style="with: 80%">
            <tr>
                <td title="Введіть свій емаіл">Email</td>
                <td><input type="email"  name="login" /></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text"  name="firstName" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text"  name="lastName" /></td>
            </tr>
            <tr>
                <td>UserName</td>
                <td><input type="text" name="localeName" /></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Повторіть пароль</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input class="myButton" type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
