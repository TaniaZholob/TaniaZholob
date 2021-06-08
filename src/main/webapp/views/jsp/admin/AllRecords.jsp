<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Records"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>

<div class="meContainer2">
    <div class="myPagination">


        <div class="title">
            <h1>Всі записи і їх статуси, пагінація)))</h1>
        </div>
        <div class="tablePagination">
            <table class="table table-striped table-bordered table-sm">
                <tr>
                    <th>№</th>
                    <th>Email</th>
                    <th><fmt:message key="name.surname.client"/></th>
                    <th><fmt:message key="name.surname.masterId"/></th>
                    <th><fmt:message key="name.procedure"/></th>
                    <th><fmt:message key="time"/></th>
                    <th><fmt:message key="status.payment"/></th>
                    <th><fmt:message key="status.perform"/></th>
                    <th><fmt:message key="record.edit"/></th>

                </tr>

                <c:forEach items="${all_records}" var="record">
                    <tr>
                        <td>${record.idOfRecord}</td>
                        <td>${record.emailOfUsers}</td>
                        <td>${record.nameAndSurnameOfUser}</td>
                        <td>${record.nameAndSurnameOfMAster}</td>
                        <td><fmt:message key="procedure.${record.procedure}"/>   </td>
                        <td>${record.dataTime}</td>
                        <td><fmt:message key="user.payment.${record.paymentStatus}"/>
                            <c:if test="${record.paymentStatus=='confirmed'}">
                                <form action="admin">
                                    <input type="hidden" name="command" value="goToAllRecords"/>
                                    <input type="hidden" name="currentPage" value="${currentPage}"/>
                                    <input type="hidden" name="changePaymentStatus" value="${record.idOfRecord}"/>
                                    <input type="hidden" name="status" value="${record.paymentStatus}"/>
                                    <input class="myButton" type="submit" value='<fmt:message key="confirm.payment"/>'/>
                                </form>
                            </c:if>
                        </td>
                        <td>${record.performStatus}</td>
                        <td><form action="admin">
                            <input type="hidden" name="command" value="goToEditRecord"/>
                            <input type="hidden" name="currentPage" value="${currentPage}"/>
                            <input type="hidden" name="changeTime" value="${record.idOfRecord}"/>
                            <input class="myButton" type="submit" value='<fmt:message key="record.change"/>'/>
                        </form></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <nav aria-label="Navigation for countries">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link"
                                             href="admin?command=goToAllRecords&currentPage=${currentPage-1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="admin?command=goToAllRecords&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="admin?command=goToAllRecords&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>

    </div>
</div>
</body>
</html>
