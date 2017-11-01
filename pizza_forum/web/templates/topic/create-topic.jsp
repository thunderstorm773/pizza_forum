<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <form method="POST">
            <label>Title</label>
            <div class="form-group">
                <input type="text" name="title" class="form-control"
                       placeholder="Enter subject..." autofocus required/>
            </div>
            <div class="form-group">
                <label>Content</label>
                <textarea name="content" class="form-control"
                          placeholder="Enter your message..." required></textarea>
            </div>
            <div class="form-group">
                <label>Category</label>
                <select name="category" class="form-control" required>
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" class="btn btn-primary" value="Publish"/>
        </form>
    </div>
</main>
