<%@ page import="java.util.List" %>
<%@ page import="com.maneesh.model.ResponseList" %>
<%@ page import="com.maneesh.model.ResponseCode" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Lists page</title>
    <style>
        body {
            font-family: 'Inter', 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(120deg, #e0eafc 0%, #cfdef3 100%);
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1300px;
            margin: 48px auto;
            padding: 0 24px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px;
        }
        .header h1 {
            font-size: 2.7rem;
            color: #1a2233;
            letter-spacing: 1px;
            font-weight: 700;
        }
        .nav-links {
            display: flex;
            gap: 32px;
        }
        .nav-links a {
            color: #38b6ff;
            text-decoration: none;
            font-weight: 600;
            font-size: 1.13rem;
            transition: color 0.2s;
        }
        .nav-links a:hover {
            color: #1a2233;
            text-decoration: underline;
        }
        .lists {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(370px, 1fr));
            gap: 38px;
        }
        .list-card {
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            padding: 32px 28px 24px 28px;
            transition: transform 0.18s, box-shadow 0.18s;
            position: relative;
            overflow: hidden;
        }
        .list-card:hover {
            transform: translateY(-8px) scale(1.03);
            box-shadow: 0 12px 40px rgba(60,60,120,0.13);
        }
        .list-header {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 20px;
        }
        .list-title {
            margin: 0;
            color: #1a2233;
            font-size: 1.6rem;
            font-weight: 700;
        }
        .list-actions {
            display: flex;
            gap: 18px;
        }
        .list-actions a {
            font-size: 1.08rem;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.2s;
        }
        .list-actions a:first-child { color: #38b6ff; }
        .list-actions a:first-child:hover { color: #1a2233; }
        .list-actions .delete { color: #d7263d; }
        .list-actions .delete:hover { color: #b71c1c; }
        .list-details {
            color: #555;
            font-size: 1.08rem;
            margin-bottom: 14px;
        }
        .list-images {
            display: flex;
            flex-wrap: wrap;
            gap: 12px;
            margin-top: 12px;
        }
        .list-images img {
            width: 64px;
            height: 64px;
            object-fit: cover;
            border-radius: 8px;
            border: 1.5px solid #e0e0e0;
            background: #f8fafc;
            transition: box-shadow 0.2s;
        }
        .list-images img:hover {
            box-shadow: 0 2px 8px rgba(60,60,120,0.18);
        }
        .no-lists {
            text-align: center;
            color: #555;
            padding: 70px 0;
            background: #fff;
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            margin-top: 48px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>My Lists</h1>
            <div class="nav-links">
                <a href="search">Search Response Codes</a>
                <a href="login">Logout</a>
            </div>
        </div>
        <%
            List<ResponseList> lists = (List<ResponseList>) request.getAttribute("lists");
            if (lists == null || lists.isEmpty()) {
        %>
            <div class="no-lists">
                <h2>No Lists Found</h2>
                <p>You haven't created any response code lists yet.</p>
                <a href="search">Start searching for response codes</a>
            </div>
        <%
            } else {
        %>
        <div class="lists">
            <%
                for (ResponseList list : lists) {
            %>
                <div class="list-card">
                    <div class="list-header">
                        <h2 class="list-title"><%= list.getListName() %></h2>
                        <div class="list-actions">
                            <a href="edit-list?id=<%= list.getListId() %>">Edit</a>
                            <a href="delete-list?id=<%= list.getListId() %>" class="delete" onclick="return confirm('Are you sure you want to delete this list?')">Delete</a>
                        </div>
                    </div>
                    <div class="list-details">
                        <p>Created: <%= list.getCreatedAt() != null ? list.getCreatedAt().toString() : "N/A" %></p>
                        <p>Response Codes: <%= (list.getResponseCodes() != null ? list.getResponseCodes().size() : 0) %></p>
                    </div>
                    <div class="list-images">
                        <%
                            List<ResponseCode> codes = list.getResponseCodes();
                            if (codes != null && !codes.isEmpty()) {
                                for (ResponseCode code : codes) {
                        %>
                            <img src="<%= code.getImageUrl() %>" alt="HTTP <%= code.getResponseCode() %>" title="HTTP <%= code.getResponseCode() %>">
                        <%
                                }
                            } else {
                        %>
                            <span style="color:#bbb;font-size:0.95rem;">No codes in this list.</span>
                        <%
                            }
                        %>
                    </div>
                </div>
            <%
                }
            %>
        </div>
        <%
            }
        %>
    </div>
</body>
</html> 