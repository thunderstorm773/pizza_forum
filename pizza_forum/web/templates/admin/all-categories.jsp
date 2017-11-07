<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container">
        <a href="${pageContext.request.contextPath}/categories/new" class="btn btn-success">New Category</a>
        <c:choose>
            <c:when test="${fn:length(categories) > 0}">
                <table class="table table-striped table-responsive-sm">
                    <caption>Categories</caption>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/home/categories/${category.id}/topics">${category.name}</a></td>
                            <td><a href="${pageContext.request.contextPath}/categories/edit/${category.id}"
                                   class="btn btn-primary">Edit</a></td>
                            <td><a href="${pageContext.request.contextPath}/categories/delete/${category.id}"
                                   class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h2 class="text-center text-uppercase text-danger">No available categories</h2>
            </c:otherwise>
        </c:choose>
    </div>
</main>
