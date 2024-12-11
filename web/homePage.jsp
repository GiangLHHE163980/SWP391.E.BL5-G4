<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NT Insurance</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Bootstrap Icons -->

    <!-- Custom CSS -->
    <style>
        /* Body and General styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        /* Header Section */
        h1 {
            text-align: center;
            font-size: 2rem;
            font-weight: bold;
            color: #d9534f;
            margin: 40px auto 10px;
        }

        h1 + p {
            text-align: center;
            font-size: 1.2rem;
            color: #5cb85c;
            margin-bottom: 30px;
        }

        /* Main Container */
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Product Grid */
        .insurance-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr); /* 3 columns */
            gap: 20px;
        }

        /* Product Card */
        .insurance-item {
            display: flex;
            flex-direction: column;
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }

        .insurance-item:hover {
            transform: translateY(-5px);
        }

        .insurance-item img {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }

        .insurance-item .content {
            padding: 15px;
            display: flex;
            flex-direction: column;
            gap: 10px;
        }

        .insurance-item h3 {
            font-size: 1rem;
            color: #333;
            margin: 0;
        }

        .insurance-item p.price {
            font-size: 0.9rem;
            color: #d9534f;
            margin: 0;
        }

        .insurance-item .actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .insurance-item button {
            background-color: #ff9900;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            font-size: 0.9rem;
            font-weight: bold;
            border-radius: 5px;
        }

        .insurance-item button:hover {
            background-color: #cc7a00;
        }

        .insurance-item a {
            text-decoration: none;
            color: #f8f9fa;
            font-size: 0.9rem;
        }

        .insurance-item a:hover {
            text-decoration: underline;
        }

        /* See All Button */
        .see-all {
            text-align: center;
            margin-top: 20px;
        }

        .see-all a {
            color: #ff9900;
            font-weight: bold;
            text-decoration: none;
            font-size: 1.1rem;
        }

        .see-all a:hover {
            text-decoration: underline;
        }
        .btn-primary {
                background-color: #ff9900;
                border: none;
            }
            .btn-primary:hover {
                background-color: #cc7a00;
            }
    </style>
</head>
<body>
    <!-- Header -->
    <%@ include file="header.jsp" %>

    <!-- Main Content -->
    <div class="container">
        <h1>Bảo Hiểm Trực Tuyến</h1>
        <p>Dễ Dàng, Thuận Tiện, Nhanh Chóng</p>

        <div class="insurance-grid">
            <!-- Loop through the list of products -->
            <c:forEach var="product" items="${topProducts}">
                <div class="insurance-item">
                    <img src="${product.avatar}" alt="${product.productName}">
                    <div class="content">
                        <h3>${product.productName}</h3>
                        <p class="price">Chỉ từ ${product.cost} VNĐ</p>
                        <div class="actions">
                            <a href="productDetail?ProductID=${product.productID}" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="see-all">
            <a href="ProductController?action=showAllProduct">Xem tất cả</a>
        </div>
    </div>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
</body>
</html>