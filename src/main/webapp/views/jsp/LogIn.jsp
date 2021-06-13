
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Login" />
<%@ include file="/views/jsp/jspf/head.jspf"%>

<body>
<div class="conteiner" align="center">
<%--    <%session.setAttribute("previous_request", null);%>--%>
    <form action="authentication" method="post">
        <form action="authentication" method="post">
        <h1 class="h1R" title="Форма аторизації">Вхід</h1>
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
        <input class="myButton" type="submit" value="Увійти"/>

    </form>
    <form action="registration" method="get">
        <input type="hidden" name="command" value="GoToRegister"/>
        <input class="myButton" type="submit" value="Зареєструватися"/>
    </form>
<c:if test="${not empty errorMessage}">
    <p><fmt:message key="login.wrong.dates"/></p>
</c:if>
</div>
</body>
</html>
