<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Sản Phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #007b5e;
        }
        .navbar-brand img {
            height: 40px;
        }
        .category-list a {
            text-decoration: none;
            color: #007b5e;
        }
        .category-list a.active {
            font-weight: bold;
            background-color: #dff5eb;
            border-left: 4px solid #007b5e;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
        }
        .product-card img {
            max-height: 150px;
            object-fit: cover;
        }
        .product-card .card-body {
            padding: 15px;
        }
        .btn-primary {
            background-color: #ff9900;
            border: none;
        }
        .btn-primary:hover {
            background-color: #cc7a00;
        }
        .border-container {
            margin: 15px;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            background-color: #ffffff;
        }
        .vertical-divider {
            border-right: 1px solid #ddd;
            height: 100%;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
     <%@ include file="header.jsp" %>
    <!-- Main Content -->
    <div class="container-fluid mt-2">
    <!-- Search Bar -->
    <div class="mb-4">
        <form method="get" action="">
            <input type="hidden" name="action" value="showAllProduct">
            <input type="hidden" name="category" value="${param.category}">
            <input type="text" name="searchQuery" class="form-control mb-2" placeholder="Tìm kiếm sản phẩm..." value="${param.searchQuery}">

            <!-- Dropdown Sort -->
            <div class="row">
                <div class="col-md-4">
                    <label for="sortBy" class="form-label">Sắp xếp theo:</label>
                    <select name="sortBy" id="sortBy" class="form-select" onchange="this.form.submit()">
                        <option value="" ${empty param.sortBy ? "selected" : ""}>Mặc định</option>
                        <option value="costAsc" ${param.sortBy == 'costAsc' ? "selected" : ""}>Giá: Tăng dần</option>
                        <option value="costDesc" ${param.sortBy == 'costDesc' ? "selected" : ""}>Giá: Giảm dần</option>
                        <option value="dateNewest" ${param.sortBy == 'dateNewest' ? "selected" : ""}>Ngày: Mới nhất</option>
                        <option value="dateOldest" ${param.sortBy == 'dateOldest' ? "selected" : ""}>Ngày: Cũ nhất</option>
                    </select>
                </div>
                <div class="col-md-2 align-self-end">
                    <button type="submit" class="btn btn-primary">Áp dụng</button>
                </div>
            </div>
        </form>
    </div>

    <!-- Content Container -->
    <div class="row border-container">
        <!-- Sidebar -->
        <div class="col-md-2 vertical-divider pe-3">
            <h5 class="mb-3">Lọc theo:</h5>
            <div class="category-list">
                <c:forEach var="type" items="${insuranceTypes}">
                    <a href="?action=showAllProduct&category=${type.insuranceType}&searchQuery=${param.searchQuery}" 
                       class="d-block py-2 px-3 rounded ${type.insuranceType == selectedCategory ? 'active' : ''}">
                        ${type.insuranceType}
                    </a>
                </c:forEach>
</div>
        </div>

        <!-- Product List -->
        <div class="col-md-9">
            <div class="row">
                <c:forEach var="product" items="${products}">
                    <div class="col-md-4 mb-4">
                        <div class="card product-card">
                            <img src="${product.avatar}" class="card-img-top" alt="${product.productName}">
                            <div class="card-body">
                                <h5 class="card-title">${product.productName}</h5>
                                <p class="text-danger mb-2">Chỉ từ ${product.cost} VNĐ/năm</p>
                                <ul>
                                    <li>${product.description}</li>
                                    <li>${product.conditions}</li>
                                </ul>
                                <a href="#" class="btn btn-primary">Tham gia</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

    <!-- Footer -->
    <%@ include file="footer.jsp" %>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>