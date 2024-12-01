<%-- 
    Document   : logIn
    Created on : Nov 28, 2024, 1:35:38 PM
    Author     : TH Computer
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="../css/style.css"/>
         <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background-color: #ffffff;
            }
            /* Centering form */
            .login-container {
                transform: scale(1.2);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            /* Login Form */
            .login-form {
                background-color: white;
                padding: 30px 40px;
                width: 350px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            .login-form h2 {
                text-align: center;
                color: black;
                margin-bottom: 20px;
                font-size: 22px;
                font-weight: bold;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-group label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
            }

            .form-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #cccccc;
                font-size: 16px;
                background-color: #f9f9f9;
            }
            .form-group a {
                display: block;
                text-align: left;
                color: #7E7E05;
                text-decoration: none;
                font-size: 14px;
                margin-top: 5px;
            }

            .form-group a:hover {
                text-decoration: underline;
            }

            .form-actions {
                display: flex;
                flex-direction: column;
                gap: 10px;
                margin-top: 10px;
            }

            .form-actions button {
                padding: 10px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
            }

            .btn-login {
                background-color: #e0e0e0;
                border: none;
                font-weight: bold;
            }

            .btn-login:hover {
                background-color: #d6d6d6;
            }

            .btn-register {
                background-color: #f9f9f9;
                color: #7E7E05;
                border: 1px solid #7E7E05;
                font-weight: bold;
            }

            .btn-register:hover {
                background-color: #7E7E05;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <!-- Login Form -->
        <div class="login-container">
             <form class="login-form" method="POST" action="login">
                 <c:if test="${not empty message}">
                <div class="message" style="color:green;">
                    ${message}
                </div>
            </c:if>
        <h2>Đăng nhập</h2>
        <div class="form-group">
            <input type="text" name="username" placeholder="Tên tài khoản" required>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Mật khẩu" required>
        </div>
        <div class="form-group">
            <a href="forgetPassword">Quên mật khẩu</a>
        </div>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <div class="form-group">
            <c:if test="${not empty error}">
                <div class="error-message" style="color:red;">
                    ${error}
                </div>
            </c:if>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn-login">Đăng nhập</button>
            <button type="button" class="btn-register" onclick="window.location.href='register'">Đăng ký</button>
        </div>
    </form>
        </div>
        <!-- Footer -->
    <%@ include file="../footer.jsp" %>
    </body>
</html>
