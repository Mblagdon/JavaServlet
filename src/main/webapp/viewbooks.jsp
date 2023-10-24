<%--
  Created by IntelliJ IDEA.
  User: Marcu
  Date: 2023-10-16
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.javaassignment2.Book" %>
<%@ page import="com.example.javaassignment2.Author" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>List of Books</h2>
<ul>
    <% List<Book> bookList = (List<Book>) request.getAttribute("bookList");
        for(Book book : bookList) { %>
    <li><%= book.getTitle() %> by
        <% for(Author author : book.getAuthorList()) { %>
        <%= author.getFirstName() %> <%= author.getLastName() %>
        <% if(!author.equals(book.getAuthorList().get(book.getAuthorList().size() - 1))) { %>
        ,
        <% } %>
        <% } %>
    </li>
    <% } %>
</ul>
<a href="index.jsp">Back to main menu</a>
</body>
</html>

