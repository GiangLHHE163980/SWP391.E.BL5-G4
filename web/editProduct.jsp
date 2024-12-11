<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm/Chỉnh sửa Sản phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="./assets/css/style.css">
</head>
<body>
    <div class="wrapper d-flex">
        <!-- Sidebar -->
        <nav class="sidebar bg-dark">
            <div class="sidebar-header text-center text-white py-4">
                <h4><span style="color: #FF69B4;">Giang</span>Admin</h4>
            </div>
            <ul class="list-unstyled px-2">
                <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-home me-2"></i> Dashboard</a></li>
                <li>
                    <a href="#tablesSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle text-white d-block py-2 px-3">
                        <i class="fas fa-table me-2"></i> Tables
                    </a>
                    <ul class="collapse list-unstyled ps-4" id="tablesSubmenu">
                        <li><a href="#" class="text-white d-block py-2">Basic Table</a></li>
                        <li><a href="#" class="text-white d-block py-2">Datatables</a></li>
                    </ul>
                </li>
                <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-chart-bar me-2"></i> Charts</a></li>
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
                        <li class="breadcrumb-item"><a href="#">Tables</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Thêm/Chỉnh sửa Sản phẩm</li>
                    </ol>
                </nav>
            </div>

            <!-- Form to Add/Edit Product -->
            <div class="container">
                <div class="form-section">
                    <form action="${pageContext.request.contextPath}/ProductController?action=editProduct" method="post">
                        <div class="mb-3">
                            <label for="productId" class="form-label">ID sản phẩm</label>
                            <input value="${productList[0].productID}" type="text" id="productId" name="productId" class="form-control" placeholder="Nhập ID sản phẩm" required readonly>
                        </div>
                        <div class="mb-3">
                            <label for="productName" class="form-label">Tên sản phẩm</label>
                            <input value="${productList[0].productName}" type="text" id="productName" name="productName" class="form-control" placeholder="Nhập tên sản phẩm" required>
                        </div>
                        <div class="mb-3">
                            <label for="insuranceType" class="form-label">Loại bảo hiểm</label>
                            <select id="insuranceType" name="insuranceType" class="form-select" required>
                                <option value="">Chọn loại bảo hiểm</option>
                                <option value="Y tế" <c:if test="${productList[0].insuranceType == 'Y tế'}">selected</c:if>>Y tế</option>
                                <option value="Xe" <c:if test="${productList[0].insuranceType == 'Xe'}">selected</c:if>>Xe</option>
                                <option value="Nhân thọ" <c:if test="${productList[0].insuranceType == 'Nhân thọ'}">selected</c:if>>Nhân thọ</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="cost" class="form-label">Chi phí</label>
                            <input value="${productList[0].cost}" type="text" id="cost" name="cost" class="form-control" placeholder="Nhập chi phí" required>
                        </div>
                        <div class="mb-3">
                            <label for="benefits" class="form-label">Quyền lợi</label>
                            <textarea id="benefits" name="benefits" class="form-control" rows="3" placeholder="Nhập quyền lợi bảo hiểm" required>${productList[0].description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="conditions" class="form-label">Điều kiện</label>
                            <textarea id="conditions" name="conditions" class="form-control" rows="3" placeholder="Nhập điều kiện bảo hiểm" required>${productList[0].conditions}</textarea>
                        </div>
                        <div class="mb-3">
                            <label for="companyID" class="form-label">ID nhà cung cấp</label>
                            <input value="${productList[0].insuranceCompany.companyID}" type="text" id="companyID" name="companyID" class="form-control" placeholder="Nhập ID nhà cung cấp" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="provider" class="form-label">Tên nhà cung cấp</label>
                            <input value="${productList[0].insuranceCompany.companyName}" type="text" id="provider" name="provider" class="form-control" placeholder="Nhập tên nhà cung cấp" required>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ nhà cung cấp</label>
                            <input value="${productList[0].insuranceCompany.address}" type="text" id="address" name="address" class="form-control" placeholder="Nhập địa chỉ" required>
                        </div>
                        <div class="mb-3">
                            <label for="contactInfo" class="form-label">Thông tin liên lạc nhà cung cấp</label>
                            <input value="${productList[0].insuranceCompany.contactInfo}" type="text" id="contactInfo" name="contactInfo" class="form-control" placeholder="Nhập thông tin liên lạc" required>
                        </div>
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-save"></i> Lưu sản phẩm
                        </button>
                        <a href="${pageContext.request.contextPath}/ProductController?action=showFullProduct" class="btn btn-secondary">
                            <i class="bi bi-arrow-left"></i> Quay lại
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
       <!-- Footer -->
        <%@ include file="footer.jsp" %>                           
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>