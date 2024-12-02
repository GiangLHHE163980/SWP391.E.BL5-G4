<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <title>View Insurance Cards</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <a href="home.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Quay lại
            </a>
            <h1>List of Insurance Cards</h1>
            <!-- Search Bar -->
            <div class="mb-4">
                <input type="text" id="searchInput" class="form-control" placeholder="Search by name, status, or dates..." onkeyup="filterTable()">
            </div>

            <!-- Table -->
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Status</th>
                        <th>Activation Date</th>
                        <th>Expiration Date</th>
                        <th>Actions</th>
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
                                        <span class="text-success fw-bold">${card.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger fw-bold">${card.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${card.startDate}" pattern="yyyy-MM-dd" /></td>
                            <td><fmt:formatDate value="${card.endDate}" pattern="yyyy-MM-dd" /></td>
                            <td>
                                <a href="viewInsuranceInfo?cardID=${card.cardID}" class="btn btn-primary">View</a>
                                <a href="renewCard?cardID=${card.cardID}" class="btn btn-success">Renew</a>
                                <a href="cancelCard?cardID=${card.cardID}" class="btn btn-danger">Cancel</a>
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
