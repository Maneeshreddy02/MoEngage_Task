<%@ page import="java.util.List" %>
<%@ page import="com.maneesh.model.ResponseList" %>
<%@ page import="com.maneesh.model.ResponseCode" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit List - Response Code Manager</title>
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
            font-size: 2.3rem;
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
        .edit-form {
            background: #fff;
            padding: 32px 28px 24px 28px;
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            margin-bottom: 40px;
        }
        .form-group {
            margin-bottom: 22px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #444;
            font-weight: 600;
            letter-spacing: 0.5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 12px;
            border: 1.5px solid #e0e0e0;
            border-radius: 7px;
            box-sizing: border-box;
            font-size: 1.13rem;
            background: #f8fafc;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        input[type="text"]:focus {
            border-color: #5b9df9;
            box-shadow: 0 0 0 2px #e3f0ff;
            outline: none;
        }
        button {
            padding: 13px 32px;
            background: linear-gradient(90deg, #5b9df9 0%, #38b6ff 100%);
            color: #fff;
            border: none;
            border-radius: 7px;
            cursor: pointer;
            font-size: 1.13rem;
            font-weight: 600;
            letter-spacing: 0.5px;
            box-shadow: 0 2px 8px rgba(60,60,120,0.07);
            transition: background 0.2s, box-shadow 0.2s;
        }
        button:hover {
            background: linear-gradient(90deg, #38b6ff 0%, #5b9df9 100%);
            box-shadow: 0 4px 16px rgba(60,60,120,0.13);
        }
        .search-results {
            margin-top: 40px;
        }
        .search-results h2 {
            margin-bottom: 18px;
            color: #1a2233;
            font-size: 1.18rem;
            font-weight: 700;
        }
        .response-codes {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 32px;
            margin-top: 28px;
        }
        .response-code {
            background: #fff;
            padding: 22px 12px 18px 12px;
            border-radius: 12px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            text-align: center;
            position: relative;
            transition: transform 0.18s, box-shadow 0.18s;
        }
        .response-code:hover {
            transform: translateY(-4px) scale(1.03);
            box-shadow: 0 12px 40px rgba(60,60,120,0.13);
        }
        .response-code img {
            max-width: 100%;
            height: 90px;
            object-fit: contain;
            margin-bottom: 10px;
            border-radius: 7px;
            background: #f8fafc;
        }
        .response-code h3 {
            margin: 0;
            color: #1a2233;
            font-size: 1.13rem;
            font-weight: 700;
        }
        .remove-code {
            position: absolute;
            top: 8px;
            right: 12px;
            color: #d7263d;
            text-decoration: none;
            font-size: 22px;
            line-height: 1;
            font-weight: bold;
            background: #fff;
            border-radius: 50%;
            width: 28px;
            height: 28px;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: background 0.2s, color 0.2s;
        }
        .remove-code:hover {
            color: #fff;
            background: #d7263d;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>
                Edit List:
                <%
                    com.maneesh.model.ResponseList list = (com.maneesh.model.ResponseList) request.getAttribute("list");
                    out.print(list != null ? list.getListName() : "");
                %>
            </h1>
            <div class="nav-links">
                <a href="list">Back to Lists</a>
                <a href="search">Search Response Codes</a>
                <a href="login">Logout</a>
            </div>
        </div>

        <div class="edit-form">
            <form action="edit-list" method="post">
                <input type="hidden" name="listId" value="<%= list != null ? list.getListId() : "" %>">
                <div class="form-group">
                    <label for="listName">List Name:</label>
                    <input type="text" id="listName" name="listName" value="<%= list != null ? list.getListName() : "" %>" required>
                </div>
                <button type="submit">Save Changes</button>
            </form>
        </div>

        <div class="search-results">
            <h2>Add More Response Codes</h2>
            <form action="add-code-to-list" method="post">
                <input type="hidden" name="listId" value="<%= list != null ? list.getListId() : "" %>">
                <div class="form-group">
                    <label for="addPattern">Response Code Pattern:</label>
                    <input type="text" id="addPattern" name="addPattern" placeholder="e.g., 2xx, 20x, 203" required>
                </div>
                <button type="submit">Add Codes</button>
            </form>
        </div>

        <div class="response-codes">
<%
    if (list != null && list.getResponseCodes() != null && !list.getResponseCodes().isEmpty()) {
        for (com.maneesh.model.ResponseCode code : list.getResponseCodes()) {
%>
    <div class="response-code">
        <a href="remove-code?listId=<%= list.getListId() %>&code=<%= code.getResponseCode() %>" class="remove-code" onclick="return confirm('Remove this response code?')">&times;</a>
        <img src="<%= code.getImageUrl() %>" alt="HTTP <%= code.getResponseCode() %>">
        <h3><%= code.getResponseCode() %></h3>
    </div>
<%
        }
    } else {
%>
    <span style="color:#bbb;font-size:0.95rem;">No codes in this list.</span>
<%
    }
%>        </div>
    </div>
</body>
</html> 