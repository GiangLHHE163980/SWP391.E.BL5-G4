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
                                    <input type="text" class="form-control rounded" name="search" placeholder="Tìm kiếm ..." value="${search}">
                                    <button class="btn btn-outline-success rounded px-2 ms-2" type="submit">
                                        <i class="bi bi-search"></i> Tìm kiếm
                                    </button>

                                </div>
                            </form>
                        </div>

                    </div>
<!--                    <div class="dropdown profile-dropdown">
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
                    </div>-->
                </div>

                <!-- Breadcrumb -->
                <div class="breadcrumb-container px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Yêu cầu thẻ</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Danh sách yêu cầu thẻ</li>
                        </ol>
                    </nav>
                </div>
                <!-- Nút thêm mới sản phẩm -->
                <div class="mb-1 mt-4 text-end">
                    <a href="#" class="btn btn-outline-success">
                        <i class="bi bi-plus"></i>
                        Thêm mới Phản hồi</a>
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
                                <!-- Loop to display products dynamically -->
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
                                                <c:when test="${request.isHandicapped}">
                                                    Có thương tật
                                                </c:when>
                                                <c:otherwise>
                                                    Không có thương tật
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td>${request.status}</td>
                                        <!--                                    
                                        -->                                        <td>
                                            <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=showFullRequestCardInfo&request_id=${request.cardID}" 
                                               class="btn btn-primary btn-sm" title="Xem chi tiết">
                                                <i class="bi bi-eye"></i>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateCardStatusByCardId&request_id=${request.cardID}&cardStatus=Active" class="btn btn-warning btn-sm" title="Đồng ý">
                                                <i class="bi bi-check" style="color: green;"></i>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateCardStatusByCardId&request_id=${request.cardID}&cardStatus=Rejected" class="btn btn-danger btn-sm" title="loại bỏ">
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