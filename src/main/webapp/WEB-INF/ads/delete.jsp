<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Delete an Ad" />
    </jsp:include>
</head>
<body>
    <p>${ad.getId()}</p>
    <p>${ad.getDescription()}</p>

    <button onclick="confirmDelete()">Delete</button>

    <!-- Confirmation Prompt -->
    <div id="delete" class="delete">
        <div class="delete-msg">
            <p>Are you sure you want to delete this ad?</p>
            <button onclick="deleteAd()">Confirm Delete</button>
            <button onclick="cancelDelete()">Cancel</button>
        </div>
    </div>

    <div id="confirm-msg">
        Ad successfully deleted!
    </div>

    <jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
