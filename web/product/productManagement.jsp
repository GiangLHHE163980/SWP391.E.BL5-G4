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
        <!-- Custom CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>
  <body>
       <header>
        <%@ include file="../header.jsp" %>
    </header>
        <div class="container-fluid p-0">
            <div class="row g-0">
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
                            <a href="CustomerForStaffController?action=showAllCustomer" class="nav-link text-white">
                                <i class="fas fa-users me-2"></i>Quản lý Khách hàng
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="CustomerForStaffController?action=showAllCardRequest" class="nav-link text-white">
                                <i class="fas fa-tasks me-2"></i>Xử lý Yêu cầu
                            </a>
                        </li>
                      
                    </ul>
                </nav>
                <!-- Header -->
   
                <!-- Main Content -->
                <main class="col bg-light">
                    <!-- Header -->
                    <div class="bg-white p-3 d-flex justify-content-between align-items-center border-bottom">
                        <h4 class="mb-0">Quản lý Sản phẩm Bảo hiểm</h4>
                        <form action="ProductController" method="get" class="d-flex">
                            <input type="hidden" name="action" value="showFullProduct">
                            <input type="text" class="form-control" name="search" placeholder="Tìm kiếm sản phẩm..." value="${search}">
                            <button class="btn btn-outline-success ms-2" type="submit">
                                <i class="bi bi-search"></i> Tìm kiếm
                            </button>
                        </form>
                    </div>

                    <!-- Breadcrumb -->
                    <div class="px-3 py-2">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a href="#">Sản phẩm</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Danh sách sản phẩm</li>
                            </ol>
                        </nav>
                    </div>

                    <!-- Add Product Button -->
                    <div class="text-end my-3 px-3">
                        <a href="${pageContext.request.contextPath}/ProductController?action=showAddPage" class="btn btn-success">
                            <i class="bi bi-plus"></i> Thêm mới Sản phẩm
                        </a>
                    </div>

                    <!-- Product Table -->
                    <div class="card mx-3">
                        <div class="card-header bg-white">
                            <h5 class="mb-0">Bảng Thông tin</h5>
                        </div>
                        <div class="card-body">
                            <table id="datatable" class="table table-striped table-bordered table-sm align-middle text-center">
                                <thead class="table-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Loại bảo hiểm</th>
                                        <th>Quyền lợi</th>
                                        <th>Chi phí</th>
                                        <th>Điều kiện</th>
                                        <th>Nhà cung cấp</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${productList}">
                                        <tr>
                                            <td>${product.productID}</td>
                                            <td>${product.productName}</td>
                                            <td>${product.insuranceType}</td>
                                            <td>${product.description}</td>
                                            <td>${product.cost} VNĐ/năm</td>
                                            <td>${product.conditions}</td>
                                            <td>${product.insuranceCompany.companyName}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/ProductController?action=showViewPage&product_id=${product.productID}" class="btn btn-primary btn-sm" title="Xem chi tiết">
                                                    <i class="bi bi-eye"></i>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/ProductController?action=showEditPage&product_id=${product.productID}" class="btn btn-warning btn-sm" title="Chỉnh sửa">
                                                    <i class="bi bi-pencil-square"></i>
                                                </a>
                                                <a href="${pageContext.request.contextPath}/ProductController?action=deleteProduct&product_id=${product.productID}" class="btn btn-danger btn-sm" title="Xóa">
                                                    <i class="bi bi-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </main>
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
                $('#datatable').DataTable({
                    paging: true,
                    searching: true,
                    info: true,
                    lengthChange: true
                });
            });
        </script>
    </body>
</html>
