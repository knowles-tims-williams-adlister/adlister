<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update an Ad" />
    </jsp:include>
</head>
<body>
    <h2>Remove Ad</h2>
    <form action="/ads/update" method="post" id="updateAd">
        <input type="hidden" id="adId" value="${ad.getId()}">
        <label for="title">${ad.getTitle()}</label>
        <input type="text" id="title" value="${ad.getDescription()}">
        <br>
        <label for="description">Description</label>
        <textarea name="description" id="description" cols="15"
        rows="15">${ad.getDescription()}"</textarea>
        <br>
        <button type="button" id="updateButton">Update Ad</button>
        <button type="button" id="cancelButton">Cancel</button>
    </form>
    <div id="confirmation-message">Successfully updated!</div>
</body>
</html>