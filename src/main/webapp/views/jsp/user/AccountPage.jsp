<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Account"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="meContainer3">
    <div>
        <h1>Email: ${user.login}</h1>
        <h1><fmt:message key="user.name.surname"/>: ${user.firstName} ${user.lastName}</h1>
    </div>
    <h1><fmt:message key="master.records"/></h1>
    <div class="tablePagination">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>№</th>
                <th><fmt:message key="name.procedure"/></th>
                <th><fmt:message key="name.surname.masterId"/></th>
                <th><fmt:message key="time"/></th>
                <th><fmt:message key="status.payment"/></th>

            </tr>

            <%int i = 0;%>
            <c:forEach items="${allRecordsOfUser}" var="record">
                <%i++;%>
                <tr>
                    <td><%=i%>
                    </td>
                    <td><fmt:message key="procedure.${record.procedure}"/></td>
                    <td>${record.nameAndSurnameOfMAster}</td>
                    <td>${record.dataTime}</td>
                    <td><fmt:message key="user.payment.${record.paymentStatus}"/>
                        <c:if test="${record.paymentStatus=='opened'}">
                            <form action="admin">
                                <input type="hidden" name="command" value="goToAccountPage"/>
                                <input type="hidden" name="changePaymentStatus" value="${record.idOfRecord}"/>
                                <input type="hidden" name="status" value="${record.paymentStatus}"/>
                                <input class="myButton" type="submit" value='<fmt:message key="user.payment"/>'/>
                            </form>
                        </c:if>
                        <c:if test="${record.paymentStatus=='closed'}">
                            <form action="admin">
                                <input type="hidden" name="command" value="goToGiveFeedback"/>
                                <input type="hidden" name="id" value="${record.idOfRecord}"/>
                                <input type="hidden" name="masterNameSurname" value="${record.nameAndSurnameOfMAster}"/>
                                <input type="hidden" name="procedure" value="${record.procedure}"/>
                                <input class="myButton" type="submit" value='<fmt:message key="user.feedback"/>'/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
</body>
</html>
