<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý Sản phẩm Bảo hiểm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <style>
            body {
                background-color: #f8f9fa;
                font-family: Arial, sans-serif;
            }
            .container {
                margin-top: 30px;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1 class="mb-4 text-center">Quản lý Sản phẩm Bảo hiểm</h1>

            <!-- Thanh tìm kiếm -->
            <div class="mb-3">
                <form action="ProductController" method="get">
                    <div class="input-group">
                        <!-- Tham số action cố định giá trị showFullProduct -->
                        <input type="hidden" name="action" value="showFullProduct">
                        <input type="text" class="form-control" name="search" placeholder="Tìm kiếm sản phẩm..." value="${search}">
                        <button class="btn btn-outline-primary" type="submit">
                            <i class="bi bi-search"></i> Tìm kiếm
                        </button>
                    </div>
                </form>
            </div>

            <!-- Nút thêm mới sản phẩm -->
            <div class="mb-1 mt-4 text-end">
                <a href="addOrEditProduct.jsp" class="btn btn-primary">
                    <i class="bi bi-plus"></i>
                    Thêm mới Sản phẩm</a>
            </div>

            <!-- Bảng danh sách sản phẩm -->
            <h2 class="mb-3">Danh sách Sản phẩm</h2>
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên sản phẩm</th>
                        <th>Loại bảo hiểm</th>
                        <th>Chi phí</th>
                        <th>Quyền lợi</th>
                        <th>Điều kiện</th>
                        <th>Nhà cung cấp</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Dữ liệu sản phẩm được lọc theo từ khóa tìm kiếm -->
                    <c:forEach var="product" items="${productList}">
                        <tr>
                            <td>${product.productID}</td>
                            <td>${product.productName}</td>
                            <td>${product.insuranceType}</td>
                            <td>${product.description}</td>
                            <td>${product.cost} VNĐ/năm</td>
                            <td>${product.conditions}</td>
                            <td>${product.insuranceCompany.companyName}</td>
                            <td>
                                <a href="addOrEditProduct.jsp?id=${product.insuranceCompany.companyID}" class="btn btn-warning btn-sm" title="Chỉnh sửa">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <button class="btn btn-danger btn-sm" title="Xóa">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
