<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container">
        <ul>
            <c:forEach var="category" items="${categories}">
                <li><a href="/home/categories/${category.id}/topics">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</main>
