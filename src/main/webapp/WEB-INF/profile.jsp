<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <div class="col-md-8 m-0">
            <h1>Welcome, ${sessionScope.user.username}!</h1>
        </div>
        <div class="col-md-3 mt-4">
            <div class="align-text-bottom fs-6">${sessionScope.user.email}</div>
        </div>
    </div>
    <div class="mt-1 mb-5">
        <hr>
    </div>
    <div class="container">
        <h1>Your Ads</h1>
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6 ad-space">
                <a href="/ads/show?id=${ad.id}" class="show-ad"><h2 class="text-secondary">${ad.title}</h2></a>
                <p class="text-secondary larger-p">${ad.description}</p>
                <a href="/ads/updateAd?id=${ad.id}" class="btn btn-primary ad-space">Update</a>
                <a href="/deleteAd?id=${ad.id}" class="btn btn-danger ad-space">Delete</a>
<%--                <div class="col-md-5">--%>
<%--                    <form action="/ads/updateAd?id=${ad.id}">--%>
<%--                        <button class="btn btn-primary btn-block ad-space">Update</button>--%>
<%--                    </form>--%>
<%--                    </div>--%>
<%--                    <div class="col-md-5">--%>
<%--                    <form action="/deleteAd?id=${ad.id}">--%>
<%--                        <button class="btn btn-danger btn-block">Delete</button>--%>
<%--                    </form>--%>
<%--                </div>--%>
            </div>

        </c:forEach>
    </div>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
