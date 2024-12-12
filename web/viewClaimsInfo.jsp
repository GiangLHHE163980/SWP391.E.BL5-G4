<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>Xem Chi Tiết Bồi Thường</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <!-- Main Container -->
        <div class="container mt-4">
            <!-- Nút Quay Lại -->
            <a href="claimList" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>

            <!-- Giao Diện Chính -->
            <div class="row g-4">
                <!-- Phần Thông Tin Bồi Thường -->
                <div class="col-12">
                    <h1 class="display-6 fw-bold">Chi Tiết Bồi Thường</h1>
                    <div class="row g-3">
                        <!-- Mã Bồi Thường -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Mã Bồi Thường</h6>
                                <p class="text-muted mb-0">${claimID}</p>
                            </div>
                        </div>

                        <!-- Tên Đầy Đủ -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Họ và Tên</h6>
                                <p class="text-muted mb-0">${userFullName}</p>
                            </div>
                        </div>

                        <!-- Số Điện Thoại -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Số Điện Thoại</h6>
                                <p class="text-muted mb-0">${userPhoneNumber}</p>
                            </div>
                        </div>

                        <!-- Trạng Thái -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Trạng Thái</h6>
                                <p class="mb-0 fw-bold">
                                    <c:choose>
                                        <c:when test="${status == 'Approved'}">
                                            <span class="text-success">Đã Duyệt</span>
                                        </c:when>
                                        <c:when test="${status == 'Pending'}">
                                            <span class="text-warning">Đang Xử Lý</span>
                                        </c:when>
                                        <c:when test="${status == 'Rejected'}">
                                            <span class="text-danger">Bị Từ Chối</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span>${status}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>

                        <!-- Lý Do -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Lý Do</h6>
                                <p class="text-muted mb-0">${reason}</p>
                            </div>
                        </div>

                        <h2 class="mt-4 fw-bold">Chi Tiết Gói Bảo Hiểm</h2>
                        <!-- Tên Gói -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Tên Gói</h6>
                                <p class="text-muted mb-0">${productName}</p>
                            </div>
                        </div>

                        <!-- Trạng Thái Gói -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Trạng Thái Gói</h6>
                                <c:choose>
                                    <c:when test="${cardStatus == 'Active'}">
                                        <span class="text-success fw-bold">Còn Hạn</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger fw-bold">Hết Hạn</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Điều Kiện Gói -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Điều Kiện</h6>
                                <p class="text-muted mb-0">${productConditions}</p>
                            </div>
                        </div>

                        <!-- Mô Tả Gói -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Mô Tả</h6>
                                <p class="text-muted mb-0">${productDescription}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
