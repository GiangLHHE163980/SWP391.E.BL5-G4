<%-- 
    Document   : homePage
    Created on : Nov 29, 2024, 11:42:58 AM
    Author     : TH Computer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="./css/style.css"/>
        <style>
        /* Định dạng chung */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        h1 {
            text-align: center;
            margin: 90px auto;
            max-width: 900px;
        }
        /* Nội dung chính */
        .container {
            transform: scale(1.3);
            text-align: center;
            margin: 20px auto;
            max-width: 900px;
            padding: 20px; /* Thêm padding cho nội dung bên trong */
            background: white; /* Đảm bảo nền trắng để thấy rõ bóng */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Bóng xung quanh */
            border-radius: 5px; /* Tùy chọn bo góc */
        }
        .insurance-grid {
            display: grid;
            grid-template-columns: 1fr 1fr; /* Hai cột */
            gap: 20px;
        }
        .insurance-item {
            display: flex; /* Flexbox cho sắp xếp ngang */
            border: 1px solid #ddd;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
        }
        .insurance-item img {
            width: 150px;
            height: 150px;
            object-fit: cover;
            background-color: #f0f0f0; /* Placeholder nếu ảnh không tải */
        }
        .insurance-item .content {
            flex: 1;
            padding: 10px;
            display: flex;
            flex-direction: column; /* Sắp xếp tên trên, nút dưới */
            justify-content: space-between;
        }
        .insurance-item h3 {
            text-align: left;
            font-size: 16px;
            color: #333;
        }
        .insurance-item button {
            background-color: #b38b00;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            font-weight: bold;
            border-radius: 5px;
            align-self: flex-start; /* Căn nút sang trái */
        }
        .insurance-item button:hover {
            background-color: #d4a517;
        }
        /* Xem tất cả */
        .see-all {
            text-align: center;
            margin: 20px 0;
        }
        .see-all a {
            color: #b38b00;
            font-weight: bold;
            text-decoration: none;
        }
        .see-all a:hover {
            text-decoration: underline;
        }
    </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <%@ include file="footer.jsp" %>
        <!-- Nội dung chính -->
    <h1>Chào mừng đến với NT Insurance</h1>
     <div class="container">
        <div class="insurance-grid">
            <div class="insurance-item">
                <img src="image/anh01.jpg" alt="Bảo hiểm chăm sóc sức khỏe">
                <div class="content">
                    <h3>Bảo hiểm chăm sóc sức khỏe</h3>
                    <button>Tham gia</button>
                </div>
            </div>
            <div class="insurance-item">
                <img src="image/anh2.jpg" alt="Bảo hiểm du lịch">
                <div class="content">
                    <h3>Bảo hiểm du lịch</h3>
                    <button>Tham gia</button>
                </div>
            </div>
            <div class="insurance-item">
                <img src="image/anh3.jpg" alt="Bảo hiểm nhân thọ">
                <div class="content">
                    <h3>Bảo hiểm nhân thọ</h3>
                    <button>Tham gia</button>
                </div>
            </div>
            <div class="insurance-item">
                <img src="image/anh4.jpg" alt="Bảo hiểm xe cộ">
                <div class="content">
                    <h3>Bảo hiểm xe cộ</h3>
                    <button>Tham gia</button>
                </div>
            </div>
        </div>
        <div class="see-all">
            <a href="#">Xem tất cả</a>
        </div>
    </div>
    </body>
</html>
