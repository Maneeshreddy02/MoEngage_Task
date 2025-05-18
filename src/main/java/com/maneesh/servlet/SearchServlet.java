package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.maneesh.Dao.ResponseCodeDAO;
import com.maneesh.daoimpl.ResponseCodeDAOImpl;
import com.maneesh.model.ResponseCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private ResponseCodeDAO responseCodeDAO;

    @Override
    public void init() throws ServletException {
        responseCodeDAO = new ResponseCodeDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String pattern = request.getParameter("pattern");
        if (pattern != null && !pattern.trim().isEmpty()) {
            try {
                List<ResponseCode> codes = responseCodeDAO.getResponseCodesByPattern(pattern);
                request.setAttribute("responseCodes", codes);
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while searching for response codes");
            }
        }

        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
} 
