<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>

<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Create a new Ad</h1>
    <form action="/ads/create" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" class="form-control" type="text" value="${fn:escapeXml(requestScope.title)}">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description" class="form-control" type="text">${fn:escapeXml(requestScope.description)}</textarea>
        </div>
        <div class="form-group">
            <br>
            <c:forEach items="${allCategories}" var="category">
                <div class="col-md-1">
                    <input type="checkbox" name="categories" id="category${category.id}" value="${category.id}">
                    <label for="category${category.id}"> ${category.name} </label>
                </div>
            </c:forEach>
        </div>
        <input type="submit" class="btn btn-block btn-primary">
    </form>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</div>
</body>
</html>
