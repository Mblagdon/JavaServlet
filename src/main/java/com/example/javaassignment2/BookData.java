//Line to ensure saved
package com.example.javaassignment2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookCollection", value = "/BookCollection")
public class BookData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = new ArrayList<>();

        try {
            // Establishing a connection to the database
            Connection conn = DBConnection.getBooksDatabaseConnection();

            // SQL query to fetch all books
            String bookQuery = "SELECT * FROM titles";
            PreparedStatement bookStmt = conn.prepareStatement(bookQuery);
            ResultSet bookRs = bookStmt.executeQuery();

            while (bookRs.next()) {
                Book book = new Book(
                        bookRs.getString("isbn"),
                        bookRs.getString("title"),
                        bookRs.getInt("editionNumber"),
                        bookRs.getString("copyright")
                );

                // Fetch associated authors for the current book
                String authorQuery = "SELECT a.* FROM authors a JOIN authorisbn ai ON a.authorID = ai.authorID WHERE ai.isbn = ?";
                PreparedStatement authorStmt = conn.prepareStatement(authorQuery);
                authorStmt.setString(1, book.getIsbn());
                ResultSet authorRs = authorStmt.executeQuery();

                List<Author> authorsForBook = new ArrayList<>();
                while (authorRs.next()) {
                    Author author = new Author(
                            authorRs.getInt("authorID"),
                            authorRs.getString("firstName"),
                            authorRs.getString("lastName")
                    );
                    authorsForBook.add(author);
                }
                book.setAuthorList(authorsForBook);

                bookList.add(book);
            }

            // Forwarding the list to the JSP for rendering
            request.setAttribute("bookList", bookList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewbooks.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
