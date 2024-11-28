<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>View Personal Info</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <a href="home.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay lại
            </a>
            <!-- Title -->
            <h1 class="fw-bold mb-4">List Contract</h1>

            <!-- Filters -->
            <div class="d-flex justify-content-center align-items-center mb-4">
                <!-- Filter Buttons -->
                <div class="btn-group me-5" role="group" id="buttonGroup">
                    <button type="button" class="btn btn-warning text-dark ">All</button>
                    <button type="button" class="btn btn-light text-dark">Activated</button>
                    <button type="button" class="btn btn-light text-dark">Not Activated</button>
                    <button type="button" class="btn btn-light text-dark">Expired</button>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

                <script>
                    // Add event listener to each button
                    document.querySelectorAll('.btn-group .btn').forEach(function (button) {
                        button.addEventListener('click', function () {
                            // Remove active class from all buttons
                            document.querySelectorAll('.btn-group .btn').forEach(function (btn) {
                                btn.classList.remove('active');
                                btn.classList.remove('btn-warning');
                                btn.classList.add('btn-light');
                            });
                            // Add active class and change color to the clicked button
                            this.classList.add('active');
                            this.classList.remove('btn-light');
                            this.classList.add('btn-warning');
                        });
                    });
                </script>

                <!-- Search Bar -->
                <div class="d-flex">
                    <input type="text" class="form-control" placeholder="Tìm kiếm" style="max-width: 200px;">
                    <button class="btn btn-light">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </div>

            <!-- Sorting Options -->
            <div class="d-flex align-items-center">
                <h6 class="fw-bold me-5">Sort by:</h6>
                <div>
                    <a href="#" class="text-decoration-none text-dark fw-bold me-3">
                        Name <i class="bi bi-arrow-up"></i>
                    </a>
                    <a href="#" class="text-decoration-none text-dark fw-bold me-3">
                        Activation Date <i class="bi bi-arrow-down"></i>
                    </a>
                    <a href="#" class="text-decoration-none text-dark fw-bold">
                        Expiration Date <i class="bi bi-arrow-up-down"></i>
                    </a>
                </div>
            </div>

            <div class="container mt-4">
                <table class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col" class="text-center">No.</th>
                            <th scope="col" class="text-center">Name</th>
                            <th scope="col" class="text-center">Status</th>
                            <th scope="col" class="text-center">Activation Date</th>
                            <th scope="col" class="text-center">Expiration Date</th>
                            <th scope="col" class="text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="table-light">
                            <td>1</td>
                            <td>Contract 1</td>
                            <td>Active</td>
                            <td>2024-01-01</td>
                            <td>2025-01-01</td>
                            <td class="d-flex justify-content-between">
                                <a href="#" class="btn btn-link text-primary">View</a>
                                <a href="#" class="btn btn-link text-primary">Renew</a>
                                <a href="#" class="btn btn-link text-danger">Cancel</a>
                            </td>

                        </tr>
                        <tr class="table-light">
                            <td>2</td>
                            <td>Contract 2</td>
                            <td>Inactive</td>
                            <td>2023-05-15</td>
                            <td>2024-05-15</td>
                            <td class="d-flex justify-content-between">
                                <a href="#" class="btn btn-link text-primary">View</a>
                                <a href="#" class="btn btn-link text-primary">Renew</a>
                                <a href="#" class="btn btn-link text-danger">Cancel</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>
