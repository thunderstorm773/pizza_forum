<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <a href="${pageContext.request.contextPath}/categories/new" class="btn btn-success">New Category</a>
        <table class="table table-striped">
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
    </div>
</main>
