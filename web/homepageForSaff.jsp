<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="wrapper d-flex">
        <!-- Sidebar -->
        <nav class="sidebar bg-dark">
            <div class="sidebar-header text-center text-white py-4">
                <h4><span style="color: #FF69B4;">Giang</span>Staff</h4>
            </div>
            <ul class="list-unstyled px-2">
                <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-home me-2"></i> Bảng điều khiển</a></li>
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
        <div class="main-content flex-grow-1 bg-light">
            <!-- Header -->
            <div class="header bg-white d-flex justify-content-between align-items-center p-3 border-bottom">
                <h4 class="mb-0">Bảng điều khiển</h4>
                <div class="dropdown profile-dropdown">
                    <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://via.placeholder.com/40" alt="Profile Picture">
                        <span class="ms-2">Hi, Admin</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="#">My Profile</a></li>
                        <li><a class="dropdown-item" href="#">Account Settings</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Logout</a></li>
                    </ul>
                </div>
            </div>

            <!-- Breadcrumb -->
            <div class="breadcrumb-container px-3 py-2">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a href="#">Bảng Điều khiển</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Trang chủ</li>
                    </ol>
                </nav>
            </div>

            <!-- Overview Section -->
            <div class="row px-3 my-4">
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Khách hàng</h5>
                            <p class="card-text">Tổng số: <strong>${customerCount}</strong></p>
                            <a href="CustomerForStaffController?action=showAllCustomer" class="btn btn-light btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Yêu cầu bảo hiểm</h5>
                            <p class="card-text">Chờ xử lý: <strong>${RequestCount}</strong></p>
                            <a href="CustomerForStaffController?action=showAllCardRequest" class="btn btn-light btn-sm">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-warning mb-3">
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

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
