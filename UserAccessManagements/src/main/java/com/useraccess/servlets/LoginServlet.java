package com.useraccess.servlets;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate inputs
        if (username == null || password == null) {
            response.getWriter().println("Username or password cannot be null!");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAcessManagementDB", "postgres", "password");
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password); // Ideally, hash the password before comparison
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                // Redirect based on user role
                String redirectPage;
                switch (role) {
                    case "Employee":
                        redirectPage = "/requestAccess.jsp";
                        break;
                    case "Manager":
                        redirectPage = "/pendingRequests.jsp";
                        break;
                    case "Admin":
                        redirectPage = "/createSoftware.jsp";
                        break;
                    default:
                        redirectPage = "/error.jsp"; // Handle unexpected roles
                }
                response.sendRedirect(request.getContextPath() + redirectPage);
            } else {
                response.getWriter().println("Invalid credentials!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error occurred!");
        }
    }
}
