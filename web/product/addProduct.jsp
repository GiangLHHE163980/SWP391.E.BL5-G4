<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        <h4 class="mb-0">Thêm/Chỉnh sửa Sản phẩm</h4>
                        <input type="text" class="form-control ms-3 search-input" placeholder="Search..." style="width: 300px;">
                    </div>
                    <div class="dropdown profile-dropdown">
                        <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://via.placeholder.com/40" alt="Profile Picture">
                            <span class="ms-2">Hi, Giang</span>
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
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ProductController?action=showFullProduct">Danh sách sản phẩm</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Thêm/Chỉnh sửa Sản phẩm</li>
                        </ol>
                    </nav>
                </div>

                <!-- Form to Add/Edit Product -->
                <div class="container">
                    <div class="form-section">
                        <form action="${pageContext.request.contextPath}/ProductController?action=addProduct" method="post">
                            <div class="mb-3">
                                <label for="productId" class="form-label">ID sản phẩm</label>
                                <input type="text" id="productId" name="productId" class="form-control" placeholder="Nhập ID sản phẩm" required readonly>
                            </div>
                            <div class="mb-3">
                                <label for="productName" class="form-label">Tên sản phẩm</label>
                                <input type="text" id="productName" name="productName" class="form-control" placeholder="Nhập tên sản phẩm" required>
                            </div>
                            <div class="mb-3">
                                <label for="insuranceType" class="form-label">Loại bảo hiểm</label>
                                <select id="insuranceType" name="insuranceType" class="form-select" required>
                                    <option value="Y tế">Y tế</option>
                                    <option value="Xe">Xe</option>
                                    <option value="Nhân thọ">Nhân thọ</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="cost" class="form-label">Chi phí</label>
                                <input type="text" id="cost" name="cost" class="form-control" placeholder="Nhập chi phí" required>
                            </div>
                            <div class="mb-3">
                                <label for="benefits" class="form-label">Quyền lợi</label>
                                <textarea id="benefits" name="benefits" class="form-control" rows="3" placeholder="Nhập quyền lợi bảo hiểm" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="conditions" class="form-label">Điều kiện</label>
                                <textarea id="conditions" name="conditions" class="form-control" rows="3" placeholder="Nhập điều kiện bảo hiểm" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="provider" class="form-label">Tên nhà cung cấp</label>
                                <input type="text" id="provider" name="provider" class="form-control" placeholder="Nhập tên nhà cung cấp" required>
                            </div>
                            <!-- Thêm trường Địa chỉ -->
                            <div class="mb-3">
                                <label for="address" class="form-label">Địa chỉ nhà cung cấp</label>
                                <input type="text" id="address" name="address" class="form-control" placeholder="Nhập địa chỉ" required>
                            </div>
                            <!-- Thêm trường Thông tin liên lạc -->
                            <div class="mb-3">
                                <label for="contactInfo" class="form-label">Thông tin liên lạc nhà cung cấp</label>
                                <input type="text" id="contactInfo" name="contactInfo" class="form-control" placeholder="Nhập thông tin liên lạc" required>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="bi bi-save"></i> Lưu sản phẩm
                            </button>
                            <a href="${pageContext.request.contextPath}/ProductController?action=showFullProduct" class="btn btn-secondary">
                                <i class="bi bi-arrow-left-circle"></i> Quay lại
                            </a>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="../footer.jsp" %>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
