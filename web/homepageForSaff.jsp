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
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <!-- Header -->
    <header>
        <%@ include file="header.jsp" %>
    </header>

    <!-- Main Wrapper -->
    <div class="d-flex vh-100">
        <!-- Sidebar -->
        <nav class="bg-dark text-white flex-shrink-0" style="width: 250px;">
            <div class="text-center py-4">
                <h4 class="m-0">
                    <span class="text-danger">Giang</span>Staff
                </h4>
            </div>
            <ul class="nav flex-column px-3">
                <li class="nav-item">
                    <a href="#" class="nav-link text-white">
                        <i class="fas fa-home me-2"></i>Bảng điều khiển
                    </a>
                </li>
                <li class="nav-item">
                    <a href="CustomerForStaffController?action=showAllCustomer" class="nav-link text-white">
                        <i class="fas fa-users me-2"></i>Quản lý Khách hàng
                    </a>
                </li>
                <li class="nav-item">
                    <a href="CustomerForStaffController?action=showAllCardRequest" class="nav-link text-white">
                        <i class="fas fa-tasks me-2"></i>Xử lý Yêu cầu
                    </a>
                </li>
                <li class="nav-item">
                    <a href="ProductController?action=showFullProduct" class="nav-link text-white">
                        <i class="fas fa-box me-2"></i>Quản lý Sản phẩm
                    </a>
                </li>
            </ul>
        </nav>

        <!-- Main Content -->
        <main class="flex-grow-1 bg-light p-4">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb" class="bg-white rounded shadow-sm mb-4">
                <ol class="breadcrumb px-3 py-2 mb-0">
                    <li class="breadcrumb-item"><a href="#">Bảng Điều khiển</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Trang chủ</li>
                </ol>
            </nav>

            <!-- Overview Section -->
            <div class="container-fluid">
                <div class="row g-3">
                    <div class="col-md-4">
                        <div class="card text-bg-primary h-100">
                            <div class="card-body">
                                <h5 class="card-title">Khách hàng</h5>
                                <p class="card-text">Tổng số: <strong>${customerCount}</strong></p>
                                <a href="CustomerForStaffController?action=showAllCustomer" class="btn btn-light btn-sm">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-bg-success h-100">
                            <div class="card-body">
                                <h5 class="card-title">Yêu cầu bảo hiểm</h5>
                                <p class="card-text">Chờ xử lý: <strong>${RequestCount}</strong></p>
                                <a href="CustomerForStaffController?action=showAllCardRequest" class="btn btn-light btn-sm">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-bg-warning h-100">
                            <div class="card-body">
                                <h5 class="card-title">Sản phẩm bảo hiểm</h5>
                                <p class="card-text">Tổng số: <strong>${InsuranceCardCount}</strong></p>
                                <a href="ProductController?action=showFullProduct" class="btn btn-light btn-sm">Xem chi tiết</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
