<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Order"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">
    <form class="formProcedure" method="post" action="user?command=goToOrderMasters">
        <h1><fmt:message key="menu.procedures"/></h1>

        <select class="selectProc" size="5" class="selectpicker" name="procedure" data-style="btn-warning" required>
            <option disabled>Select service</option>
            <c:forEach items="${all_procedures}" var="procedure">

                <div class="feature">
                    <option class="procedureOption" value="${procedure.title}">
                        <div class="feature-title">
                            <h2>
                                <fmt:message key="procedure.${procedure.title}"/></h2>
                        </div>
                    </option>
                    <div class="feature-title">
                        <p>${procedure.price}</p>
                    </div>
                </div>
                </option>
            </c:forEach>

        </select>
        <p><input type="submit" class="btn btn-warning"
                  value='<fmt:message key="record.user.select"/> '/></p>
    </form>


</div>
</body>
</html>
