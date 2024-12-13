<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gia Hạn Bảo Hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="container mt-4">
            <a href="insuranceCards?userID=${userID}" class="btn btn-success mb-4 text-decoration-none">
                <i class="bi bi-arrow-left"></i> Trở Về
            </a>

            <div class="card p-4">
                <form action="RenewCardController" method="post">
                    <div class="mb-4">
                        <h2 class="fw-bold mb-4">Gia Hạn Gói Bảo Hiểm</h2>
                        <!-- Tên Gói -->
                        <div class="row">
                            <!-- Tên Gói -->
                            <div class="col-md-6 mb-3">
                                <div class="card p-3">
                                    <h6 class="fw-bold">Tên Gói</h6>
                                    <p class="text-muted mb-0">${productName}</p>
                                </div>
                            </div>

                            <input type="hidden" name="userID" value="${userID}">
                            <input type="hidden" name="cardID" value="${cardID}">

                            <!-- Trạng Thái Gói -->
                            <div class="col-md-6 mb-3">
                                <div class="card p-3">
                                    <h6 class="fw-bold">Trạng Thái Gói</h6>
                                    <c:choose>
                                        <c:when test="${cardStatus == 'Active'}">
                                            <span class="text-success fw-bold">Còn Hạn</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger fw-bold">Hết Hạn</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>


                        <!-- Điều Kiện Gói -->
                        <div class="col-md-12 mb-3">
                            <div class="card p-3">
                                <h6 class="fw-bold">Điều Kiện</h6>
                                <p class="text-muted mb-0">${productConditions}</p>
                            </div>
                        </div>

                        <div class="row g-3 mt-4">
                            <div class="col-md-6">
                                <label for="startDate" class="fw-bold form-label">Chọn Ngày Bắt Đầu Mới</label>
                                <input type="date" class="form-control" id="startDate" name="startDate" value="${startDate}" onchange="updateEndDate()" required>
                            </div>
                            <div class="col-md-6">
                                <label for="endDate" class="fw-bold form-label">Ngày Hết Hiệu Lực Mới</label>
                                <input type="date" class="form-control" id="endDate" name="endDate" value="${endDate}" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center mt-4">
                        <!-- Submit Button -->
                        <button type="submit" class="btn btn-success me-2">Gửi yêu cầu</button>

                        <!-- Cancel Button -->
                        <a href="insuranceCards?userID=${userID}" class="btn btn-secondary">Hủy</a>
                    </div>
                </form>
            </div>


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
