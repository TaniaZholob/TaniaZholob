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
<%--                                <input type="radio" checked="checked" name="sort" value="name">--%>
                                <span class="checkmark"></span>
                            </label>
                            <label class="containerSort">${rating}
                                <input type="radio" name="sort" value="rating">
                                <span class="checkmark"></span>
                            </label>
                            <input type="submit">
                        </form>
                    </div>
<%--                    <div class="sort">--%>
<%--                        <h2>Фільтруємо</h2>--%>
<%--                    </div>--%>
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
                                    <p>Рейтинг: ${masterId.rating}</p>
                                </div>
                                <div class="feature-title">
                                    <div class="reviews">
                                        <a class="reviewsHref" href="#">Відгуки</a>
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






<%--<!-- Button trigger modal -->--%>
<%--<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">--%>
<%--    <div class="feature-title">--%>
<%--        <div class="reviews">--%>
<%--            <a class="reviewsHref"  href="#">Відгуки</a>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--</button>--%>
<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>--%>
<%--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <c:forEach items="${masterId.reviews}" var="review">--%>
<%--                    <p class="f"> ${review.user.firstName} ${review.user.lastName}</p>--%>
<%--                    <p class="f1">${review.review}</p>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
