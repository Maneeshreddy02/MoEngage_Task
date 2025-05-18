package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.maneesh.Dao.UserDao;
import com.maneesh.daoimpl.UserDAOImpl;
import com.maneesh.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDAO;

    @Override
    public void init() throws ServletException {
    	userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate input
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            // Check if username already exists
            if (userDAO.getUserByUsername(username) != null) {
                request.setAttribute("error", "Username already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Check if email already exists
            if (userDAO.getUserByEmail(email) != null) {
                request.setAttribute("error", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Create new user
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password); 

            if (userDAO.createUser(user)) {
                response.sendRedirect("login.jsp?registered=true");
            } else {
                request.setAttribute("error", "Failed to create user");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "An error occurred during registration");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
} 
