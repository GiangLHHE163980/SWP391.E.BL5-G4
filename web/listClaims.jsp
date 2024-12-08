<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <title>Claims List</title>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container mt-4">
            <a href="home.jsp" class="btn btn-success mb-3">
                <i class="bi bi-arrow-left"></i> Back
            </a>
            <h1>List of Claims</h1>
            <!-- Search Bar -->
            <div class="mb-4">
                <input type="text" id="searchInput" class="form-control" placeholder="Search by claim type, status, or reason..." onkeyup="filterTable()">
            </div>

            <!-- Table -->
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>No.</th>
                        <th>Claim ID</th>
                        <th>Claim Type</th>
                        <th>Status</th>
                        <th>Submitted At</th>
                        <th>Actions</th>
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
                                        <span class="text-success fw-bold">${claim.status}</span>
                                    </c:when>
                                    <c:when test="${claim.status == 'Pending'}">
                                        <span class="text-warning fw-bold">${claim.status}</span>
                                    </c:when>
                                    <c:when test="${claim.status == 'Rejected'}">
                                        <span class="text-danger fw-bold">${claim.status}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>${claim.status}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${claim.submittedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                                <a href="claimsInfo?claimID=${claim.claimID}" class="btn btn-success">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty claims}">
                        <tr>
                            <td colspan="10" class="text-center text-warning">No claims found.</td>
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
