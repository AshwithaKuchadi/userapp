package com.useraccess.servlets;


import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] accessLevels = request.getParameterValues("accessLevels");
        String levels = String.join(",", accessLevels);

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UserAcessManagementDB", "postgres", "password");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, levels);
            stmt.executeUpdate();
            response.sendRedirect("createSoftware.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
