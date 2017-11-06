<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form method="POST">
                    <label>Title</label>
                    <div class="form-group">
                        <input type="text" name="title" class="form-control"
                               placeholder="Enter subject..." value="${topic.title}" required/>
                    </div>
                    <div class="form-group">
                        <label>Content</label>
                        <textarea name="content" class="form-control"
                                  placeholder="Enter your message..." required>${topic.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="category" class="form-control" required>
                            <c:forEach var="category" items="${categories}">
                                <c:choose>
                                    <c:when test="${category.name == topic.categoryName}">
                                        <option value="${category.id}" selected>${category.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.id}">${category.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-warning" value="Edit"/>
                </form>
            </div>
        </div>
    </div>
</main>
