<%@ page import="com.codeup.adlister.models.Ad" %>
<%@ page import="com.codeup.adlister.dao.*" %>
<%@ page import="com.codeup.adlister.models.User" %><%--
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

<h1>Ad Information</h1>

<%--<% Ad ad = (Ad) request.getAttribute("ad"); %>--%>

<%--<% User user = (User) request.getAttribute("user"); %>--%>

<%-- Displaying the ad information --%>
<h2>Title: ${ad.title}</h2>
<p>Description:  ${ad.description}</p>

<%-- Displaying the user information --%>
<h2>User Information</h2>
<%--<p>User ID: <%= user.getId() %></p>--%>
<p>Name: ${user.username}</p>
<p>Email: ${user.email}</p>

<%-- Add a link to go back to the list of ads --%>
<a href="/ads">Back to Ads List</a>
</body>
</html>
