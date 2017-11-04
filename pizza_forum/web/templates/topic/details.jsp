<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
        <div class="thumbnail">
            <h4><strong><a href="#">${topic.title}</a></strong></h4>
            <p><a href="#">${topic.authorUsername}</a> <fmt:formatDate value="${topic.publishDate}" pattern="d MMM yyyy"/></p>
            <p>${topic.content}</p>
            <c:if test="${loggedInUser != null && (loggedInUser.role == 'ADMIN' || loggedInUser.username == topic.authorUsername)}">
                <div class="form-group">
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/topics/edit/${topic.id}" class="btn btn-warning">Edit</a>
                        <a href="${pageContext.request.contextPath}/topics/delete/${topic.id}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </c:if>
        </div>
        <c:forEach var="reply" items="${topic.replies}">
            <div class="thumbnail reply">
                <h5><strong><a href="#">${reply.authorUsername}</a></strong> <fmt:formatDate value="${reply.publishDate}" pattern="d MMM yyyy"/></h5>
                <p>${reply.content}</p>
                <div class="reply image">
                    <img src="${reply.imageURL}" />
                </div>
            </div>
        </c:forEach>
        <c:if test="${loggedInUser != null}">
            <div class="thumbnail reply">
                <form method="POST">
                    <div class="form-group">
                        <label>Content</label>
                        <textarea class="form-control" name="content"
                                  placeholder="Enter your reply..." required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Image URL</label>
                        <input type="text" name="imageURL" class="form-control" placeholder="http://..."/>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Reply"/>
                </form>
            </div>
        </c:if>
    </div>
</main>
