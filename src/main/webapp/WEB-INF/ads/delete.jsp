<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Delete an Ad"/>
    </jsp:include>
</head>
<body>
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
<<<<<<< HEAD
    </div>

    <div id="confirm-msg">
        Ad successfully deleted!
    </div>

    <jsp:include page="/WEB-INF/partials/error_message.jsp"/>
=======
        <input class="visually-hidden" type="text" name="id" id="id" value="${ad.getId()}">
        <input type="submit" class="btn btn-danger btn-block">
    </form>
</div>
<jsp:include page="/WEB-INF/partials/error_message.jsp"/>
>>>>>>> master
</body>
</html>
