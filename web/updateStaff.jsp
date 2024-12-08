<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cập Nhật Thông Tin Nhân Viên</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/style.css"/>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> <!-- SweetAlert CDN -->

        <!-- JavaScript for Avatar Preview -->
        <script>
            function previewAvatar(event) {
                var reader = new FileReader();
                reader.onload = function () {
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

        <div class="container py-5">
            <h1 class="mb-4 text-center text-primary">Cập Nhật Thông Tin Nhân Viên</h1>

            <!-- Error Message -->
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

            <form action="manage-staff" method="post" enctype="multipart/form-data">
                <input type="hidden" name="userID" value="${user.userID}" />
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="oldPassword" value="${user.passwordHash}" />
                <input type="hidden" name="oldAvatar" value="${user.avatar}" />

                <!-- Full Name -->
                <div class="mb-4">
                    <label for="fullName" class="form-label">Họ và tên</label>
                    <input type="text" name="fullName" class="form-control" id="fullName" value="${user.fullName}" required />
                </div>

                <div class="mb-4">
                    <label for="fullName" class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" id="fullName" value="${user.username}" readonly />
                </div>

                <!-- Email -->
                <div class="mb-4">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" id="email" value="${user.email}" required />
                </div>

                <!-- Password -->
                <div class="mb-4">
                    <label for="password" class="form-label">Mật khẩu</label>
                    <input type="password" name="password" class="form-control" id="password" />
                    <small class="form-text text-muted">Để trống nếu không thay đổi mật khẩu.</small>
                </div>

                <!-- Phone Number -->
                <div class="mb-4">
                    <label for="phoneNumber" class="form-label">Điện thoại</label>
                    <input type="text" name="phoneNumber" class="form-control" id="phoneNumber" value="${user.phoneNumber}" />
                </div>

                <!-- Address -->
                <div class="mb-4">
                    <label for="address" class="form-label">Địa chỉ</label>
                    <textarea name="address" class="form-control" id="address" rows="3">${user.address}</textarea>
                </div>

                <!-- Avatar -->
                <div class="mb-4">
                    <label for="avatar" class="form-label">Avatar</label>
                    <div class="d-flex align-items-center">
                        <img id="avatarPreview" src="${user.avatar}" alt="Current Avatar" class="img-thumbnail mb-3" width="150" />
                        <input type="file" name="avatar" class="form-control ms-3" id="avatar" accept="image/*" onchange="previewAvatar(event)" />
                    </div>
                </div>

                <!-- Status -->
                <div class="mb-4">
                    <label for="status" class="form-label">Trạng thái</label>
                    <select name="status" class="form-select" id="status">
                        <option value="1" ${user.isActive ? 'selected' : ''}>Kích hoạt</option>
                        <option value="0" ${!user.isActive ? 'selected' : ''}>Không kích hoạt</option>
                    </select>
                </div>

                <!-- Gender (Sex) -->
                <div class="mb-4">
                    <label for="sex" class="form-label">Giới tính</label>
                    <select name="sex" class="form-select" id="sex">
                        <option value="Male" ${user.sex == 'Male' ? 'selected' : ''}>Nam</option>
                        <option value="Female" ${user.sex == 'Female' ? 'selected' : ''}>Nữ</option>
                        <option value="Other" ${user.sex == 'Other' ? 'selected' : ''}>Khác</option>
                    </select>
                </div>

                <!-- Social Security Number -->
                <div class="mb-4">
                    <label for="socialSecurityNumber" class="form-label">Số bảo hiểm xã hội</label>
                    <input type="text" name="socialSecurityNumber" class="form-control" id="socialSecurityNumber" value="${user.socialSecurityNumber}" />
                </div>

                <!-- Birthday -->
                <div class="mb-4">
                    <label for="birthday" class="form-label">Ngày sinh</label>
                    <input type="date" name="birthday" class="form-control" id="birthday" 
                           value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />" />
                </div>

                <!-- Submit Buttons -->
                <div class="text-center">
                    <button type="submit" class="btn btn-success">Cập nhật</button>
                    <a href="manage-staff" class="btn btn-secondary">Hủy</a>
                </div>
            </form>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>

        <!-- Bootstrap JS, Popper.js and jQuery -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
