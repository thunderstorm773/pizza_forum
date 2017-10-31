<main>
    <div class="container">
        <jsp:include page="../../fragments/errors.jsp"/>
        <form method="POST">
            <label>Username</label>
            <div class="form-group">
                <input type="text" name="username" class="form-control"
                       placeholder="Enter username or email" autofocus required />
            </div>
            <label>Password</label>
            <div class="form-group">
                <input type="password" name="password" class="form-control"
                       placeholder="Enter password" required />
            </div>
            <a href="${pageContext.request.contextPath}/forum/register" class="btn btn-primary">Register</a>
            <input type="submit" class="btn btn-success" value="Log In"/>
        </form>
    </div>
</main>
