<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update an Ad" />
    </jsp:include>
</head>
<body>
    <div>
    <h2>Update your Ad</h2>
        <form action="/ads/updateAd" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title"
                value="${ad.getTitle()}" name="updateTitle"
                       class="form-control">
            </div>
             <div class="form-group">
                <label for="description">Description</label>
                <input name="updateDescription" id="description"
                class="form-control" type="text"
                       value="${ad.getDescription()}">
            </div>
                <input type="hidden" name="adId" value="${ad.getId()}">
                <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</body>
</html>
