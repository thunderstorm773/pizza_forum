<main>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form method="POST">
                    <label>Title</label>
                    <div class="form-group">
                        <input type="text" class="form-control" value="${topic.title}" disabled/>
                    </div>
                    <div class="form-group">
                        <label>Content</label>
                        <textarea class="form-control" disabled>${topic.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select name="category" class="form-control" disabled>
                            <option>${topic.categoryName}</option>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-danger" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</main>
