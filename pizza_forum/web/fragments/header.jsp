<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="navbar-wrapper">
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="container">
                    <c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
                    <div id="navbar">
                        <ul class="nav navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/home/topics">Topics</a></li>
                            <c:if test="${loggedInUser != null}">
                                <li><a href="${pageContext.request.contextPath}/home/categories">Categories</a></li>
                            </c:if>
                            <c:choose>
                                <c:when test="${loggedInUser != null && loggedInUser.role == 'ADMIN'}">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                           aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li class=""><a href="${pageContext.request.contextPath}/categories/all">Categories</a></li>
                                        </ul>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                        <ul class="signmenu nav navbar-nav">
                            <c:choose>
                                <c:when test="${loggedInUser != null}">
                                    <li><span class="navbar-text">Hello, <a href="${pageContext.request.contextPath}/forum/profile/${loggedInUser.id}">${loggedInUser.username}</a></span></li>
                                    <li><span class="navbar-text"><a href="${pageContext.request.contextPath}/forum/logout">Log out</a></span></li>
                                </c:when>
                                <c:otherwise>
                                    <li><span class="navbar-text">Hello, <a href="${pageContext.request.contextPath}/forum/login">Log in</a></span></li>
                                    <li><span class="navbar-text"><a href="${pageContext.request.contextPath}/forum/register">Register</a></span></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</header>
