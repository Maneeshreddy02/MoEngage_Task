package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.maneesh.Dao.ResponseCodeDAO;
import com.maneesh.Dao.ResponseListDAO;
import com.maneesh.daoimpl.ResponseCodeDAOImpl;
import com.maneesh.daoimpl.ResponseListDAOImpl;
import com.maneesh.model.ResponseCode;
import com.maneesh.model.ResponseList;
import com.maneesh.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/save-list")
public class SaveListServlet extends HttpServlet {
    private ResponseListDAO responseListDAO;
    private ResponseCodeDAO responseCodeDAO;

    @Override
    public void init() throws ServletException {
        responseListDAO = new ResponseListDAOImpl();
        responseCodeDAO = new ResponseCodeDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        String savePattern = request.getParameter("savePattern");
        String listName = savePattern; // Use the pattern as the list name
        if (listName != null && !listName.trim().isEmpty()) {
            try {
                // Create new list
                ResponseList list = new ResponseList();
                list.setUserId(user.getUserId());
                list.setListName(listName);

                if (responseListDAO.createList(list)) {
                    if (savePattern.matches("\\d{3}")) {
                        // Only save the exact code
                        ResponseCode code = responseCodeDAO.getResponseCodeByCode(savePattern);
                        if (code != null) {
                            responseListDAO.addResponseCodeToList(list.getListId(), code.getResponseCode());
                        }
                    } else {
                        // Save all matching codes
                        List<ResponseCode> codes = responseCodeDAO.getResponseCodesByPattern(savePattern);
                        for (ResponseCode code : codes) {
                            responseListDAO.addResponseCodeToList(list.getListId(), code.getResponseCode());
                        }
                    }
                    response.sendRedirect("list");
                } else {
                    request.setAttribute("error", "Failed to create list");
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                request.setAttribute("error", "An error occurred while saving the list");
                request.getRequestDispatcher("search.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "List name and pattern are required");
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }
}