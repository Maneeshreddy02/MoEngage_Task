<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register page</title>
    <style>
        body {
            font-family: 'Inter', 'Segoe UI', Arial, sans-serif;
            background: linear-gradient(120deg, #e0eafc 0%, #cfdef3 100%);
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: #fff;
            padding: 44px 36px 32px 36px;
            border-radius: 18px;
            box-shadow: 0 8px 32px rgba(60,60,120,0.10);
            width: 100%;
            max-width: 410px;
        }
        h1 {
            text-align: center;
            color: #1a2233;
            margin-bottom: 32px;
            font-size: 2.2rem;
            font-weight: 700;
            letter-spacing: 1px;
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
        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 12px;
            border: 1.5px solid #e0e0e0;
            border-radius: 7px;
            box-sizing: border-box;
            font-size: 1.13rem;
            background: #f8fafc;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        input[type="text"]:focus,
        input[type="password"]:focus,
        input[type="email"]:focus {
            border-color: #5b9df9;
            box-shadow: 0 0 0 2px #e3f0ff;
            outline: none;
        }
        button {
            width: 100%;
            padding: 13px;
            background: linear-gradient(90deg, #5b9df9 0%, #38b6ff 100%);
            color: #fff;
            border: none;
            border-radius: 7px;
            cursor: pointer;
            font-size: 1.13rem;
            font-weight: 600;
            letter-spacing: 0.5px;
            margin-top: 10px;
            box-shadow: 0 2px 8px rgba(60,60,120,0.07);
            transition: background 0.2s, box-shadow 0.2s;
        }
        button:hover {
            background: linear-gradient(90deg, #38b6ff 0%, #5b9df9 100%);
            box-shadow: 0 4px 16px rgba(60,60,120,0.13);
        }
        .error {
            color: #d7263d;
            background: #fff0f3;
            border-radius: 6px;
            padding: 10px 0;
            margin-bottom: 20px;
            text-align: center;
            font-weight: 600;
            font-size: 1.05rem;
        }
        .login-link {
            text-align: center;
            margin-top: 22px;
            font-size: 1.05rem;
        }
        .login-link a {
            color: #38b6ff;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.2s;
        }
        .login-link a:hover {
            color: #1a2233;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Register</h1>
        <% if (request.getAttribute("error") != null) { %>
            <div class="error">${error}</div>
        <% } %>
        <form action="register" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
            <button type="submit">Register</button>
        </form>
        <div class="login-link">
            Already have an account? <a href="login.jsp">Login here</a>
        </div>
    </div>
</body>
</html> 