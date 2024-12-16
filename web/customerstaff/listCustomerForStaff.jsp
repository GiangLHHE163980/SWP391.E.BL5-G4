<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách khách hàng</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!-- Custom CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>
        <header>
            <%@ include file="../header.jsp" %>
        </header>
        <div class="d-flex">
            <!-- Sidebar -->
            <nav class="col-auto bg-dark text-white vh-100">
                <div class="text-center py-4">
                    <h4 class="m-0">
                        <span class="text-danger">Giang</span>Staff
                    </h4>
                </div>
                <ul class="nav flex-column px-3">
                    <li class="nav-item">
                        <a href="HomePageForStaffController?action=homepageForStaff" class="nav-link text-white">
                            <i class="fas fa-home me-2"></i>Bảng điều khiển
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
            <div class="flex-grow-1 bg-light">
                <!-- Header -->
                <div class="bg-white p-3 border-bottom d-flex justify-content-between align-items-center">
                    <h4 class="mb-0">Danh sách khách hàng</h4>
                    <input type="text" class="form-control w-25" placeholder="Tìm kiếm...">
                </div>

                <!-- Breadcrumb -->
                <div class="px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Khách hàng</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách khách hàng</li>
                        </ol>
                    </nav>
                </div>

                <!-- Customer List Table -->
                <div class="container mt-4">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            Danh sách khách hàng
                        </div>
                        <div class="card-body">
                            <table class="table table-hover table-bordered text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên Đầy đủ</th>
                                        <th>Email</th>
                                        <th>Mã Thẻ bảo hiểm</th>
                                        <th>Trạng thái thẻ</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="customer" items="${listC}">
                                        <tr>
                                            <td>${customer.userID}</td>
                                            <td>${customer.fullName}</td>
                                            <td>${customer.email}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty customer.insuranceCard}">
                                                        ${customer.insuranceCard.cardNumber}
                                                    </c:when>
                                                    <c:otherwise>
                                                        N/A
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty customer.insuranceCard}">
                                                        ${customer.insuranceCard.status}
                                                    </c:when>
                                                    <c:otherwise>
                                                        N/A
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=showFullCustomerInfo&CustomerId=${customer.userID}&CardId=${customer.insuranceCard.cardID}" class="btn btn-primary btn-sm">
                                                    <i class="bi bi-eye"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
