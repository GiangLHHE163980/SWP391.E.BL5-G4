<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý Khách hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/style.css"/>

    </head>
    <body>

        <!-- Header -->
        <%@ include file="header.jsp" %>
        <div class="container">
            <h1 class="mb-4 text-center">Quản lý Khách hàng</h1>

            <!-- Bộ lọc và Tìm kiếm -->
            <form action="#" method="get" class="row mb-4">
                <div class="col-md-4">
                    <input type="text" name="name" class="form-control" placeholder="Tìm theo tên">
                </div>
                <div class="col-md-4">
                    <select name="status" class="form-control">
                        <option value="">-- Lọc trạng thái thẻ --</option>
                        <option value="active">Đã gia hạn</option>
                        <option value="expired">Hết hạn</option>
                        <option value="revoked">Bị thu hồi</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
                </div>
            </form>
            <a href="manage-customer?action=add" class="btn btn-success mb-3">Thêm mới</a>
            <!-- Bảng danh sách khách hàng -->
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Họ và tên</th>
                        <th>Email</th>
                        <th>Điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Avatar</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.userID}</td>
                            <td>${user.fullName}</td>
                            <td>${user.email}</td>
                            <td>${user.phoneNumber}</td>
                            <td>${user.address}</td>
                            <td><img src="${user.avatar}" alt="Avatar" width="50"></td>
                            <td>${user.isActive ? 'Kích hoạt' : 'Không kích hoạt'}</td>
                            <td>
                                <a href="manage-customer?action=edit&id=${user.userID}" class="btn btn-warning btn-sm">Chỉnh sửa</a>
                                <a href="manage-customer?action=delete&id=${user.userID}" class="btn btn-danger btn-sm">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
