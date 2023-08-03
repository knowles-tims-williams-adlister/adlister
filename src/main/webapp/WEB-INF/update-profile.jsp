<%--
  Created by IntelliJ IDEA.
  User: alaniz
  Date: 7/31/23
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

<main>
    <div class="container">
        <h1>${sessionScope.user.username}</h1>
    </div>
    <jsp:include page="/WEB-INF/partials/update_username.jsp"/>
    <jsp:include page="/WEB-INF/partials/update_email.jsp"/>
    <jsp:include page="/WEB-INF/partials/update_password.jsp"/>
    <jsp:include page="/WEB-INF/partials/delete_user.jsp"/>
</main>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
<jsp:include page="/WEB-INF/partials/error_message.jsp"/>
</body>
</html>
