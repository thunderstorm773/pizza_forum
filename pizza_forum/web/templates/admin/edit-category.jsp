<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <form method="POST">
            <label>Name</label>
            <div class="form-group">
                <input type="text" name="name" class="form-control"
                       value="${category.name}" placeholder="Enter new category name" required/>
            </div>
            <input type="submit" class="btn btn-warning" value="Edit Category"/>
        </form>
    </div>
</main>
