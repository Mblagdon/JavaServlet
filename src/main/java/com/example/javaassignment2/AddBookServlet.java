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

@WebServlet(name = "AddBook", value = "/AddBook")
public class AddBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String copyright = request.getParameter("copyright");
        int editionNumber = Integer.parseInt(request.getParameter("editionNumber"));
        String authorFirstName = request.getParameter("authorFirstName");
        String authorLastName = request.getParameter("authorLastName");

        PrintWriter out = response.getWriter();

        try {
            Connection conn = DBConnection.getBooksDatabaseConnection();

            // Check if the author exists in the database
            PreparedStatement checkAuthorStmt = conn.prepareStatement("SELECT COUNT(*) FROM authors WHERE firstName = ? AND lastName = ?");
            checkAuthorStmt.setString(1, authorFirstName);
            checkAuthorStmt.setString(2, authorLastName);
            ResultSet rs = checkAuthorStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                out.println("<html><body>");
                out.println("<h1>Error: Author does not exist in the database. Please add the author first!</h1>");
                out.println("<a href='index.jsp'>Back to Home</a>");
                out.println("</body></html>");
                return; // Exit early since author doesn't exist
            }

            // If author exists, insert the book
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO titles(isbn, title, editionNumber, copyright) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, editionNumber); // set the editionNumber
            preparedStatement.setString(4, copyright);
            preparedStatement.executeUpdate();

            // Retrieve the authorID of the author
            PreparedStatement getAuthorIdStmt = conn.prepareStatement("SELECT authorID FROM authors WHERE firstName = ? AND lastName = ?");
            getAuthorIdStmt.setString(1, authorFirstName);
            getAuthorIdStmt.setString(2, authorLastName);
            ResultSet authorIdRs = getAuthorIdStmt.executeQuery();

            if (authorIdRs.next()) {
                int authorId = authorIdRs.getInt("authorID");

                // Insert the association between the book and the author
                PreparedStatement associateBookWithAuthorStmt = conn.prepareStatement("INSERT INTO authorisbn (authorID, isbn) VALUES (?, ?)");
                associateBookWithAuthorStmt.setInt(1, authorId);
                associateBookWithAuthorStmt.setString(2, isbn);
                associateBookWithAuthorStmt.executeUpdate();
            }

            out.println("<html><body>");
            out.println("<h1>Book Added Successfully!</h1>");
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
