<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xem thông tin Khách hàng</title>
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
                    <div class="d-flex align-items-center">
                        <h4 class="mb-0">Xem thông tin Khách hàng</h4>
                    </div>
                    <div class="dropdown profile-dropdown">
                        <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://via.placeholder.com/40" alt="Profile Picture">
                            <span class="ms-2">Hi, Giang</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="#">My Profile</a></li>
                            <li><a class="dropdown-item" href="#">Logout</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Breadcrumb -->
                <div class="breadcrumb-container px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Khách hàng</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/CustomerForStaffController?action=showAllCustomer">Danh sách khách hàng</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Xem thông tin Khách hàng</li>
                        </ol>
                    </nav>
                </div>

                <!-- Customer Information -->
                <div class="container mt-4">
                    <div class="card mb-4">
                        <div class="card-header">Thông tin Khách hàng</div>
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/CustomerForStaffController?action=updateCardStatus" method="post">
                                <input type="hidden" name="userID" value="${listCI.userID}">
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="customerName" class="form-label">Tên đầy đủ:</label>
                                        <input type="text" class="form-control" id="customerName" value="${listCI.fullName}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="customerEmail" class="form-label">Email:</label>
                                        <input type="text" class="form-control" id="customerEmail" value="${listCI.email}" readonly>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="insuranceCard" class="form-label">Số thẻ bảo hiểm:</label>
                                        <input type="text" class="form-control" id="insuranceCard" value="${listCI.insuranceCard.cardNumber}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="cardStatus" class="form-label">Trạng thái thẻ:</label>
                                        <select class="form-select" id="cardStatus" name="cardStatus">
                                            <option value="Active" ${listCI.insuranceCard.status == 'Active' ? 'selected' : ''}>Active</option>
                                            <option value="Expired" ${listCI.insuranceCard.status == 'Expired' ? 'selected' : ''}>Expired</option>
                                            <option value="Revoked" ${listCI.insuranceCard.status == 'Revoked' ? 'selected' : ''}>Revoked</option>
                                        </select>


                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">Cập nhật trạng thái</button>
                            </form>
                        </div>
                    </div>

                    <!-- Insurance Requests -->
                    <div class="card mb-4">
                        <div class="card-header">Yêu cầu bảo hiểm</div>
                        <div class="card-body">
                            <table class="table table-striped table-bordered table-sm text-center align-middle">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên Sản phẩm</th>
                                        <th>Lý do</th>
                                        <th>Trạng thái</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <c:if test="${not empty listCI.claims}">
                                    <tbody>
                                        <c:forEach var="claim" items="${listCI.claims}">
                                            <tr>
                                                <td>${claim.claimID}</td>
                                                <td>${listCI.insuranceProduct.productName}</td>
                                                <td>${claim.reason}</td>
                                                <td>${claim.status}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateInsuranceRequestStatus&userID=${listCI.userID}&claimStatus=Approve" class="btn btn-success btn-sm">Approve</a>

                                                    <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateInsuranceRequestStatus&userID=${listCI.userID}&claimStatus=Reject" class="btn btn-danger btn-sm">Reject</a>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>



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
