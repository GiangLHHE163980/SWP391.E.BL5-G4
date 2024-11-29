<%-- 
    Document   : register
    Created on : Nov 28, 2024, 10:45:38 PM
    Author     : TH Computer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="../css/style.css"/>
        <style>
            /* Center form */
            .form-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f9f9f9;
            }
            
            /* Form styles */
            form {
                transform: scale(1.2);
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                width: 400px;
            }
            
            h2 {
                text-align: center;
                font-size: 1.8rem;
                margin-bottom: 20px;
            }

            input[type="text"], input[type="password"], input[type="email"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 1rem;
            }

            /* Mã xác thực input + button */
            .verification-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .verification-container input {
                flex: 2;
                margin-right: 10px;
            }

            .verification-container button {
                flex: 1.3;
                background-color: #7E7E05;
                color: white;
                border: none;
                border-radius: 5px;
                padding: 10px;
                font-size: 1rem;
                cursor: pointer;
            }

            button {
                width: 100%;
                background-color: #1E562B;
                color: white;
                padding: 12px;
                font-size: 1rem;
                border: none;
                border-radius: 5px;
                margin-top: 1px;
                cursor: pointer;
            }

            button:hover {
                background-color: #154722;
            }

            /* Register button specific style */
            .register-button {
                color: #7E7E05;
                border: 1px solid #7E7E05;
                background-color: white;
            }

            .register-button:hover {
                background-color: #7E7E05;
                color: white;
            }
            .back-to-login {
                position: absolute; /* Định vị góc trái bên trong form */
                top: 100px; /* Khoảng cách từ phía trên */
                left: 65px; /* Khoảng cách từ phía trái */
                color: #7E7E05;
                text-decoration: underline;
                font-size: 1.5rem;
                font-weight: bold;
                cursor: pointer;
            }
            .back-to-login:hover {
                color: #4C4C04; /* Màu khi hover */
            }
        </style>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <%@ include file="../footer.jsp" %>
        
        <!-- Form container -->
        <div class="form-container">
            <a href="logIn.jsp" class="back-to-login">Trở về đăng nhập</a>
            <form action="registerServlet" method="POST">
                <h2>Đăng ký</h2>
                <input type="text" name="username" placeholder="Tên tài khoản" required>
                <input type="password" name="password" placeholder="Mật khẩu" required>
                <input type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu" required>
                <input type="email" name="email" placeholder="Email" required>

                <!-- Mã xác thực -->
                <div class="verification-container">
                    <input type="text" name="verificationCode" placeholder="Mã xác thực" required>
                    <button type="button"">Gửi mã xác thực</button>
                </div>
                <!-- Submit button -->
                <button type="submit" class="register-button">Đăng ký</button>
            </form>
        </div>
    </body>
</html>
