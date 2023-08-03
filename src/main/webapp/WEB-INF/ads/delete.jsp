<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Delete an Ad"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
<%--    <% request.setAttribute("adId", request.getParameter("adId"));%>--%>
    <h3>${ad.getTitle()}</h3>
    <p>${ad.getDescription()}</p>
    <!-- Confirmation Prompt -->
    <form action="/deleteAd" method="post">
        <div class="form-group">
            <label for="delete">Type "Delete" In Field To DELETE Account</label>
            <input id="delete" name="delete" class="form-control" type="text">
        </div>
        <input class="visually-hidden" type="text" name="id" id="id" value="${ad.getId()}">
        <button type="submit" value="submit" name="deleteButton" id="deleteButton" class="btn btn-danger btn-block">
        <button type="submit" id="cancelButton" name="cancelButton"
                value="cancel" class="btn btn-block btn-primary">Cancel</button>
    </form>
</div>
<jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
