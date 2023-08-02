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
            <div class="col-md-6">
                <h2 class="text-secondary">${ad.title}</h2>
                <p class="text-secondary">${ad.description}</p>
                <a href="/ads/show?id=${ad.id}">Show</a>
                <a href="/deleteAd?id=${ad.id}">Delete</a>
            </div>

        </c:forEach>
    </div>
    <jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
