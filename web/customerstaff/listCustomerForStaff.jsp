<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Customer List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>
    <body>
        <div class="wrapper d-flex">
            <!-- Sidebar -->
            <nav class="sidebar bg-dark">
                <div class="sidebar-header text-center text-white py-4">
                    <h4><span style="color: #FF69B4;">Giang</span>Admin</h4>
                </div>
                <ul class="list-unstyled px-2">
                    <li><a href="HomePageForStaffController?action=homepageForStaff" class="text-white d-block py-2 px-3"><i class="fas fa-home me-2"></i> Dashboard</a></li>
                    <li>
                        <a href="#tablesSubmenu" data-bs-toggle="collapse" aria-expanded="false" class="dropdown-toggle text-white d-block py-2 px-3">
                            <i class="fas fa-table me-2"></i> Customers
                        </a>
                        <ul class="collapse list-unstyled ps-4" id="tablesSubmenu">
                            <li><a href="customerList.jsp" class="text-white d-block py-2">Customer List</a></li>
                        </ul>
                    </li>
                    <li><a href="#" class="text-white d-block py-2 px-3"><i class="fas fa-chart-bar me-2"></i> Reports</a></li>
                    <li><a href="logout.jsp" class="text-white d-block py-2 px-3"><i class="fas fa-sign-out-alt me-2"></i> Logout</a></li>
                </ul>
            </nav>

            <!-- Main Content -->
            <div class="main-content flex-grow-1 bg-light">
                <!-- Header -->
                <div class="header bg-white d-flex justify-content-between align-items-center p-3 border-bottom">
                    <h4 class="mb-0">Customer List</h4>
                    <input type="text" class="form-control search-input ms-3" placeholder="Search...">
                </div>

                <!-- Breadcrumb -->
                <div class="breadcrumb-container px-3 py-2">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mb-0">
                            <li class="breadcrumb-item"><a href="#">Customers</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Customer List</li>
                        </ol>
                    </nav>
                </div>

                <!-- Customer List Table -->
                <div class="container mt-4">
                    <div class="card">
                        <div class="card-header">Customer List</div>
                        <div class="card-body">
                            <table class="table table-striped table-bordered table-sm">
                                <thead class="thead-dark">
                                    <tr>
                                        <th class="text-center">ID</th>
                                        <th class="text-center">Name</th>
                                        <th class="text-center">Email</th>
                                        <th class="text-center">Insurance Card</th>
                                        <th class="text-center">Card Status</th>
                                        <th class="text-center">Actions</th>
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
                                                <a href="${pageContext.request.contextPath}/CustomerForStaffController?action=showFullCustomerInfo&CustomerId=${customer.userID}" class="btn btn-primary btn-sm">View</a>
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
