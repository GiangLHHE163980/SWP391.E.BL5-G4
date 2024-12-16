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
        <style>
            html, body {
                height: 100%;
                margin: 0;
            }

            .wrapper {
                display: flex;
                height: 100%;
                overflow: hidden;
            }

            nav {
                width: 250px;
                height: 100vh; /* Full screen height for sidebar */
                position: fixed;
                top: 0;
                left: 0;
                bottom: 0;
            }

            .main-content {
                margin-left: 250px; /* Offset for the sidebar */
                flex-grow: 1;
                height: 100vh;
                overflow-y: auto; /* Allow scrolling if content is long */
            }

            .header {
                height: 80px; /* Adjust header height */
            }

            .breadcrumb-container {
                background-color: #f8f9fa;
                padding: 15px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper d-flex">
            <!-- Sidebar -->
            <nav class="bg-dark text-white flex-shrink-0" style="width: 250px;">
                <div class="text-center py-4">
                    <h4 class="m-0">
                        <span class="text-danger">Giang</span>Staff
                    </h4>
                </div>
                <ul class="nav flex-column px-3">
                    <li class="nav-item">
                        <a href="#" class="nav-link text-white">
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
                    <li class="nav-item">
                        <a href="ProductController?action=showFullProduct" class="nav-link text-white">
                            <i class="fas fa-box me-2"></i>Quản lý Sản phẩm
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
                                        <input type="text" class="form-control" id="customerName" value="${listCl[0].user.fullName}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="customerEmail" class="form-label">Email:</label>
                                        <input type="text" class="form-control" id="customerEmail" value="${listCl[0].user.email}" readonly>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-6">
                                        <label for="insuranceCard" class="form-label">Số thẻ bảo hiểm:</label>
                                        <input type="text" class="form-control" id="insuranceCard" value="${listCl[0].insuranceCard.cardID}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="cardStatus" class="form-label">Trạng thái thẻ:</label>
                                        <select class="form-select" id="cardStatus" name="cardStatus">
                                            <option value="Active">Active</option>
                                            <option value="Expired">Expired</option>
                                            <option value="Revoked">Revoked</option>
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
                                <c:if test="${not empty listCl}">
                                    <tbody>
                                        <c:forEach var="claim" items="${listCl}">
                                            <tr>
                                                <td>${claim.claimID}</td>
                                                <td>${claim.insuranceCard.insuranceProduct.productName}</td>
                                                <td>${claim.reason}</td>
                                                <td>${claim.status}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateInsuranceRequestStatus&claimID=${claim.claimID}&claimStatus=Approve&CustomerId=${claim.user.userID}&CardId=${claim.insuranceCard.cardID}" class="btn btn-success btn-sm">Approve</a>
                                                    <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=updateInsuranceRequestStatus&claimID=${claim.claimID}&claimStatus=Reject&CustomerId=${claim.user.userID}&CardId=${claim.insuranceCard.cardID}" class="btn btn-danger btn-sm">Reject</a>
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
