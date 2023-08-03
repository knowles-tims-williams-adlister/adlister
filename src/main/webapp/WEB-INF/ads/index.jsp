<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h2>Ad Search</h2>
    <form action="/search" method="post">
        <div class="form-group">
            <label for="search">Search by ad title</label>
            <input id="search" name="keyword" class="form-control" type="text"
                   placeholder="title">
        </div>
        <input type="submit" class="btn btn-primary btn-block">
    </form>
</div>
<div class="container">
    <h1>Here Are all the ads!</h1>
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <a class="text-decoration-none text-dark " href="/ads/show?id=${ad.id}">See More</a>
        </div>
    </c:forEach>
</div>
<jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
