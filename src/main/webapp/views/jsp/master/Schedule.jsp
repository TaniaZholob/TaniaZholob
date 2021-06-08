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
        <h1>Name and Surname: ${user.firstName} ${user.lastName}</h1>
    </div>
    <h1>Your records</h1>
    <div class="tablePagination">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>№</th>
                <th><fmt:message key="name.procedure"/></th>
                <th><fmt:message key="name.surname.client"/></th>
                <th><fmt:message key="time"/></th>
                <th><fmt:message key="status.perform"/></th>

            </tr>

            <%int i = 0;%>
            <c:forEach items="${allRecordsOfMaster}" var="record" >
                <%i++;%>
                <tr>
                    <td><%=i%></td>
                    <td> <fmt:message key="procedure.${record.procedure}"/></td>
                    <td>${record.nameAndSurnameOfUser}</td>
                    <td>${record.dataTime}</td>
                    <td><fmt:message key="masterId.perform.${record.performStatus}"/>
                        <c:if test="${record.performStatus=='active'}">
                            <form action="masterId">
                                <input type="hidden" name="command" value="performRecord"/>
                                <input type="hidden" name="changePerformStatus" value="${record.idOfRecord}"/>
                                <input class="myButton" type="submit" value='<fmt:message key="masterId.perform"/>'/>
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
