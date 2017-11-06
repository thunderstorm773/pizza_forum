<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
    <div class="container">
        <h1>${user.username}</h1>
        <table class="table table-striped table-responsive">
            <caption>Topics</caption>
            <thead>
            <tr>
                <th>Title</th>
                <th>Category</th>
                <th>Date</th>
                <th>Replies Count</th>
                <c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
                <c:if test="${loggedInUser != null && (loggedInUser.role == 'ADMIN' || loggedInUser.username == user.username)}">
                    <th>Edit</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="topic" items="${user.topics}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/topics/details/${topic.id}">${topic.title}</a></td>
                    <td><a href="${pageContext.request.contextPath}/home/categories">${topic.categoryName}</a></td>
                    <td><fmt:formatDate value="${topic.publishDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${topic.repliesCount}</td>
                    <c:if test="${loggedInUser != null && (loggedInUser.role == 'ADMIN' || loggedInUser.username == user.username)}">
                        <td><a href="${pageContext.request.contextPath}/topics/edit/${topic.id}"
                               class="btn btn-warning">Edit</a></td>
                        <td><a href="${pageContext.request.contextPath}/topics/delete/${topic.id}"
                               class="btn btn-danger">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
