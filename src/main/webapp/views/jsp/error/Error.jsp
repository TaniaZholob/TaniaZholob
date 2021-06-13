<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Error"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>

<div class="meContainer3">


    <tr>
        <td class="content">
            <h1>Error</h1>

            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>

            <c:if test="${not empty message}">
                <h3>Message: ${message}</h3>
            </c:if>

            <c:if test="${not empty errorMessage and empty exception and empty code}">
                <h3>Error message: ${errorMessage}</h3>
            </c:if>

            <c:if test="${not empty exception}">
                <hr/>
                <h3>Stack trace:</h3>
                <c:forEach var="stackTraceElement" items="${exception.stackTrace}">
                    ${stackTraceElement}
                </c:forEach>
            </c:if>

        </td>
    </tr>
</div>
</body>
</html>
