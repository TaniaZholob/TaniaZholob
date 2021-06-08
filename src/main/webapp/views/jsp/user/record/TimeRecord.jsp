<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<html>
<c:set var="title" value="Order"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">
    <form class="meContainer" method="post" action="user?command=recordEnd">
        <%
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate2 = LocalDate.now().plusMonths(2);
            String formattedString = localDate.format(formatter);
            String formattedString2 = localDate2.format(formatter);
        %>
        <p><fmt:message key="menu.procedures"/></p>
        <label>
            <input type="date" name="data" value="<%=formattedString%>" min="<%=formattedString%>"
                   max="<%=formattedString2%>"/>
            <input type="time" name="time" list="time" max="17:00" min="8:00"></p>
            <datalist id="time">
                <option value="08:00">
                <option value="9:00">
                <option value="10:00">
                <option value="11:00">
                <option value="10:00">
                <option value="13:00">
                <option value="14:00">
                <option value="15:00">
                <option value="16:00">
                <option value="17:00">
            </datalist>
        </label>
        <input class="myButton" type="submit">
    </form>
    <c:if test="${not empty wrongData}">
        <h1> Виберіть іншу дату</h1>
    </c:if>
</div>
</body>
</html>
