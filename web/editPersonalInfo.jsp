<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>Edit Personal Info</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <!-- Main Container -->
        <div class="container mt-4">
            <!-- Return Button -->
            <a href="viewPersonalInfo.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Cancel
            </a>

            <!-- Main Layout -->
            <form action="updatePersonalInfo" method="post" enctype="multipart/form-data">
                <div class="row g-4">
                    <!-- Avatar Section (1/3 of the page) -->
                    <div class="col-md-4 text-center mt-5">
                        <img src="${user.avatar}" alt="User Avatar" class="rounded-circle img-fluid mb-3" style="max-width: 200px;">
                        <div class="form-group mt-3">
                            <label for="avatar" class="fw-bold">Change Avatar</label>
                            <input type="file" id="avatar" name="avatar" class="form-control" accept="image/*">
                            <small class="text-muted">Upload a new profile picture (optional).</small>
                        </div>
                    </div>

                    <!-- User Info Section (2/3 of the page) -->
                    <div class="col-md-8">
                        <div class="row g-3">
                            <h1 class="display-6 fw-bold">Edit Your Information</h1>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="fullName" class="fw-bold">Full Name</label>
                                    <input type="text" id="fullName" name="fullName" class="form-control" value="${user.fullName}" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="birthday" class="fw-bold">Birthday</label>
                                    <input type="date" id="birthday" name="birthday" class="form-control" value="${user.birthday}" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="phoneNumber" class="fw-bold">Phone Number</label>
                                    <input type="tel" id="phoneNumber" name="phoneNumber" class="form-control" value="${user.phoneNumber}" required>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="sex" class="fw-bold">Sex</label>
                                    <select id="sex" name="sex" class="form-control" required>
                                        <option value="Male" ${user.sex == 'Male' ? 'selected' : ''}>Male</option>
                                        <option value="Female" ${user.sex == 'Female' ? 'selected' : ''}>Female</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="address" class="fw-bold">Address</label>
                                    <textarea id="address" name="address" class="form-control" rows="3" required>${user.address}</textarea>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="fw-bold">Email</label>
                                    <p class="form-control-plaintext">${user.email}</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="fw-bold">Social Security Number</label>
                                    <p class="form-control-plaintext">${user.socialSecurityNumber}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Buttons -->
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-success me-3">
                        <i></i> Submit
                    </button>
                    <a href="viewPersonalInfo.jsp" class="btn btn-secondary">
                        <i></i> Cancel
                    </a>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
