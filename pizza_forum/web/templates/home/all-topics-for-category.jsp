<main>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <main>
        <div class="container">
            <c:forEach var="topic" items="${topics}">
                <div class="thumbnail">
                    <h4>
                        <strong><a href="${pageContext.request.contextPath}/topics/details/${topic.id}">${topic.title}</a></strong>
                        <small><a href="${pageContext.request.contextPath}/home/categories">${topic.categoryName}</a></small>
                    </h4>
                    <p><a href="#">${topic.authorUsername}</a> | Replies: ${topic.repliesCount} | <fmt:formatDate value="${topic.publishDate}" pattern="d MMM yyyy"/></p>
                </div>
            </c:forEach>
        </div>
    </main>
</main>