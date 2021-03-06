<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
        <div class="thumbnail">
            <h4><strong><a href="#">${topic.title}</a></strong></h4>
            <fmt:setLocale value="en_US" />
            <p><a href="${pageContext.request.contextPath}/forum/profile/${topic.authorId}">${topic.authorUsername}</a> <fmt:formatDate value="${topic.publishDate}" pattern="d MMM yyyy"/></p>
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
        <c:set var="replies" value="${topic.replies}"/>
        <c:choose>
            <c:when test="${fn:length(replies) > 0}">
                <c:forEach var="reply" items="${replies}">
                    <div class="thumbnail reply">
                        <p><a href="${pageContext.request.contextPath}/forum/profile/${reply.authorId}">${reply.authorUsername}</a> <fmt:formatDate value="${reply.publishDate}" pattern="d MMM yyyy"/></p>
                        <p>${reply.content}</p>
                        <div class="reply image">
                            <img src="${reply.imageURL}" />
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2 class="text-center text-uppercase text-danger">No available replies</h2>
            </c:otherwise>
        </c:choose>
        <c:if test="${loggedInUser != null}">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="thumbnail reply">
                        <form method="POST" action="${pageContext.request.contextPath}/topics/details/add-reply/${topic.id}">
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
                </div>
            </div>
        </c:if>
    </div>
</main>
