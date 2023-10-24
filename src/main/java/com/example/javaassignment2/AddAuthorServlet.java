//Line to ensure saved
package com.example.javaassignment2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AddAuthor", value = "/AddAuthor")
public class AddAuthorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        PrintWriter out = response.getWriter();

        try {
            Connection conn = DBConnection.getBooksDatabaseConnection();

            // Check if the author already exists in the database
            PreparedStatement checkAuthorStmt = conn.prepareStatement("SELECT COUNT(*) FROM authors WHERE firstName = ? AND lastName = ?");
            checkAuthorStmt.setString(1, firstName);
            checkAuthorStmt.setString(2, lastName);
            ResultSet rs = checkAuthorStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                out.println("<html><body>");
                out.println("<h1>Error: Author already exists in the database.</h1>");
                out.println("<a href='index.jsp'>Back to Home</a>");
                out.println("</body></html>");
                return; // Exit early since author already exists
            }

            // If author doesn't exist, insert the new author
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO authors (firstName, lastName) VALUES (?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.executeUpdate();

            out.println("<html><body>");
            out.println("<h1>Author Added Successfully!</h1>");
            out.println("<a href='index.jsp'>Back to Home</a>");
            out.println("</body></html>");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>Error Occurred: " + ex.getMessage() + "</h1>");
            out.println("<a href='index.jsp'>Back to Home</a>");
            out.println("</body></html>");
        }
    }
}

