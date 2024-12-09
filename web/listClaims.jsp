<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <title>Danh Sách Bồi Thường</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <a href="home.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay Lại
            </a>
            <h1>Danh Sách Bồi Thường</h1>
            <!-- Thanh Tìm Kiếm -->
            <div class="mb-4">
                <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm theo loại, trạng thái, hoặc lý do..." onkeyup="filterTable()">
            </div>

            <!-- Bảng -->
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>STT</th>
                        <th>Mã Bồi Thường</th>
                        <th>Loại Bồi Thường</th>
                        <th>Trạng Thái</th>
                        <th>Ngày Gửi</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody id="claimsTableBody">
                    <c:forEach var="claim" items="${claims}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${claim.claimID}</td>
                            <td>${claim.claimType}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${claim.status == 'Approved'}">
                                        <span class="text-success fw-bold">Đã Duyệt</span>
                                    </c:when>
                                    <c:when test="${claim.status == 'Pending'}">
                                        <span class="text-warning fw-bold">Đang Xử Lý</span>
                                    </c:when>
                                    <c:when test="${claim.status == 'Rejected'}">
                                        <span class="text-danger fw-bold">Bị Từ Chối</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>${claim.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${claim.submittedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                                <a href="claimsInfo?claimID=${claim.claimID}" class="btn btn-success">Xem</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty claims}">
                        <tr>
                            <td colspan="10" class="text-center text-warning">Không tìm thấy bồi thường nào.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <script>
            function filterTable() {
                const searchValue = document.getElementById("searchInput").value.toUpperCase();
                const table = document.getElementById("claimsTableBody");
                const rows = table.getElementsByTagName("tr");

                for (let i = 0; i < rows.length; i++) {
                    const cells = rows[i].getElementsByTagName("td");
                    let rowContainsSearchValue = false;

                    for (let j = 0; j < cells.length; j++) {
                        if (cells[j].innerText.toUpperCase().includes(searchValue)) {
                            rowContainsSearchValue = true;
                            break;
                        }
                    }

                    rows[i].style.display = rowContainsSearchValue ? "" : "none";
                }
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
