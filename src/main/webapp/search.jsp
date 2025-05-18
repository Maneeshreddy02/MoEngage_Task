<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Response Codes page</title>
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
        .search-form {
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
        .results {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 32px;
            margin-top: 28px;
        }
        .response-code {
            background: #fff;
            padding: 26px 14px 20px 14px;
            border-radius: 12px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            text-align: center;
            transition: transform 0.18s, box-shadow 0.18s;
        }
        .response-code:hover {
            transform: translateY(-4px) scale(1.03);
            box-shadow: 0 12px 40px rgba(60,60,120,0.13);
        }
        .response-code img {
            max-width: 100%;
            height: 120px;
            object-fit: contain;
            margin-bottom: 12px;
            border-radius: 7px;
            background: #f8fafc;
        }
        .response-code h3 {
            margin: 0;
            color: #1a2233;
            font-size: 1.35rem;
            font-weight: 700;
        }
        .save-list {
            margin-top: 40px;
            text-align: center;
        }
        .save-list form {
            display: flex;
            justify-content: center;
            gap: 18px;
            align-items: center;
        }
        .save-list input[type="text"] {
            width: 340px;
            padding: 12px;
            border-radius: 7px;
            border: 1.5px solid #e0e0e0;
            font-size: 1.13rem;
            background: #f8fafc;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        .save-list input[type="text"]:focus {
            border-color: #5b9df9;
            box-shadow: 0 0 0 2px #e3f0ff;
            outline: none;
        }
        .save-list button {
            padding: 13px 32px;
            font-size: 1.13rem;
            border-radius: 7px;
            background: linear-gradient(90deg, #5b9df9 0%, #38b6ff 100%);
            color: #fff;
            border: none;
            font-weight: 600;
            letter-spacing: 0.5px;
            box-shadow: 0 2px 8px rgba(60,60,120,0.07);
            transition: background 0.2s, box-shadow 0.2s;
        }
        .save-list button:hover {
            background: linear-gradient(90deg, #38b6ff 0%, #5b9df9 100%);
            box-shadow: 0 4px 16px rgba(60,60,120,0.13);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Search Response Codes</h1>
            <div class="nav-links">
                <a href="list">My Lists</a>
                <a href="login">Logout</a>
            </div>
        </div>

        <div class="search-form">
            <form action="search" method="get">
                <div class="form-group">
                    <label for="pattern">Response Code:</label>
                    <input type="text" id="pattern" name="pattern" placeholder="e.g., 1xx, 10x, 103" value="${param.pattern}">
                </div>
                <button type="submit">Search</button>
            </form>
        </div>

        <%
            java.util.List responseCodes = (java.util.List) request.getAttribute("responseCodes");
            if (responseCodes != null && !responseCodes.isEmpty()) {
        %>
            <div class="results">
            <%
                for (Object obj : responseCodes) {
                    com.maneesh.model.ResponseCode code = (com.maneesh.model.ResponseCode) obj;
            %>
                <div class="response-code">
                    <img src="<%= code.getImageUrl() %>" alt="HTTP <%= code.getResponseCode() %>">
                    <h3><%= code.getResponseCode() %></h3>
                </div>
            <%
                }
            %>
            </div>
        <%
            }
        %>
        <div class="save-list">
            <form action="save-list" method="post">
                <input type="text" name="savePattern" placeholder="Enter code (e.g., 1xx, 10x, 103)" required>
                <button type="submit">Save List</button>
            </form>
        </div>
    </div>
</body>
</html> 