<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Order"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">
    <form class="formProcedure"  method="post" action="user?command=goToOrderTime">
        <h1><fmt:message key="menu.masters"/></h1>
        <select class="selectProc" size="5" class="selectpicker" name="master" data-style="btn-warning" required>
            <option disabled>Select service</option>
            <c:forEach items="${all_masters_pr}" var="master">
                <div class="feature">
                    <option class="procedureOption" value="${master.id}">
                        <div class="feature-title">
                            <h2>${master.name}</h2>
                        </div>

                        <div class="feature-title">
                            <p>${master.surname}</p>
                        </div>
                    </option>
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
