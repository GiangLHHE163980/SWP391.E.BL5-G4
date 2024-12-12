<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>Thông Tin Thẻ Bảo Hiểm</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <!-- Main Container -->
        <div class="container mt-4">
            <!-- Return Button -->
            <a href="insuranceCards?userID=${userID}" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>

            <!-- Main Layout -->
            <div class="row g-4">
                <!-- Insurance Info Section -->
                <div class="col-12">
                    <h1 class="display-6 fw-bold">Chi Tiết Thẻ Bảo Hiểm</h1>
                    <div class="row g-3">
                        <!-- Card ID -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Mã Thẻ</h6>
                                <p class="text-muted mb-0">${cardDetails.cardID}</p>
                            </div>
                        </div>

                        <!-- Status -->
                        <div class="col-md-6">
                            <div class="card p-2">
                                <h6 class="fw-bold">Tình Trạng</h6>
                                <c:choose>
                                    <c:when test="${cardDetails.status == 'Active'}">
                                        <p class="text-success fw-bold">Còn Hạn</p>
                                    </c:when>
                                    <c:when test="${cardDetails.status == 'Pending'}">
                                        <p class="text-warning fw-bold">Chờ Xử Lý</p>
                                    </c:when>
                                    <c:when test="${cardDetails.status == 'Pending Renew'}">
                                        <p class="text-warning fw-bold">Chờ Gia Hạn</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-danger fw-bold">Hết Hạn</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <!-- Start Date -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Ngày Bắt Đầu</h6>
                                <p class="text-muted mb-0"><fmt:formatDate value="${cardDetails.startDate}" pattern="dd/MM/yyyy" /></p>
                            </div>
                        </div>

                        <!-- End Date -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Ngày Kết Thúc</h6>
                                <p class="text-muted mb-0"><fmt:formatDate value="${cardDetails.endDate}" pattern="dd/MM/yyyy" /></p>
                            </div>
                        </div>

                        <!-- Product Details Section -->
                        <h2 class="mt-4 fw-bold">Chi Tiết Sản Phẩm</h2>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Tên Sản Phẩm</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.productName}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Chi Phí</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.cost}0 VND</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Mô Tả</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.description}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Điều Kiện</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.conditions}</p>
                            </div>
                        </div>

                        <!-- Company Details Section -->
                        <h2 class="mt-4 fw-bold">Chi Tiết Công Ty</h2>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Tên Công Ty</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.companyName}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Thông Tin Liên Hệ</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.contactInfo}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Địa Chỉ</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.address}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
