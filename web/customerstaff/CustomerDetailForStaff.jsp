<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Customer</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Bootstrap Icons -->
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
                <div class="d-flex align-items-center">
                    <h4 class="mb-0">View Customer</h4>
                    <input type="text" class="form-control ms-3 search-input" placeholder="Search...">
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
                        <li class="breadcrumb-item"><a href="#">Customers</a></li>
                        <li class="breadcrumb-item active" aria-current="page">View Customer</li>
                    </ol>
                </nav>
            </div>

            <!-- Customer Information -->
            <div class="container mt-4">
                <div class="card mb-4">
                    <div class="card-header">Customer Information</div>
                    <div class="card-body">
                        <form>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="customerName" class="form-label">Name:</label>
                                    <input type="text" class="form-control" id="customerName" value="${listCI[0].fullName}" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label for="customerEmail" class="form-label">Email:</label>
                                    <input type="text" class="form-control" id="customerEmail" value="${listCI[0].email}" readonly>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="insuranceCard" class="form-label">Insurance Card:</label>
                                    <input type="text" class="form-control" id="insuranceCard" value="${listCI[0].insuranceCard.cardNumber}" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label for="cardStatus" class="form-label">Card Status:</label>
                                    <select class="form-select" id="cardStatus">
                                        <option value="active" ${listCI[0].insuranceCard.status == 'active' ? 'selected' : ''}>Active</option>
                                        <option value="expired" ${listCI[0].insuranceCard.status == 'expired' ? 'selected' : ''}>Expired</option>
                                        <option value="revoked" ${listCI[0].insuranceCard.status == 'revoked' ? 'selected' : ''}>Revoked</option>
                                    </select>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary">Update Status</button>
                        </form>
                    </div>
                </div>

                <!-- Insurance Requests -->
                <div class="card mb-4">
                    <div class="card-header">Insurance Requests</div>
                    <div class="card-body">
                        <table class="table table-striped table-bordered table-sm text-center align-middle">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Request ID</th>
                                    <th>Product</th>
                                    <th>Reason</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="request" items="${requests}">
                                    <tr>
                                        <td>${request.id}</td>
                                        <td>${request.product}</td>
                                         <td>${request.product}</td>
                                        <td>${request.status}</td>
                                        <td>
                                            <button class="btn btn-success btn-sm">Approve</button>
                                            <button class="btn btn-danger btn-sm">Reject</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Activity Log -->
                <div class="card">
                    <div class="card-header">Activity Log</div>
                    <div class="card-body">
                        <ul class="list-group">
                            <c:forEach var="log" items="${activityLogs}">
                                <li class="list-group-item">${log.timestamp} - ${log.description}</li>
                            </c:forEach>
                        </ul>
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
