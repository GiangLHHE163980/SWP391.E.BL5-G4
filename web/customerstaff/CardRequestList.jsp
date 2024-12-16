<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý Sản phẩm Bảo hiểm</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Datatables CSS -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="d-flex flex-column" style="height: 100vh;">

        <header>
            <%@ include file="../header.jsp" %>
        </header>

        <div class="d-flex flex-fill">
            <!-- Sidebar -->
            <nav class="sidebar bg-dark text-white p-3" style="width: 250px; height: 100vh;">
                <div class="text-center py-4">
                    <h4 class="m-0">
                        <span class="text-danger">Giang</span>Staff
                    </h4>
                </div>
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a href="HomePageForStaffController?action=homepageForStaff" class="nav-link text-white">
                            <i class="fas fa-home me-2"></i>Bảng điều khiển
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="CustomerForStaffController?action=showAllCustomer" class="nav-link text-white">
                            <i class="fas fa-users me-2"></i>Quản lý Khách hàng
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
            <div class="main-content flex-fill bg-light">
                <!-- Header -->
                <div class="d-flex justify-content-between align-items-center p-3 border-bottom bg-white shadow-sm">
                    <h4 class="mb-0 text-dark">Quản lý Yêu cầu thẻ</h4>
                    <form action="ProductController" method="get" class="d-flex">
                        <input type="hidden" name="action" value="showFullProduct">
                        <input type="text" class="form-control" name="search" placeholder="Tìm kiếm ..." value="${search}">
                        <button class="btn btn-outline-success ms-2" type="submit">
                            <i class="bi bi-search"></i> Tìm kiếm
                        </button>
                    </form>
                </div>

                <!-- Breadcrumb -->
                <div class="px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Yêu cầu thẻ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách yêu cầu thẻ</li>
                        </ol>
                    </nav>
                </div>

                <!-- Add Product Button -->
                <div class="text-end my-3 px-3">
                    <a href="#" class="btn btn-outline-success">
                        <i class="bi bi-plus"></i> Thêm mới Phản hồi
                    </a>
                </div>

                <!-- Table Card -->
                <div class="card mx-3">
                    <div class="card-header bg-white">
                        <h5>Bảng Thông tin</h5>
                    </div>
                    <div class="card-body">
                        <table id="datatable" class="table table-striped table-bordered text-center align-middle">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên Khách hàng</th>
                                    <th>Ngày sinh</th>
                                    <th>Giới tính</th>
                                    <th>Sản phẩm lựa chọn</th>
                                    <th>Giá trị</th>
                                    <th>Thời hạn</th>
                                    <th>Xác Nhận thương tật</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="request" items="${listR}">
                                    <tr>
                                        <td>${request.cardID}</td>
                                        <td>${request.user.fullName}</td>
                                        <td>${request.user.birthday}</td>
                                        <td>${request.user.sex}</td>
                                        <td>${request.insuranceProduct.productName}</td>
                                        <td>${request.insuranceProduct.cost}</td>
                                        <td>1 năm</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${request.isHandicapped}">Có thương tật</c:when>
                                                <c:otherwise>Không có thương tật</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${request.status}</td>
                                        <td>
                                            <a href="#" class="btn btn-primary btn-sm">
                                                <i class="bi bi-eye"></i>
                                            </a>
                                            <a href="#" class="btn btn-warning btn-sm">
                                                <i class="bi bi-check"></i>
                                            </a>
                                            <a href="#" class="btn btn-danger btn-sm">
                                                <i class="bi bi-x-circle-fill"></i>
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

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- Datatables JS -->
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#datatable').DataTable();
            });
        </script>

    </body>
</html>
