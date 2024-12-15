<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Trang Quản lý</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <!-- Custom CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
    <div>
          <!-- Header -->
        <%@ include file="header.jsp" %>
    </div>
    <div class="wrapper d-flex flex-nowrap vh-100">
    <!-- Sidebar -->
    <nav class="sidebar bg-dark flex-shrink-0" style="width: 250px;">
        <div class="sidebar-header text-center text-white py-4">
            <h4 class="m-0"><span style="color: #FF69B4;">Giang</span>Staff</h4>
        </div>
        <ul class="list-unstyled px-2">
            <li>
                <a href="#" class="text-white d-block py-2 px-3">
                    <i class="fas fa-home me-2"></i> Bảng điều khiển
                </a>
            </li>
            <li>
                <a href="CustomerForStaffController?action=showAllCustomer" class="text-white d-block py-2 px-3">
                    <i class="fas fa-users me-2"></i> Quản lý Khách hàng
                </a>
            </li>
            <li>
                <a href="CustomerForStaffController?action=showAllCardRequest" class="text-white d-block py-2 px-3">
                    <i class="fas fa-tasks me-2"></i> Xử lý Yêu cầu
                </a>
            </li>
            <li>
                <a href="ProductController?action=showFullProduct" class="text-white d-block py-2 px-3">
                    <i class="fas fa-box me-2"></i> Quản lý Sản phẩm
                </a>
            </li>
        </ul>
    </nav>

    <!-- Main Content -->
    <div class="main-content flex-grow-1 bg-light px-4 pt-4" style="padding-bottom: 2rem;">
       

        <!-- Breadcrumb -->
        <div class="breadcrumb-container py-2 px-3 bg-white rounded shadow-sm mb-4">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb mb-0">
                    <li class="breadcrumb-item"><a href="#">Bảng Điều khiển</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Trang chủ</li>
                </ol>
            </nav>
        </div>

        <!-- Overview Section -->
        <div class="container-fluid">
            <div class="row row-cols-1 row-cols-md-3 g-3">
                <div class="col">
                    <div class="card text-white bg-primary h-100">
                        <div class="card-body">
                            <h5 class="card-title">Khách hàng</h5>
                            <p class="card-text">Tổng số: <strong>${customerCount}</strong></p>
                            <a href="CustomerForStaffController?action=showAllCustomer" class="btn btn-light btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-white bg-success h-100">
                        <div class="card-body">
                            <h5 class="card-title">Yêu cầu bảo hiểm</h5>
                            <p class="card-text">Chờ xử lý: <strong>${RequestCount}</strong></p>
                            <a href="CustomerForStaffController?action=showAllCardRequest" class="btn btn-light btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="card text-white bg-warning h-100">
                        <div class="card-body">
                            <h5 class="card-title">Sản phẩm bảo hiểm</h5>
                            <p class="card-text">Tổng số: <strong>${InsuranceCardCount}</strong></p>
                            <a href="ProductController?action=showFullProduct" class="btn btn-light btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
