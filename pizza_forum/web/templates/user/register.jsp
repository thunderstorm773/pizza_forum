<main>
    <div class="container body-content">
        <jsp:include page="../../fragments/errors.jsp"/>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form method="POST">
                    <label>Username</label>
                    <div class="form-group">
                        <input type="text" name="username" class="form-control"
                               placeholder="Enter username" autofocus required/>
                    </div>
                    <label>Email</label>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control"
                               placeholder="Enter email" required/>
                    </div>
                    <label>Password</label>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control"
                               placeholder="Enter password" required/>
                    </div>
                    <label>Confirm Password</label>
                    <div class="form-group">
                        <input type="password" name="confirmPassword" class="form-control"
                               placeholder="Confirm password" required/>
                    </div>
                    <input type="reset" class="btn btn-danger" value="Clear"/>
                    <input type="submit" class="btn btn-success" value="Register"/>
                </form>
            </div>
        </div>
    </div>
</main>
