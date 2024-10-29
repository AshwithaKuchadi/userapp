package com.useraccess.servlets;





import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RequestServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/UserAcessManagement";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract parameters from the request
        String userId = request.getParameter("userId");
        String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        if (userId == null || softwareId == null || accessType == null || reason == null) {
            response.sendRedirect("requestAccess.jsp?error=Missing+required+fields");
            return;
        }

        // Insert the access request into the database
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String insertQuery = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, Integer.parseInt(userId));
                    preparedStatement.setInt(2, Integer.parseInt(softwareId));
                    preparedStatement.setString(3, accessType);
                    preparedStatement.setString(4, reason);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        response.sendRedirect("requestAccess.jsp?success=Request+submitted+successfully");
                    } else {
                        response.sendRedirect("requestAccess.jsp?error=Unable+to+submit+request");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("PostgreSQL JDBC driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Database error while submitting access request", e);
        }
    }
}

