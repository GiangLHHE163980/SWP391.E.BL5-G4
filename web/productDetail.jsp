<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin bảo hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="../css/style.css"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                color: #333;
                background-color: #f9f9f9;
            }
            /* Main Content */
            .main {
                padding: 20px 40px;
            }

            .main .product-info {
                display: flex;
                flex-wrap: wrap;
                gap: 40px;
                margin-bottom: 40px;
            }

            .main .product-info img {
                width: 100%;
                max-width: 500px;
                height: auto;
                object-fit: cover;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                margin-bottom: 40px;
            }

            .main .price-info {
                background-color: #f5f5f5;
                border: 1px solid #ddd;
                border-radius: 10px;
                padding: 20px;
                font-size: 16px;
                line-height: 1.6;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                max-width: 400px;
            }

            .main .product-details {
                flex: 1;
                width: 100%;
            }

            .main .product-details h1 {
                font-size: 28px;
                margin-bottom: 15px;
                color: #337733;
            }

            .main .product-details p {
                font-size: 16px;
                line-height: 1.8;
                margin-bottom: 20px;
                color: #555;
                text-align: justify;
            }

            .main .product-details .join-btn {
                background-color: #d8a13c;
                color: #fff;
                padding: 12px 24px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                transition: background-color 0.3s ease;
            }

            .main .product-details .join-btn:hover {
                background-color: #b4882e;
            }

            /* Related Products */
            .related-products {
                display: grid; /* Sử dụng grid để chia không gian */
                grid-template-columns: repeat(3, 1fr); /* Chia thành 3 cột đều */
                gap: 20px; /* Khoảng cách giữa các sản phẩm */
                margin-top: 20px;
            }

            h2 {
                width: 100%;
                font-size: 24px;
                margin-bottom: 20px;
                color: #333;
                font-weight: bold;
                margin-top: 60px;
            }

            .product-card {
                display: flex;
                flex-direction: column;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                height: 100%; /* Đảm bảo sản phẩm chiếm hết chiều cao của grid */
            }

            .product-card img {
                max-height: 200px; /* Đảm bảo ảnh không quá cao */
                width: 100%; /* Đảm bảo ảnh chiếm toàn bộ chiều rộng của sản phẩm */
                object-fit: cover; /* Đảm bảo ảnh không bị méo */
            }

            .product-card .card-body {
                padding: 15px;
                flex-grow: 1; /* Đảm bảo phần nội dung bên trong card chiếm hết không gian còn lại */
            }

            .product-card .card-body h5 {
                font-size: 18px;
                margin-bottom: 10px;
            }

            .product-card .btn-primary {
                background-color: #ff9900;
                border: none;
                color: #fff;
                padding: 10px 15px;
                text-align: center;
                display: inline-block;
                border-radius: 5px;
                font-size: 14px;
                cursor: pointer;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .product-card .btn-primary:hover {
                background-color: #cc7a00;
            }
            .border-container {
                margin: 15px;
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                background-color: #fff;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .vertical-divider {
                border-right: 1px solid #ddd;
                height: auto;
            }
            .left-section {
                margin-right: 50px; /* Thêm khoảng cách giữa bên trái và bên phải */
                margin-left: 100px;
            }
            .right-section {
                flex-grow: 1; /* Phần bên phải sẽ chiếm hết không gian còn lại */
                padding-right: 50px; /* Tùy chỉnh lề bên trái của phần bên phải */
            }
        </style>
    </head>
    <body>

        <%@ include file="../header.jsp" %>
        <%@ include file="../footer.jsp" %>
        <!-- Main Content -->
        <div class="main">
            <!-- Product Information -->
            <div class="product-info">
                <div class="left-section">
                    <img src="<c:out value='${product.avatar}' />" alt="${product.productName}">
                    <div class="price-info">
                        <p><strong>Giá cả:</strong> <span class="text-danger mb-2"><c:out value='${product.cost}' /> VNĐ/Năm</span></p>
                        <p><strong>Đối tượng tham gia:</strong> <c:out value='${product.conditions}' /></p>
                        <p><strong>Thời hạn bảo hiểm:</strong> 1 Năm</p>
                        <p><strong>Hình thức hợp đồng:</strong> Chứng nhận bảo hiểm điện tử</p>
                    </div>
                </div>
                <div class="right-section">
                    <div class="product-details">
                        <!-- Hiển thị tên sản phẩm -->
                        <h1><c:out value='${product.productName}' /></h1>
                        <!-- Hiển thị mô tả sản phẩm -->
                        <p><c:out value='${product.description}' /></p>
                        <button class="join-btn">Tham gia</button>
                    </div>
                    <!-- Related Products -->
                    <h2>Sản phẩm liên quan</h2>
                    <div class="related-products">

                        <c:forEach var="relatedProduct" items="${relatedProducts}">
                            <div class="product-card">
                                <img src="${relatedProduct.avatar}" alt="${relatedProduct.productName}">
                                <div class="card-body">
                                    <h5 class="card-title">${relatedProduct.productName}</h5>
                                    <p class="text-danger mb-2">Giá: ${relatedProduct.cost} VNĐ</p>
                                    <p>Đối tượng tham gia: ${relatedProduct.conditions}</p>
                                    <a href="productDetail?ProductID=${relatedProduct.productID}" class="btn btn-primary">Xem chi tiết</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


    </div>
</body>
</html>