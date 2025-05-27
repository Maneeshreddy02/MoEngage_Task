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

@WebServlet("/remove-code")
public class RemoveCodeServlet extends HttpServlet {
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

        String listId = request.getParameter("listId");
        String code = request.getParameter("code");

        if (listId != null && !listId.trim().isEmpty() && code != null && !code.trim().isEmpty()) {
            try {
                if (responseListDAO.removeResponseCodeFromList(Integer.parseInt(listId), code)) {
                    response.sendRedirect("edit-list?id=" + listId);
                } else {
                    request.setAttribute("error", "Failed to remove response code");
                    request.getRequestDispatcher("edit-list?id=" + listId).forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while removing the response code");
                request.getRequestDispatcher("edit-list?id=" + listId).forward(request, response);
            }
        } else {
            response.sendRedirect("list");
        }
    }
} 