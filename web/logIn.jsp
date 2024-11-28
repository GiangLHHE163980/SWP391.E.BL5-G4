<%-- 
    Document   : logIn
    Created on : Nov 28, 2024, 1:35:38 PM
    Author     : TH Computer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
         <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background-color: #ffffff;
            }

            /* Navbar */
            .navbar {
                background-color: #1E562B;
                height: 60px;
                display: flex;
                align-items: center;
                padding: 0 20px;
                color: white;
                font-size: 20px;
                font-weight: bold;
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
                width: 93%;
                padding: 10px;
                border: 1px solid #cccccc;
                font-size: 16px;
                background-color: #f9f9f9;
            }

            .form-group input:focus {
                border-color: #1E562B;
                outline: none;
                background-color: #ffffff;
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
        <!-- Navbar -->
        <div class="navbar">
            Logo
        </div>

        <!-- Login Form -->
        <div class="login-container">
            <form class="login-form">
                <h2>Đăng nhập</h2>
                <div class="form-group">
                    <input type="text" placeholder="Tên tài khoản" ">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Mật khẩu" ">
                </div>
                <div class="form-group">
                    <a href="#">Quên mật khẩu</a>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn-login">Đăng nhập</button>
                    <button type="button" class="btn-register">Đăng ký</button>
                </div>
            </form>
        </div>
    </body>
</html>
