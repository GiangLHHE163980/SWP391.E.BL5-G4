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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert CDN -->
    </head>
    <body>
        <c:if test="${param.isAction != null && param.isAction == false}">
            <script>
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: 'Hành động thực hiện thất bại',
                    showConfirmButton: true
                });
            </script>
        </c:if>
        <c:if test="${param.isAction != null && param.isAction == true}">
            <script>
                Swal.fire({
                    icon: 'success',
                    title: 'Thành công!',
                    text: 'Hành động thực hiện thành công',
                    showConfirmButton: true
                });
            </script>
        </c:if>
        <!-- Header -->
        <%@ include file="header.jsp" %>
      
        <div class="container content">
            <h1 class="mb-4 text-center">Quản lý Khách hàng</h1>

            <!-- Bộ lọc và Tìm kiếm -->
            <form action="manage-customer" method="get" class="row mb-4">
                <div class="col-md-3">
                    <input type="text" name="name" class="form-control" placeholder="Tìm theo tên" value="${param.name}">
                </div>
                <div class="col-md-3">
                    <select name="status" class="form-control">
                        <option value="">-- Lọc trạng thái --</option>
                        <option value="active" ${param.status == 'active' ? 'selected' : ''}>Hoạt động</option>
                        <option value="inactive" ${param.status == 'inactive' ? 'selected' : ''}>Không hoạt động</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
                </div>
            </form>

            <a href="manage-customer?action=add" class="btn btn-success mb-3">Thêm mới</a>

            <!-- Bảng danh sách khách hàng -->
            <c:if test="${empty users}">
                <div class="alert alert-warning" role="alert">
                    Không có khách hàng nào.
                </div>
            </c:if>
            <c:if test="${users.size() != 0}">
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr class="text-center">
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
            </c:if>
            <!-- Phân trang -->
            <div class="d-flex justify-content-center">
                <c:if test="${totalPages > 1}">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${totalPages}" step="1">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="manage-customer?page=${i}&name=${param.name}&status=${param.status}&role=${param.role}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
