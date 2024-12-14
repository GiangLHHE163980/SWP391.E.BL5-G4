<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bảng Tổng Quan Khách Hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <style>
            .link-card {
                display: flex;               /* Enable Flexbox for centering */
                justify-content: center;     /* Center horizontally */
                align-items: center;         /* Center vertically */
                text-align: center;          /* Ensure text is centered */
                height: 150px;               /* Set height of the card */
                border: 1px solid #ccc;      /* Optional: Add a border */
                border-radius: 8px;          /* Optional: Rounded corners */
                text-decoration: none;       /* Remove underline for links */
                color: #f9f9f9;              /* Inherit text color */
                background-color: #198754;   /* Optional: Add background color */
                transition: all 0.3s ease;   /* Optional: Add hover transition */
            }

            .link-card:hover {
                transform: scale(1.05);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .link-card h3 {
                margin: 0;
                font-size: 1.5rem;
                font-weight: bold;
                line-height: normal;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            
            <h1 class="mt-5 mb-4">Trang Tổng Quan Khách Hàng</h1>

            <div class="row g-4 mt-5">
                <!-- First Card -->
                <div class="col-md-3">
                    <a href="userInfo" class="link-card">
                        <h3>Thông Tin Của Tôi</h3>
                    </a>
                </div>

                <!-- Second Card -->
                <div class="col-md-3">
                    <a href="UpdateUserInfoController" class="link-card">
                        <h3>Chỉnh Sửa Thông Tin </h3>
                    </a>
                </div>

                <!-- Third Card -->
                <div class="col-md-3">
                    <a href="insuranceCards" class="link-card">
                        <h3>Danh Sách Gói Bảo Hiểm</h3>
                    </a>
                </div>

                <!-- Fourth Card -->
                <div class="col-md-3">
                    <a href="claimList" class="link-card">
                        <h3>Danh Sách Yêu Cầu Bồi Thường</h3>
                    </a>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
