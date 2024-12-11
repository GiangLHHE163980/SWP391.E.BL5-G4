<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Tin Yêu Cầu Bảo Hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <!-- Header -->
            <a href="   " class="btn btn-success mb-3 text-decoration-none">
                <i class="bi bi-arrow-left"></i> Trở Về
            </a>

            <h1 class="h4 fw-bold mt-3">THÔNG TIN YÊU CẦU BẢO HIỂM</h1>

            <!-- Form Section -->
            <form action="requestInsuranceController" method="post">
                <div class="row">
                    <!-- Left Form -->
                    <div class="col-lg-8">
                        <div class="card p-4">
                            <h5 class="fw-bold mb-4">Bên mua bảo hiểm</h5>              
                            <!-- Form Fields -->
                            <div class="row g-3">
                                <!-- Full Name -->
                                <div class="col-md-6">
                                    <label for="fullName" class="form-label">Họ tên <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="fullName" name="userName" placeholder="Nhập họ tên" value="${userName}" required>
                                </div>
                                <!-- Gender -->
                                <div class="col-md-6">
                                    <label for="gender" class="form-label">Giới tính <span class="text-danger">*</span></label>
                                    <select class="form-select" id="gender" name="gender" required>
                                        <option value="" disabled selected>Chọn giới tính</option>
                                        <option value="male" ${gender == 'male' ? 'selected' : ''}>Nam</option>
                                        <option value="female" ${gender == 'female' ? 'selected' : ''}>Nữ</option>
                                    </select>
                                </div>
                                <!-- Date of Birth -->
                                <div class="col-md-6">
                                    <label for="dob" class="form-label">Ngày sinh <span class="text-danger">*</span></label>
                                    <input type="date" class="form-control" id="dob" name="dob" value="${dob}" required>
                                </div>
                                <!-- ID Card -->
                                <div class="col-md-6">
                                    <label for="idCard" class="form-label">CMND/CCCD/Hộ chiếu <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="idCard" name="idCard" placeholder="Nhập số CMND/CCCD/Hộ chiếu" value="${idCard}" required>
                                </div>
                                <!-- Phone Number -->
                                <div class="col-md-6">
                                    <label for="phone" class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Nhập số điện thoại" value="${phone}" required>
                                </div>
                                <!-- Email -->
                                <div class="col-md-6">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" value="${email}">
                                </div>
                            </div>

                            <h5 class="fw-bold mt-4">Thời hạn bảo hiểm</h5>
                            <div class="row g-3">
                                <div class="col-md-6">
                                    <label for="startDate" class="form-label">Ngày bắt đầu hiệu lực</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate" value="${startDate}" onchange="updateEndDate()" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="endDate" class="form-label">Ngày hết hiệu lực</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate" value="${endDate}" readonly>
                                </div>
                            </div>
                            <div class="card p-4 mt-4">
                                
                                <p>Người tham gia bảo hiểm có thuộc một trong các trường hợp dưới đây không?</p>
                                <ol>
                                    <li>Người có tiền sử hoặc đang bị bệnh tâm thần, thần kinh, bệnh phong.</li>
                                    <li>Người bị tàn phế hoặc thương tật vĩnh viễn từ 50% trở lên.</li>
                                </ol>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="isHandicapped" id="optionYes" value="yes" required>
                                    <label class="form-check-label" for="optionYes">Có</label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="isHandicapped" id="optionNo" value="no" required>
                                    <label class="form-check-label" for="optionNo">Không</label>
                                </div>
                                
                            </div>
                        </div>
                    </div>

                    <!-- Right Summary -->
                    <div class="col-lg-4">
                        <div class="card p-3">
                            <h6 class="fw-bold" style="font-size: 1.25rem;">Sản phẩm lựa chọn</h6>
                            <div class="card border-0">
                                <div class="card-body">
                                    <!-- Display Product Details Dynamically -->
                                    <p class="card-text fw-bold" style="font-size: 1.3rem;">
                                        <span style="font-size: 1.1rem;">Tên Sản Phẩm: </span>${productName}
                                    </p>


                                    <!-- Description -->
                                    <p class="fw-bold" style="font-size: 1.1rem;">Mô Tả:</p>
                                    <p class="text-muted" style="font-size: 1.2rem;">${description}</p>

                                    <!-- Cost -->


                                    <!-- Conditions -->
                                    <p class="fw-bold" style="font-size: 1.1rem;">Điều Kiện:</p>
                                    <p class="text-muted" style="font-size: 1.2rem;">${conditions}</p>

                                    <p class="fw-bold" style="font-size: 1.3rem;">
                                        <span style="font-size: 1.1rem;">Giá Tiền: </span>${cost}00 VND/năm
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div class="mt-3 p-3 bg-light rounded">
                            <p class="mb-1">1 × Người</p>
                            <p class="fw-bold mb-1">Tổng phí bảo hiểm: <span>${cost}00 VND</span></p>
                            <small class="text-muted">(Không tính VAT)</small>
                            <button type="submit" class="btn btn-primary w-100 mt-3 fw-bold">TIẾP TỤC</button>
                        </div>
                    </div>
                </div>
                <!-- Hidden Inputs for UserID and ProductID -->
                <input type="hidden" name="userID" value="${param.userID}">
                <input type="hidden" name="productID" value="${param.productID}">
            </form>
        </div>
        <script>
            // Automatically set the end date to one year after the selected start date
            function updateEndDate() {
                const startDateField = document.getElementById('startDate');
                const endDateField = document.getElementById('endDate');

                const startDate = new Date(startDateField.value);
                if (!isNaN(startDate)) {
                    // Add 1 year to the start date
                    const endDate = new Date(startDate);
                    endDate.setFullYear(endDate.getFullYear() + 1);

                    // Format the end date to YYYY-MM-DD
                    const formattedEndDate = endDate.toISOString().split('T')[0];
                    endDateField.value = formattedEndDate;
                }
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
