<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>View Claim Info</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <!-- Main Container -->
        <div class="container mt-4">
            <!-- Return Button -->
            <a href="home.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Back
            </a>

            <!-- Main Layout -->
            <div class="row g-4">
                <!-- Claim Info Section -->
                <div class="col-12">
                    <h1 class="display-6 fw-bold">Claim Details</h1>
                    <div class="row g-3">
                        <!-- ClaimID -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Claim ID</h6>
                                <p class="text-muted mb-0">${claimID}</p>
                            </div>
                        </div>

                        <!-- User Full Name -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Full Name</h6>
                                <p class="text-muted mb-0">${userFullName}</p>
                            </div>
                        </div>

                        <!-- User Phone Number -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Phone Number</h6>
                                <p class="text-muted mb-0">${userPhoneNumber}</p>
                            </div>
                        </div>

                        <!-- Status -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Status</h6>
                                <p class="mb-0 fw-bold">
                                    <c:choose>
                                        <c:when test="${status == 'Approved'}">
                                            <span class="text-success">${status}</span>
                                        </c:when>
                                        <c:when test="${status == 'Pending'}">
                                            <span class="text-warning">${status}</span>
                                        </c:when>
                                        <c:when test="${status == 'Rejected'}">
                                            <span class="text-danger">${status}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span>${status}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>


                        <!-- Reason -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Reason</h6>
                                <p class="text-muted mb-0">${reason}</p>
                            </div>
                        </div>

                        <h2 class="mt-4 fw-bold">Plan Details</h2>
                        <!-- Plan Name -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Plan Name</h6>
                                <p class="text-muted mb-0">${productName}</p>
                            </div>
                        </div>

                        <!-- Plan Status -->
                        <div class="col-md-6">
                            <div class="card p-3">
                                <h6 class="fw-bold">Plan Status</h6>
                                <c:choose>
                                    <c:when test="${cardStatus == 'Active'}">
                                        <span class="text-success fw-bold">${cardStatus}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger fw-bold">${cardStatus}</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>



                        <!-- Plan Conditions -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Conditions</h6>
                                <p class="text-muted mb-0">${productConditions}</p>
                            </div>
                        </div>

                        <!-- Plan Description -->
                        <div class="col-md-12">
                            <div class="card p-3">
                                <h6 class="fw-bold">Description</h6>
                                <p class="text-muted mb-0">${productDescription}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
