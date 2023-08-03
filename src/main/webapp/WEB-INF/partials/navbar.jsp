<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="../../css/style.css">
<link href="https://fonts.googleapis.com/css2?family=Borel&display=swap" rel="stylesheet">
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <img src="../../images/cat.png" alt="cat with shades icon">
            <a class="navbar-brand" href="/ads">CatPack</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <c:choose>
                        <c:when test="${fn:contains(pageContext.request.requestURL, '/profile')}">
                            <li><a href="/update-profile">Update Profile</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/profile">Profile</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${fn:contains(pageContext.request.requestURL, '/create')}">
                            <li><a href="/logout">Logout</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/ads/create">Create Ad</a></li>
                            <li><a href="/logout">Logout</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${fn:contains(pageContext.request.requestURL, '/login')}">
                            <li><a href="/register">Register</a></li>
                        </c:when>
                        <c:when test="${fn:contains(pageContext.request.requestURL, '/register')}">
                            <li><a href="/login">Login</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/register">Register</a></li>
                            <li><a href="/login">Login</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
