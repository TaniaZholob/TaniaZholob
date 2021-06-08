<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="MainPage"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>
<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>

<div class="meContainer">

    <div class="intro">
        <div class="meContainer">
            <div class="intro_inner">
                <h1 class="intro_title">Welcome to studio of beauty</h1>
            </div>
        </div>
        <div class="intro_slider">
            <div class="meContainer">
                <div class="slider_inner">
                    <div class="slider_item active">
                        <a class="nav_link" href="salon?command=sortMasters">${masters}</a>
                    </div>
                    <div class="slider_item">
                        <a class="nav_link" href="salon?command=gotProcedures">${procedures}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>