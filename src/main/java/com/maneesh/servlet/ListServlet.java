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

@WebServlet("/list/*")
public class ListServlet extends HttpServlet {
    private ResponseListDAO responseListDAO;
    private ResponseCodeDAO responseCodeDAO;

    @Override
    public void init() throws ServletException {
        responseListDAO = new ResponseListDAOImpl();
        responseCodeDAO = new ResponseCodeDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        try {
            List<ResponseList> lists = responseListDAO.getListsByUserId(user.getUserId());
            for (ResponseList list : lists) {
                List<ResponseCode> codes = responseCodeDAO.getResponseCodesByListId(list.getListId());
                list.setResponseCodes(codes);
            }
            request.setAttribute("lists", lists);
            request.getRequestDispatcher("lists.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "An error occurred while retrieving lists");
            request.getRequestDispatcher("lists.jsp").forward(request, response);
        }
    }
} 