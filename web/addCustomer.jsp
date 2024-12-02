<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Mới Khách Hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert CDN -->
    <script>
        function previewAvatar(event) {
            var reader = new FileReader();
            reader.onload = function() {
                var output = document.getElementById('avatarPreview');
                output.src = reader.result;
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="container">
        <h1 class="mb-4 text-center">Thêm Mới Khách Hàng</h1>

        <!-- Form thêm mới khách hàng -->
        <form action="manage-customer" method="post" enctype="multipart/form-data">
             <input type="hidden" name="action" class="form-control" value="add" />
            <div class="row mb-3">
                <label for="fullName" class="col-md-2 col-form-label">Họ và tên</label>
                <div class="col-md-10">
                    <input type="text" name="fullName" class="form-control" id="fullName" required />
                </div>
            </div>

            <div class="row mb-3">
                <label for="email" class="col-md-2 col-form-label">Email</label>
                <div class="col-md-10">
                    <input type="email" name="email" class="form-control" id="email" required />
                </div>
            </div>

            <div class="row mb-3">
                <label for="password" class="col-md-2 col-form-label">Mật khẩu</label>
                <div class="col-md-10">
                    <input type="password" name="password" class="form-control" id="password" required />
                </div>
            </div>

            <div class="row mb-3">
                <label for="phoneNumber" class="col-md-2 col-form-label">Điện thoại</label>
                <div class="col-md-10">
                    <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="address" class="col-md-2 col-form-label">Địa chỉ</label>
                <div class="col-md-10">
                    <textarea name="address" class="form-control" id="address" rows="3"></textarea>
                </div>
            </div>

            <div class="row mb-3">
                <label for="avatar" class="col-md-2 col-form-label">Avatar</label>
                <div class="col-md-10">
                    <!-- Preview ảnh -->
                    <img id="avatarPreview" src="./image/default-avatar.jpg" alt="Avatar Preview" class="img-fluid mb-3" width="150">
                    <input type="file" name="avatar" class="form-control" id="avatar" accept="image/*" onchange="previewAvatar(event)" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="status" class="col-md-2 col-form-label">Trạng thái</label>
                <div class="col-md-10">
                    <select name="status" class="form-control" id="status">
                        <option value="1">Kích hoạt</option>
                        <option value="0">Không kích hoạt</option>
                    </select>
                </div>
            </div>

            <div class="row mb-3 text-center">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-success">Thêm mới</button>
                    <a href="manage-customer" class="btn btn-secondary">Hủy</a>
                </div>
            </div>
        </form>

        <!-- Hiển thị lỗi bằng SweetAlert nếu có lỗi -->
        <c:if test="${not empty message}">
            <script>
                Swal.fire({
                    icon: 'error',
                    title: 'Lỗi!',
                    text: '${message}',
                    showConfirmButton: true
                });
            </script>
        </c:if>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
