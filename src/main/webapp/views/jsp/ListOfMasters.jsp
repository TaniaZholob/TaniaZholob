<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/jsp/jspf/derective/taglib.jspf" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Masters"/>
<%@ include file="/views/jsp/jspf/head.jspf" %>

<body>
<%@ include file="/views/jsp/jspf/header.jspf" %>
<div class="meContainer">
    <div class="info_for_guest">
        <div class="blockInformation">
            <div class="blockMasters">
                <div class="sort_navigation">
                    <div class="sort">
                        <h2>${sort}</h2>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="sortMasters"/>
                            <label class="containerSort">${name}
                                <input type="radio" name="sort" value="name">
                                <span class="checkmark"></span>
                            </label>
                            <label class="containerSort">${rating}
                                <input type="radio" name="sort" value="rating">
                                <span class="checkmark"></span>
                            </label>
                            <input class="myButton" type="submit" value='<fmt:message key="sort"/>'/>
                        </form>
                    </div>

                </div>
                <div class="masters_items" style="overflow-y:scroll; height:450px;">
                    <c:forEach items="${all_masters}" var="masterId">
                        <div class="feature">
                            <div class="featureNameSurname">
                                <div class="feature-title">
                                    <h2>${masterId.name}</h2>
                                </div>
                                <div class="feature-title">
                                    <p>${masterId.surname}</p>
                                </div>
                            </div>
                            <div class="review">
                                <div class="feature-title">
                                    <p><fmt:message key="rating"/>: ${masterId.rating}</p>
                                </div>
                                <div class="feature-title">
                                    <div class="reviews">
                                        <a class="reviewsHref" href="#"><fmt:message key="review"/></a>
                                        <div class="allReviews">
                                            <c:forEach items="${masterId.reviews}" var="review">
                                                <p class="f"> ${review.user.firstName} ${review.user.lastName}</p>
                                                <p class="f1">${review.review}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


