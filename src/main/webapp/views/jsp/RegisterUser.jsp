<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Register" />
<%@ include file="/views/jsp/jspf/head.jspf" %>

<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">

    <form class="logForm" action="controller" method="post">
        <h1 class="h1R" title="Форма реєстрації на сайті"><fmt:message key="registration"/></h1>
        <input type="hidden" name="command" value="register"/>

        <table style="with: 80%">
            <tr>
                <td title="Введіть свій емаіл"><fmt:message key="register.email"/></td>
                <td><input type="email" name="login" required/></td>
            </tr>
            <tr>
                <td><fmt:message key="name"/></td>
                <td><input type="text" name="firstName" required/></td>
            </tr>
            <tr>
                <td><fmt:message key="surname"/></td>
                <td><input type="text" name="lastName" required/></td>
            </tr>
            <tr>
                <td><fmt:message key="register.password"/></td>
                <td><input type="password" name="password" required/></td>
            </tr>
<%--            <tr>--%>
<%--                <td><fmt:message key="register.password.repeat"/></td>--%>
<%--                <td><input type="password" name="password" required/></td>--%>
<%--            </tr>--%>
        </table>
        <input class="myButton" type="submit" value='<fmt:message key="register.do"/>'/>
    </form>
</div>
</body>
</html>
