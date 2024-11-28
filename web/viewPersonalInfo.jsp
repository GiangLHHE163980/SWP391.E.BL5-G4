<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>View Personal Info</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
          <!-- Main Container -->
    <div class="container mt-4">
        <!-- Return Button -->
        <a href="home.jsp" class="btn btn-success mb-3">
            <i class="bi bi-arrow-left"></i> Quay lại
        </a>

        <!-- Main Layout -->
        <div class="row g-4">
            <!-- Avatar Section (1/3 of the page) -->
            <div class="col-md-4 text-center mt-5">
                <img src="https://via.placeholder.com/200" alt="User Avatar" class="rounded-circle img-fluid mb-3" style="max-width: 200px;">
                
            </div>

            <!-- User Info Section (2/3 of the page) -->
            <div class="col-md-8">
                
                <div class="row g-3">
                    <h1 class="display-6 fw-bold">Thông tin của bạn</h1>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">Họ và tên</h6>
                            <p class="text-muted mb-0">Nguyễn Văn A</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">Ngày sinh</h6>
                            <p class="text-muted mb-0">01/01/1990</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">Email</h6>
                            <p class="text-muted mb-0">example@gmail.com</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">Số điện thoại</h6>
                            <p class="text-muted mb-0">0373248749</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">CMND/CCCD/Hộ chiếu</h6>
                            <p class="text-muted mb-0">123456789</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-3">
                            <h6 class="fw-bold">Giới tính</h6>
                            <p class="text-muted mb-0">Nam</p>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="card p-3">
                            <h6 class="fw-bold">Địa chỉ</h6>
                            <p class="text-muted mb-0">123 Đường ABC, Quận X, TP. Hồ Chí Minh</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
