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

@WebServlet(name = "AuthorCollection", value = "/AuthorCollection")
public class AuthorData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authorList = new ArrayList<>();

        try {
            // Establishing a connection to the database
            Connection conn = DBConnection.getBooksDatabaseConnection();

            // SQL query to fetch all authors
            String authorQuery = "SELECT * FROM authors";
            PreparedStatement authorStmt = conn.prepareStatement(authorQuery);
            ResultSet authorRs = authorStmt.executeQuery();

            while (authorRs.next()) {
                Author author = new Author(
                        authorRs.getInt("authorID"),
                        authorRs.getString("firstName"),
                        authorRs.getString("lastName")
                );

                // Fetch associated books for the current author
                String bookQuery = "SELECT t.* FROM titles t JOIN authorisbn ai ON t.isbn = ai.isbn WHERE ai.authorID = ?";
                PreparedStatement bookStmt = conn.prepareStatement(bookQuery);
                bookStmt.setInt(1, author.getId());
                ResultSet bookRs = bookStmt.executeQuery();

                while (bookRs.next()) {
                    Book book = new Book(
                            bookRs.getString("isbn"),
                            bookRs.getString("title"),
                            bookRs.getInt("editionNumber"),
                            bookRs.getString("copyright")
                    );
                    author.getBooks().add(book);
                }

                authorList.add(author);
            }

            // Forwarding the list to the JSP for rendering
            request.setAttribute("authorList", authorList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewauthors.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
