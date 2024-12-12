<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>View Insurance Info</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <!-- Main Container -->
        <div class="container mt-4">
            <!-- Return Button -->
            <a href="insuranceCards?userID=${userID}" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>

            <!-- Main Layout -->
            <div class="row g-4">
                <!-- Insurance Info Section -->
                <div class="col-12">
                    <h1 class="display-6 fw-bold">Insurance Card Details</h1>
                    <div class="row g-3">
                        <!-- Card ID -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Card ID</h6>
                                <p class="text-muted mb-0">${cardDetails.cardID}</p>
                            </div>
                        </div>

                        <!-- Status -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Status</h6>
                                <p class="text-muted mb-0">${cardDetails.status}</p>
                            </div>
                        </div>

                        <!-- Start Date -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Start Date</h6>
                                <p class="text-muted mb-0">${cardDetails.startDate}</p>
                            </div>
                        </div>

                        <!-- End Date -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">End Date</h6>
                                <p class="text-muted mb-0">${cardDetails.endDate}</p>
                            </div>
                        </div>

                        <!-- Product Details Section -->
                        <h2 class="mt-4 fw-bold">Product Details</h2>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Product Name</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.productName}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Cost</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.cost}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Description</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.description}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Conditions</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.conditions}</p>
                            </div>
                        </div>

                        <!-- Company Details Section -->
                        <h2 class="mt-4 fw-bold">Company Details</h2>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Company Name</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.companyName}</p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Contact Info</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.contactInfo}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Address</h6>
                                <p class="text-muted mb-0">${cardDetails.insuranceProduct.insuranceCompany.address}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
