<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <form method="POST">
            <label>Name</label>
            <div class="form-group">
                <input type="text" name="name" class="form-control"
                       placeholder="Enter category name..." autofocus required/>
            </div>
            <input type="submit" class="btn btn-primary" value="Add Category"/>
        </form>
    </div>
</main>
