<%--
  Created by IntelliJ IDEA.
  User: jemalknowles1
  Date: 7/31/23
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Ad</title>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Show ad information" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">

    <h1>Ad Information</h1>
    <hr>
    <div class="container">
        <div class="col-md-7">
            <%-- Displaying the ad information --%>
            <h2>Title</h2>
            <h4>${ad.title}</h4>
            <h5>Description</h5>
            <p>${ad.description}</p>
        </div>
        <div class="col-md-3">
            <%-- Displaying the user information --%>
            <h3>User</h3>
            <%--<p>User ID: <%= user.getId() %></p>--%>
            <p>Name: ${user.username}</p>
            <p>Email: ${user.email}</p>
        </div>
    </div>

    <%-- Add a link to go back to the list of ads --%>
    <a href="/ads">Back to Ads List</a>
</div>
</body>
</html>
