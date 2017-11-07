<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<main>
    <div class="container">
        <c:choose>
            <c:when test="${fn:length(categories) > 0}">
                <ul>
                    <c:forEach var="category" items="${categories}">
                        <li><a href="/home/categories/${category.id}/topics">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <h2 class="text-center text-uppercase text-danger">No available categories</h2>
            </c:otherwise>
        </c:choose>
    </div>
</main>
