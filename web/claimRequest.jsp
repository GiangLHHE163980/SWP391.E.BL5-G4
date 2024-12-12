<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Tin Yêu Cầu Bảo Hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <!-- Header -->
            <a href="   " class="btn btn-success mb-3 text-decoration-none">
                <i class="bi bi-arrow-left"></i> Trở Về
            </a>

            <h1 class="h4 fw-bold mt-3 mb-4">YÊU CẦU KHIẾU NẠI BẢO HIỂM</h1>

            <!-- Form Section -->
            <form action="ClaimsRequestController" method="post">
                <div class="row">                  

                    <div class="card p-4">

                        <!-- Form Fields -->
                        <!-- Card Details (Always Visible) -->
                        <div class="mb-4">
                            <h5 class="fw-bold mb-4">Chi Tiết Gói Bảo Hiểm Đã Chọn</h5>
                            <!-- Tên Gói -->
                            <div class="row">
                                <!-- Tên Gói -->
                                <div class="col-md-6 mb-3">
                                    <div class="card p-3">
                                        <h6 class="fw-bold">Tên Gói</h6>
                                        <p class="text-muted mb-0">${productName}</p>
                                    </div>
                                </div>

                                <!-- Trạng Thái Gói -->
                                <div class="col-md-6 mb-3">
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
                            </div>


                            <!-- Điều Kiện Gói -->
                            <div class="col-md-12 mb-3">
                                <div class="card p-3">
                                    <h6 class="fw-bold">Điều Kiện</h6>
                                    <p class="text-muted mb-0">${productConditions}</p>
                                </div>
                            </div>

                            <!-- Mô Tả Gói -->
                            <div class="col-md-12 mb-3">
                                <div class="card p-3">
                                    <h6 class="fw-bold">Mô Tả</h6>
                                    <p class="text-muted mb-0">${productDescription}</p>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <!-- Claim Type -->
                            <div class="col-md-6 mb-3">
                                <label for="claimType" class="fw-bold form-label">Loại khiếu nại <span class="text-danger"></span></label>
                                <input 
                                    type="text" 
                                    class="form-control" 
                                    id="claimType" 
                                    name="claimType" 
                                    value="${productType}" 
                                    readonly 
                                    style="height: 110px; font-size: 1.1rem;" 
                                />
                            </div>

                            <!-- Claim Reason -->
                            <div class="col-md-6 mb-3">
                                <label for="claimReason" class="fw-bold form-label">Lý do khiếu nại <span class="text-danger">*</span></label>
                                <textarea 
                                    class="form-control" 
                                    id="claimReason" 
                                    name="claimReason" 
                                    rows="4" 
                                    placeholder="Nhập lý do khiếu nại" 
                                    required
                                    ></textarea>
                            </div>
                        </div>


                    </div>

                </div>
                <!-- Hidden Inputs for UserID and ProductID -->
                <div class="d-flex justify-content-center mt-4">
                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-success me-2">Gửi yêu cầu</button>

                    <!-- Cancel Button -->
                    <button type="button" class="btn btn-secondary" onclick="window.location.href = 'your_cancel_link_here'">Hủy</button>
                </div>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
