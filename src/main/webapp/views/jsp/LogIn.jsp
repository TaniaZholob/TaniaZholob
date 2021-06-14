
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Login" />
<%@ include file="/views/jsp/jspf/head.jspf"%>

<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">
    <form action="authentication" method="post">
        <form action="authentication" method="post">
        <h1 class="h1R" title="Форма аторизації"><fmt:message key="login.aut"/></h1>
        <input type="hidden" name="command" value="authorization"/>

        <table style="with: 80%">
            <tr>
                <td title="Введіть свій емаіл">Email</td>
                <td><input type="email" name="login"/></td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td><input type="password" name="password"/></td>
            </tr>
        </table>
        <input class="myButton" type="submit" value='<fmt:message key="login.logIn"/>'/>

    </form>
    <form action="registration" method="get">
        <input type="hidden" name="command" value="GoToRegister"/>
        <input class="myButton" type="submit" value='<fmt:message key="login.registrate"/>'/>
    </form>
<c:if test="${not empty errorMessage}">
    <p><fmt:message key="login.wrong.dates"/></p>
</c:if>
</div>
</body>
</html>
