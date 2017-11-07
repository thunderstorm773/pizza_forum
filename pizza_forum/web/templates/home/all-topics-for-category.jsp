<main>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <main>
        <div class="container">
            <c:choose>
                <c:when test="${fn:length(topics) > 0}">
                    <c:forEach var="topic" items="${topics}">
                        <div class="thumbnail">
                            <h4>
                                <strong><a href="${pageContext.request.contextPath}/topics/details/${topic.id}">${topic.title}</a></strong>
                                <small><a href="${pageContext.request.contextPath}/home/categories">${topic.categoryName}</a></small>
                            </h4>
                            <fmt:setLocale value="en_US" />
                            <p><a href="${pageContext.request.contextPath}/forum/profile/${topic.authorId}">${topic.authorUsername}</a> | Replies: ${topic.repliesCount} | <fmt:formatDate value="${topic.publishDate}" pattern="d MMM yyyy"/></p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2 class="text-center text-uppercase text-danger">No available topics</h2>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</main>
