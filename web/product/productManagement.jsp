<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <div class="d-flex align-items-center">
                        <h4 class="mb-0">Staff</h4>
                        <div class="mb-1 ms-2">
                            <form action="ProductController" method="get">
                                <div class="input-group">
                                    <!-- Tham số action cố định giá trị showFullProduct -->
                                    <input type="hidden" name="action" value="showFullProduct">
                                    <input type="text" class="form-control rounded" name="search" placeholder="Tìm kiếm sản phẩm..." value="${search}">
                                    <button class="btn btn-outline-success rounded px-2 ms-2" type="submit">
                                        <i class="bi bi-search"></i> Tìm kiếm
                                    </button>

                                </div>
                            </form>
                        </div>

                    </div>
                    <div class="dropdown profile-dropdown">
                        <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://via.placeholder.com/40" alt="Profile Picture">
                            <span class="ms-2">Hi, Hizria</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">My Profile</a></li>
                            <li><a class="dropdown-item" href="#">My Balance</a></li>
                            <li><a class="dropdown-item" href="#">Inbox</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Account Settings</a></li>
                            <li><a class="dropdown-item" href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Breadcrumb -->
                <div class="breadcrumb-container px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Sản phẩm</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách sản phẩm</li>
                        </ol>
                    </nav>
                </div>
                <!-- Nút thêm mới sản phẩm -->
                <div class="mb-1 mt-4 text-end">
                    <a href="${pageContext.request.contextPath}/ProductController?action=showAddPage" class="btn btn-outline-success">
                        <i class="bi bi-plus"></i>
                        Thêm mới Sản phẩm</a>
                </div>

                <!-- Table Card -->
                <div class="card mx-3 my-4">
                    <div class="card-header bg-white">
                        <h5>Bảng Thông tin</h5>
                    </div>
                    <div class="card-body">
                        <table id="datatable" class="table table-striped table-bordered table-sm text-center align-middle">

                            <thead>
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
                                <!-- Loop to display products dynamically -->
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
                                            <a href="${pageContext.request.contextPath}/ProductController?action=showViewPage&product_id=${product.productID}" 
                                               class="btn btn-primary btn-sm" title="Xem chi tiết">
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
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- Datatables -->
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#datatable').DataTable({
                    paging: false,
                    searching: false,
                    info: false,
                    lengthChange: false,
                });
            });
        </script>
       
    </body>
</html>