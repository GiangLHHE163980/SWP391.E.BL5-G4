<%-- 
    Document   : forgetPassword
    Created on : Nov 28, 2024, 11:11:23 PM
    Author     : TH Computer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quên mật khẩu</title>
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

            h1 {
                font-size: 2rem;
                font-weight: bold;
                display: inline-block;
                padding-bottom: 5px;
                margin-bottom: 15px;
                color: #000;
            }

            .description {
                font-size: 0.7rem;
                color: #555;
                margin-bottom: 20px;
            }

            input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 1rem;
            }

            .btn-continue {
                background-color: #1E562B;
                color: #fff;
                border: none;
                padding: 10px 20px;
                font-size: 1rem;
                border-radius: 5px;
                cursor: pointer;
            }

            .btn-continue:hover {
                background-color: #15471F;
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
        <div class="form-container">
            <a href="logIn.jsp" class="back-to-login">Trở về đăng nhập</a>
            <form>
            <h1>Quên mật khẩu</h1>
            <p class="description">
                Vui lòng nhập địa chỉ email đã đăng ký để được cấp lại mật khẩu.
            </p>
                <input type="text" placeholder="Email" name="email" required>
                <button type="submit" class="btn-continue">Tiếp tục</button>
            </form>
        </div>
    </body>
</html>