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
                    <li><a href="${pageContext.request.contextPath}/HomePageForStaffController?action=homepageForStaff" class="text-white d-block py-2 px-3"><i class="fas fa-home me-2"></i> Bảng điều khiển</a></li>
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
                    <h4 class="mb-0">Danh sách khách hàng</h4>
                    <input type="text" class="form-control search-input ms-3" placeholder="Tìm kiếm...">
                </div>

                <!-- Breadcrumb -->
                <div class="breadcrumb-container px-3 py-2">
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
                        <div class="card-header">Danh sách khách hàng</div>
                        <div class="card-body">
                            <table class="table table-striped table-bordered table-sm">
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center">ID</th>
                                        <th class="text-center">Tên Đầy đủ</th>
                                        <th class="text-center">Email</th>
                                        <th class="text-center">Mã Thẻ bảo hiểm</th>
                                        <th class="text-center">Trạng thái thẻ</th>
                                        <th class="text-center">Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="customer" items="${listC}">
                                        <tr>
                                            <td class="text-center">${customer.userID}</td>
                                            <td class="text-center">${customer.fullName}</td>
                                            <td class="text-center">${customer.email}</td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${not empty customer.insuranceCard}">
                                                        ${customer.insuranceCard.cardNumber}
                                                    </c:when>
                                                    <c:otherwise>
                                                        N/A
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="text-center">
                                                <c:choose>
                                                    <c:when test="${not empty customer.insuranceCard}">
                                                        ${customer.insuranceCard.status}
                                                    </c:when>
                                                    <c:otherwise>
                                                        N/A
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=showFullCustomerInfo&CustomerId=${customer.userID}" class="btn btn-primary btn-sm">
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
