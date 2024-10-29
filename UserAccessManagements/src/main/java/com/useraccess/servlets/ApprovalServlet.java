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


public class ApprovalServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/UserAcessManagement"; // Ensure database name is correct
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String requestId = request.getParameter("requestId");

        // Validate inputs
        if (action == null || requestId == null || !isNumeric(requestId)) {
            response.sendRedirect("pendingRequests.jsp?error=Invalid+Request");
            return;
        }

        String status = action.equals("approve") ? "Approved" : "Rejected";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String updateQuery = "UPDATE requests SET status = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, status);
                    preparedStatement.setInt(2, Integer.parseInt(requestId));

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        response.sendRedirect("pendingRequests.jsp?success=Request+updated+successfully");
                    } else {
                        response.sendRedirect("pendingRequests.jsp?error=Request+not+found");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException("PostgreSQL JDBC driver not found", e);
        } catch (SQLException e) {
            throw new ServletException("Database error while updating request status", e);
        }
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
