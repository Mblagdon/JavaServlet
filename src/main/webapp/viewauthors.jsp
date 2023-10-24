<%--
  Created by IntelliJ IDEA.
  User: Marcu
  Date: 2023-10-16
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.javaassignment2.Author" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>List of Authors</h2>
<ul>
    <% List<Author> authorList = (List<Author>) request.getAttribute("authorList");
        for(Author author : authorList) { %>
    <li><%= author.getFirstName() %> <%= author.getLastName() %></li>
    <% } %>
</ul>
<a href="index.jsp">Back to main menu</a>
</body>
</html>

