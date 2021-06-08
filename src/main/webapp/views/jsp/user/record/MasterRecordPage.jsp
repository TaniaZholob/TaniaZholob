<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Order"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="conteiner" align="center">
    <form method="post" action="user?command=goToOrderTime">
        <p><fmt:message key="menu.masters"/></p>
        <select size="5" class="selectpicker" name="masterId" data-style="btn-warning" required>
            <option disabled>Select service</option>
            <c:forEach items="${all_masters_pr}" var="masterId">
                <div class="feature">
                    <option value="${masterId.id}">
                        <div class="feature-title">
                            <h2>${masterId.name}</h2>
                        </div>

                        <div class="feature-title">
                            <p>${masterId.surname}</p>
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
