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
    <h2>Remove Ad</h2>
    <form action="/ads/update" method="post" id="updateAd">
        <input type="hidden" name="adId" value="${ad.getId()}">
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="${fn:escapeXml(newTitle)}">
        <br>
        <label for="description">Description</label>
        <textarea name="description" id="description" cols="15" rows="15">${fn:escapeXml(newDescription)}</textarea>
        <br>
        <button type="submit" id="updateButton">Update Ad</button>
        <button type="button" id="cancelButton">Cancel</button>
    </form>
    <div id="confirmation-message">Successfully updated!</div>
</body>
</html>
