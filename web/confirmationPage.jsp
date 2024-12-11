<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xác Nhận Yêu Cầu Bảo Hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <!-- Header -->
            <a href=" " class="btn btn-success mb-3 text-decoration-none">
                <i class="bi bi-arrow-left"></i> Trở Về
            </a>

            <h1 class="h4 fw-bold mt-3">XÁC NHẬN YÊU CẦU BẢO HIỂM</h1>

            <!-- Summary Section -->
            <div class="row">
                <!-- Left Summary -->
                <div class="col-lg-8">
                    <div class="card p-4">
                        <h5 class="fw-bold mb-4">Thông Tin Người Yêu Cầu</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>Họ tên:</strong> ${userName}</li>
                            <li class="list-group-item"><strong>Giới tính:</strong> ${gender == 'male' ? 'Nam' : 'Nữ'}</li>
                            <li class="list-group-item"><strong>Ngày sinh:</strong> 
                                ${fn:substring(dob, 8, 10)}/${fn:substring(dob, 5, 7)}/${fn:substring(dob, 0, 4)}
                            </li>
                            <li class="list-group-item"><strong>CMND/CCCD/Hộ chiếu:</strong> ${idCard}</li>
                            <li class="list-group-item"><strong>Số điện thoại:</strong> ${phone}</li>
                            <li class="list-group-item"><strong>Email:</strong> ${email}</li>
                        </ul>

                        <h5 class="fw-bold mt-4">Thời Hạn Bảo Hiểm</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>Ngày bắt đầu hiệu lực:</strong> 
                                ${fn:substring(startDate, 8, 10)}/${fn:substring(startDate, 5, 7)}/${fn:substring(startDate, 0, 4)}
                            </li>
                            <li class="list-group-item"><strong>Ngày hết hiệu lực:</strong> 
                                ${fn:substring(endDate, 8, 10)}/${fn:substring(endDate, 5, 7)}/${fn:substring(endDate, 0, 4)}
                            </li>
                        </ul>

                        <h5 class="fw-bold mt-4">Tình Trạng Người Tham Gia</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>Người tham gia có thuộc trường hợp đặc biệt:</strong> ${isHandicapped ? 'Có' : 'Không'}</li>
                        </ul>
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
                        <form action="ConfirmInsuranceController" method="post" class="mt-3">
                            <input type="hidden" name="userID" value="${userID}">
                            <input type="hidden" name="productID" value="${productID}">
                            <button type="submit" class="btn btn-primary w-100 fw-bold">XÁC NHẬN</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
