<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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
            <a href="CustomerDashboardController" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>

            <!-- Main Layout -->
            <div class="row g-4">
                <!-- Avatar Section (1/3 of the page) -->
                <div class="col-md-4 text-center mt-5">
                    <img src="${user.avatar}" alt="User Avatar" class="rounded-circle img-fluid mb-3" style="max-width: 200px;">
                </div>

                <!-- User Info Section (2/3 of the page) -->
                <div class="col-md-8">
                    <div class="row g-3">
                        <h1 class="display-6 fw-bold">Thông tin của bạn</h1>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Họ và tên</h6>
                                <p class="text-muted mb-0">${user.fullName}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Ngày sinh</h6>
                                <!-- Check if birthDate is not null and format it -->
                                <p class="text-muted mb-0">
                                    <c:choose>
                                        <c:when test="${user.birthday != null}">
                                        <fmt:formatDate value="${user.birthday}" pattern="dd/MM/yyyy"/>
                                    </c:when>
                                    <c:otherwise>Chưa có thông tin</c:otherwise>
                                </c:choose>
                                </p>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Email</h6>
                                <p class="text-muted mb-0">${user.email}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Số điện thoại</h6>
                                <p class="text-muted mb-0">${user.phoneNumber}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">CMND/CCCD/Hộ chiếu</h6>
                                <p class="text-muted mb-0">${user.socialSecurityNumber}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Giới tính</h6>
                                <p class="text-muted mb-0">
                                    ${user.sex == "Male" ? "Nam" : user.sex == "Female" ? "Nữ" : user.sex}
                                </p>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Địa chỉ</h6>
                                <p class="text-muted mb-0">${user.address}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
