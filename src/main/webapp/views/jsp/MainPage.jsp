<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


<head>
    <title>Салон краси</title>
    <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Montserrat:ital,wght@0,200;0,400;0,700;1,100&display=swap"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="views/Style/css.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <c:if test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>


    <fmt:setBundle basename="app"/>
    <fmt:message key="header.button.ua" var="button_ua"/>
    <fmt:message key="header.button.en" var="button_en"/>
    <fmt:message key="hello" var="hello"/>
    <fmt:message key="log_in" var="log_in"/>
    <fmt:message key="log_out" var="log_out"/>
    <fmt:message key="registration" var="registration"/>
    <fmt:message key="information" var="information"/>
    <fmt:message key="contacts" var="contacts"/>
    <fmt:message key="list.of.masters" var="masters"/>
    <fmt:message key="list.of.procedures" var="procedures"/>
    <fmt:message key="beauty.salon" var="saloon"/>


</head>
<body>
<header class="header">
    <div class="meContainer">
        <div class="header_inner">
            <div class="header_logo"><h1>${saloon}</h1></div>
            <nav class="nav">
                <a class="nav_link" href="controller?command=login">${log_in}</a>
                <a class="nav_link" href="#">${information}</a>
                <a class="nav_link" href="#">${contacts}</a>
                <form action="controller?command=locale" method="post">
                    <button class="nav_link" type="submit" name="locale" value="ua">${button_ua}</button>
                    <button class="nav_link" type="submit" name="locale" value="en">${button_en}</button>
                </form>
            </nav>
        </div>
    </div>
</header>
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
                        <a class="nav_link" href="#block1">${masters}</a>
                    </div>
                    <div class="slider_item">
                        <a class="nav_link" href="#block2">${procedures}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="meContainer">
    <div class="info_for_guest">
        <div class="blockInformation" id="block1">
            <div class="blockMasters">
                <div class="sort_navigation">
                    <h2>Сортуємо</h2>
                    <h2>Фільтруємо</h2>
                </div>
                <div class="masters_items">
                    <c:forEach items="${all_masters}" var="master" end="3">
                        <div class="feature">
                            <div class="feature-title">
                                <h2>${master.name}</h2>
                            </div>
                            <div class="feature-info">
                                <p>${master.surname}</p>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </div>

        </div>
        <div class="blockInformation" id="block2">Блок 2</div>
    </div>
</div>

</body>
</html>