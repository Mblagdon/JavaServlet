<%--
  Created by IntelliJ IDEA.
  User: Marcu
  Date: 2023-10-16
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Add a New Author</h2>
<form action="AddAuthor" method="post">
    First Name: <input type="text" name="firstName"><br>
    Last Name: <input type="text" name="lastName"><br>
    <input type="submit" value="Add Author">
</form>
<a href="index.jsp">Back to main menu</a>
</body>
</html>
