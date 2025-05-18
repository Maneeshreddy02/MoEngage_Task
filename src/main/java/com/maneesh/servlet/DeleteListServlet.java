package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.maneesh.Dao.ResponseListDAO;
import com.maneesh.daoimpl.ResponseListDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-list")
public class DeleteListServlet extends HttpServlet {
    private ResponseListDAO responseListDAO;

    @Override
    public void init() throws ServletException {
        responseListDAO = new ResponseListDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String listId = request.getParameter("id");
        if (listId != null && !listId.trim().isEmpty()) {
            try {
                if (responseListDAO.deleteList(Integer.parseInt(listId))) {
                    response.sendRedirect("list");
                } else {
                    request.setAttribute("error", "Failed to delete list");
                    request.getRequestDispatcher("list").forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while deleting the list");
                request.getRequestDispatcher("list").forward(request, response);
            }
        } else {
            response.sendRedirect("list");
        }
    }
} 
