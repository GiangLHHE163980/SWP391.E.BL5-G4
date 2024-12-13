<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <title>View Insurance Cards</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <a href="CustomerDashboardController" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>
            <h1>Danh Sách Thẻ Bảo Hiểm</h1>
            <!-- Search Bar -->
            <div class="mt-4 mb-5">
                <input type="text" id="searchInput" class="form-control" placeholder="Tìm Kiếm Thẻ" onkeyup="filterTable()">
            </div>

            <!-- Table -->
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>STT.</th>
                        <th>Tên Thẻ</th>
                        <th>Trạng Thái</th>
                        <th>Ngày Kích Hoạt</th>
                        <th>Ngày Hết Hạn</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody id="insuranceTableBody">
                    <c:forEach var="card" items="${insuranceCards}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${card.insuranceProduct.productName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${card.status == 'Active'}">
                                        <span class="text-success fw-bold">Còn Hạn</span>
                                    </c:when>
                                    <c:when test="${card.status == 'Pending'}">
                                        <span class="text-warning fw-bold">Chờ Xử Lý</span>
                                    </c:when>
                                    <c:when test="${card.status == 'Pending Renew'}">
                                        <span class="text-warning fw-bold">Chờ Gia Hạn</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger fw-bold">Hết Hạn</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${card.startDate}" pattern="dd/MM/yyyy" /></td>
                            <td><fmt:formatDate value="${card.endDate}" pattern="dd/MM/yyyy" /></td>
                            <td>
                                <div class="d-flex justify-content-between">
                                    <a href="viewInsuranceInfo?cardID=${card.cardID}" class="btn btn-secondary">Xem Chi Tiết</a>                                  
                                    <a href="ClaimsRequestController?cardID=${card.cardID}" class="btn btn-primary">Yêu Cầu Bồi Thường</a>
                                    <a href="RenewCardController?cardID=${card.cardID}" class="btn btn-success">Gia Hạn</a>
                                </div>

                            </td>

                        </tr>
                    </c:forEach>
                    <c:if test="${empty insuranceCards}">
                        <tr>
                            <td colspan="6" class="text-center text-warning">No insurance cards found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <script>
            function filterTable() {
                // Get the input value and convert to uppercase for case-insensitive matching
                const searchValue = document.getElementById("searchInput").value.toUpperCase();
                const table = document.getElementById("insuranceTableBody");
                const rows = table.getElementsByTagName("tr");

                for (let i = 0; i < rows.length; i++) {
                    // Get all the cells in the current row
                    const cells = rows[i].getElementsByTagName("td");
                    let rowContainsSearchValue = false;

                    // Check each cell in the row
                    for (let j = 0; j < cells.length; j++) {
                        if (cells[j].innerText.toUpperCase().includes(searchValue)) {
                            rowContainsSearchValue = true;
                            break;
                        }
                    }

                    // Show or hide the row based on the search value
                    rows[i].style.display = rowContainsSearchValue ? "" : "none";
                }
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
