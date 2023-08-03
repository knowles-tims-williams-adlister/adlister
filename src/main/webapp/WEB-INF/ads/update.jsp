<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update an Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h2>Update Your Ad</h2>
    <form action="/ads/updateAd" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" id="title" name="title"
                   value="${ad.title}" class="form-control">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea name="description" id="description" cols="30"
             rows="15" class="form-control">${ad.description}</textarea>
        </div>
            <input class="d-none" type="text" name="id" id="id"
             value="${ad.getId()}">
            <button type="submit" id="updateButton" name="updateButton"
            value="update" class="btn btn-block btn-primary">Update
            Ad</button>
            <button type="submit" id="cancelButton" name="cancelButton"
            value="cancel" class="btn btn-block btn-primary">Cancel</button>
    </form>
</div>
<jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
