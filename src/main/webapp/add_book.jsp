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
<h2>Add a New Book</h2>
<form action="AddBook" method="post">
    ISBN: <input type="text" name="isbn"><br>
    Title: <input type="text" name="title"><br>
    Edition Number: <input type="number" name="editionNumber"><br>
    Copyright: <input type="text" name="copyright"><br>
    Author First Name: <input type="text" name="authorFirstName"><br>
    Author Last Name: <input type="text" name="authorLastName"><br>
    <input type="submit" value="Add Book">
</form>
<a href="index.jsp">Back to main menu</a>
</body>
</html>
