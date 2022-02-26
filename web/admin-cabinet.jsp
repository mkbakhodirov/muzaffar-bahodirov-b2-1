<%--
  Created by IntelliJ IDEA.
  User: Muzaffar
  Date: 26.02.2022
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Cabinet</title>
</head>
<body>
<div class="container">
    <%

    %>
    <div class="row">
        <h3>Username: ${username}</h3>
    </div>
    <div class="row">
        <div class="col-md-4 offset-4">
            <div class="card">
                <div class="card-body">
                    <h3 class="text-center">Add book</h3>
                    <form action="/books/add" method="post">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input  type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
                            <span ${res}></span>
                        </div>
                        <div class="form-group">
                            <label for="author">Author</label>
                            <input type="text" class="form-control" id="author" name="author" placeholder="Enter author" required>
                        </div>
                        <div class="form-group">
                            <label for="price">Price</label>
                            <input type="text" class="form-control" id="price" name="price" placeholder="Enter price" required>
                        </div>
                        <button class="btn btn-dark" type="submit">Add book</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
