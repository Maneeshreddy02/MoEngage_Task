package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.maneesh.Dao.ResponseListDAO;
import com.maneesh.daoimpl.ResponseListDAOImpl;
import com.maneesh.model.ResponseList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-list")
public class EditListServlet extends HttpServlet {
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
                ResponseList list = responseListDAO.getListById(Integer.parseInt(listId));
                if (list != null) {
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("edit-list.jsp").forward(request, response);
                } else {
                    response.sendRedirect("list");
                }
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while retrieving the list");
                request.getRequestDispatcher("list").forward(request, response);
            }
        } else {
            response.sendRedirect("list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String listId = request.getParameter("listId");
        String listName = request.getParameter("listName");

        if (listId != null && !listId.trim().isEmpty() && listName != null && !listName.trim().isEmpty()) {
            try {
                ResponseList list = responseListDAO.getListById(Integer.parseInt(listId));
                if (list != null) {
                    list.setListName(listName);
                    if (responseListDAO.updateList(list)) {
                        response.sendRedirect("list");
                    } else {
                        request.setAttribute("error", "Failed to update list");
                        request.getRequestDispatcher("edit-list.jsp").forward(request, response);
                    }
                } else {
                    response.sendRedirect("list");
                }
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while updating the list");
                request.getRequestDispatcher("edit-list.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("list");
        }
    }
} 