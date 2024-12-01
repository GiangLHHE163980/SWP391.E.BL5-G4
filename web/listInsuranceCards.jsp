<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <td>${card.cardNumber}</td>
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
                    <td>${card.startDate}</td>
                    <td>${card.endDate}</td>
                    <td>
                        <a href="viewCardDetails?cardID=${card.cardID}" class="btn btn-primary">View</a>
                        <a href="renewCard?cardID=${card.cardID}" class="btn btn-success">Renew</a>
                        <a href="cancelCard?cardID=${card.cardID}" class="btn btn-danger">Cancel</a>
                    </td>
                    </tr>
                </c:forEach>
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
