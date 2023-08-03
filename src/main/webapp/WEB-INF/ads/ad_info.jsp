<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="col-md-9">
            <%-- Displaying the ad information --%>
            <h2>Title</h2>
            <h5>${ad.title}</h5>
            <h3>Description</h3>
            <p>${ad.description}</p>
            <div>
                <h3>Categories</h3>
                <c:forEach items="${categories}" var="category">
                    <div class="col-md-2">
                        <span class="fs-6">${category.name}</span>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-3">
            <%-- Displaying the user information --%>
            <h3>User</h3>
            <%--<p>User ID: <%= user.getId() %></p>--%>
            <p>Name: ${user.username}</p>
            <p>Email: ${user.email}</p>
        </div>
    </div>


</div>
<br>
<div class="container">
    <%-- Add a link to go back to the list of ads --%>
    <a href="/ads">Back to Ads List</a>
</div>
</body>
</html>
