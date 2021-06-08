<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Account"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>

<div class="meContainer3">

    <form class="formFeedback" action="admin" method="post" oninput="daysoutput.value=dayscount.value">
        <input type="hidden" name="command" value="giveFeedback"/>
        <input type="hidden" name="masterNameSurname" value="${masterNameSurname}"/>
        <input type="hidden" name="id" value="${id}"/>


        <fieldset class="fieldS">
            <legend><fmt:message key="user.personal.data"/></legend>
            <div class="half-width">
                <label class="feedBackLable" >Email</label>
                <input class="feedBackInput" type="text" value="${user.login}" disabled="">
            </div>
            <div class="half-width">
                <label class="feedBackLable" for="1"><fmt:message key="user.name.surname"/></label>
                <input class="feedBackInput" id="1" type="text" value="${user.firstName} ${user.lastName}" disabled="">
            </div>
        </fieldset>
        <fieldset class="fieldS">
            <legend><fmt:message key="feedback.phrase"/></legend>
            <div class="half-width">
                <label class="feedBackLable" for="2"><fmt:message key="name.procedure"/></label>
                <input class="feedBackInput" id="2" type="text" value="<fmt:message key="procedure.${procedure}"/>"  disabled="">
            </div>
            <div class="half-width">
                <label class="feedBackLable" for="3"><fmt:message key="name.surname.masterId"/></label>
                <input class="feedBackInput" id="3" type="text" value="${masterNameSurname}" disabled="">
            </div>
            <div class="half-width">
                <label class="feedBackLable" ><fmt:message key="feedback.evaluation"/></label>
                0 <input class="feedBackInput" type="range" min="0" max="10" step="1" name="dayscount" value="10"> 10
            </div>
            <div class="half-width output-area">
                <output class="feedBackOutPut" name="daysoutput">10</output>
            </div>
            <label class="feedBackLable" for="4"><fmt:message key="feedback.text"/></label>
            <textarea class="feedBackTextArea" id="4" rows="5" name="textOfFeedback"><fmt:message key="feedback.text.enter"/></textarea>
        </fieldset>
        <div class="buttons">
            <input class="myButton" type="submit" value="<fmt:message key="feedback.send"/>">
        </div>
    </form>


</div>

</body>
</html>
