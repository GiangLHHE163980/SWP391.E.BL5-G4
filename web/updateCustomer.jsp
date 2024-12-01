<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Khách Hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/style.css"/>

    <!-- Thêm mã JavaScript cho preview ảnh -->
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
    <!-- Header -->
    <%@ include file="header.jsp" %>
    
    <div class="container">
        <h1 class="mb-4 text-center">Cập Nhật Khách Hàng</h1>
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

        <form action="manage-customer" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userID" value="${user.userID}" />
            <input type="hidden" name="action" class="form-control" value="update" />
            <input type="hidden" name="oldPassword" class="form-control" value="${user.passwordHash}" />
            <input type="hidden" name="oldAvatar" class="form-control" value="${user.avatar}" />
            
            <div class="row mb-3">
                <label for="fullName" class="col-md-2 col-form-label">Họ và tên</label>
                <div class="col-md-10">
                    <input type="text" name="fullName" class="form-control" id="fullName" value="${user.fullName}" required />
                </div>
            </div>
            
            <div class="row mb-3">
                <label for="email" class="col-md-2 col-form-label">Email</label>
                <div class="col-md-10">
                    <input type="email" name="email" class="form-control" id="email" value="${user.email}" required />
                </div>
            </div>

            <!-- Thêm trường Mật khẩu -->
            <div class="row mb-3">
                <label for="password" class="col-md-2 col-form-label">Mật khẩu</label>
                <div class="col-md-10">
                    <input type="password" name="password" class="form-control" id="password" />
                    <small class="form-text text-muted">Để trống nếu không thay đổi mật khẩu.</small>
                </div>
            </div>

            <div class="row mb-3">
                <label for="phoneNumber" class="col-md-2 col-form-label">Điện thoại</label>
                <div class="col-md-10">
                    <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" value="${user.phoneNumber}" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="address" class="col-md-2 col-form-label">Địa chỉ</label>
                <div class="col-md-10">
                    <textarea name="address" class="form-control" id="address" rows="3">${user.address}</textarea>
                </div>
            </div>

            <div class="row mb-3">
                <label for="avatar" class="col-md-2 col-form-label">Avatar</label>
                <div class="col-md-10">
                    <!-- Preview ảnh -->
                    <img id="avatarPreview" src="${user.avatar}" alt="Current Avatar" class="img-fluid mb-3" width="150" />
                    <input type="file" name="avatar" class="form-control" id="avatar" accept="image/*" onchange="previewAvatar(event)" />
                </div>
            </div>

            <div class="row mb-3">
                <label for="status" class="col-md-2 col-form-label">Trạng thái</label>
                <div class="col-md-10">
                    <select name="status" class="form-control" id="status">
                        <option value="1" ${user.isActive ? 'selected' : ''}>Kích hoạt</option>
                        <option value="0" ${!user.isActive ? 'selected' : ''}>Không kích hoạt</option>
                    </select>
                </div>
            </div>

            <div class="row mb-3 text-center">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-success">Cập nhật</button>
                    <a href="manage-customer" class="btn btn-secondary">Hủy</a>
                </div>
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
