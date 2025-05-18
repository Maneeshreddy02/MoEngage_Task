package com.maneesh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.maneesh.Dao.ResponseCodeDAO;
import com.maneesh.Dao.ResponseListDAO;
import com.maneesh.daoimpl.ResponseCodeDAOImpl;
import com.maneesh.daoimpl.ResponseListDAOImpl;
import com.maneesh.model.ResponseCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-code-to-list")
public class AddCodeToListServlet extends HttpServlet {
    private ResponseCodeDAO responseCodeDAO;
    private ResponseListDAO responseListDAO;

    @Override
    public void init() throws ServletException {
        responseCodeDAO = new ResponseCodeDAOImpl();
        responseListDAO = new ResponseListDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listIdStr = request.getParameter("listId");
        String addPattern = request.getParameter("addPattern");
        if (listIdStr != null && addPattern != null && !addPattern.trim().isEmpty()) {
            int listId = Integer.parseInt(listIdStr);
            addPattern = addPattern.trim();
            try {
                if (addPattern.matches("\\d{3}")) {
                    // Only add the exact code
                    ResponseCode code = responseCodeDAO.getResponseCodeByCode(addPattern);
                    if (code != null) {
                        responseListDAO.addResponseCodeToList(listId, code.getResponseCode());
                    }
                } else {
                    // Add all matching codes
                    List<ResponseCode> codes = responseCodeDAO.getResponseCodesByPattern(addPattern);
                    for (ResponseCode code : codes) {
                        responseListDAO.addResponseCodeToList(listId, code.getResponseCode());
                    }
                }
            } catch (SQLException e) {
                // Optionally, set an error attribute here
            }
        }
        // Redirect back to the edit-list page for this list
        response.sendRedirect("edit-list?id=" + request.getParameter("listId"));
    }
} 
