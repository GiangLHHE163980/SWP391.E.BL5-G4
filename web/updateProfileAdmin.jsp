<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${action == 'view'  ? "" : "Cập Nhật "}Thông Tin</title>
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
            <h1 class="mb-4 text-center text-primary">${action == 'view'  ? "" : "Cập Nhật "}Thông Tin</h1>

            <!-- Error Message -->
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

            <c:choose>
                <c:when test="${action == 'view'}">
                    <!-- View mode (Read-only) -->
                    <div class="card mb-4 shadow-lg">
                        <div class="card-header bg-primary text-white">
                            <h5 class="mb-0">Thông Tin</h5>
                        </div>
                        <div class="card-body">
                            <img src="${user.avatar}" alt="Avatar" class="img-thumbnail mb-2" width="150" />
                            <table class="table table-bordered">
                                <tbody>
                                    <tr>
                                        <th class="col-4">Họ và tên</th>
                                        <td>${user.fullName}</td>
                                    </tr>
                                    <tr>
                                        <th>Username</th>
                                        <td>${user.username}</td>
                                    </tr>
                                    <tr>
                                        <th>Email</th>
                                        <td>${user.email}</td>
                                    </tr>
                                    <tr>
                                        <th>Điện thoại</th>
                                        <td>${user.phoneNumber}</td>
                                    </tr>
                                    <tr>
                                        <th>Địa chỉ</th>
                                        <td>${user.address}</td>
                                    </tr>
                                    <tr>
                                        <th>Trạng thái</th>
                                        <td>${user.isActive ? 'Kích hoạt' : 'Không kích hoạt'}</td>
                                    </tr>
                                    <tr>
                                        <th>Giới tính</th>
                                        <td>${user.sex}</td>
                                    </tr>
                                    <tr>
                                        <th>Ngày sinh</th>
                                        <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
                                    </tr>
                                </tbody>
                            </table>

                            <!-- Centered Edit Button -->
                            <div class="text-center mt-3">
                                <a href="profileAdmin?action=edit&id=${user.userID}" class="btn btn-primary">Chỉnh sửa thông tin</a>
                            </div>
                        </div>
                    </div>
                </c:when>


                <c:otherwise>
                    <!-- Edit mode (Editable) -->
                    <form action="profileAdmin" method="post" enctype="multipart/form-data">
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
                            <input type="text" name="username" class="form-control" id="username" value="${user.username}" readonly />
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
                        <input type="hidden" name="status" value="${user.isActive == true ? '1' : '0'}" />
                        <!-- Gender (Sex) -->
                        <div class="mb-4">
                            <label for="sex" class="form-label">Giới tính</label>
                            <select name="sex" class="form-select" id="sex">
                                <option value="Male" ${user.sex == 'Male' ? 'selected' : ''}>Male</option>
                                <option value="Female" ${user.sex == 'Female' ? 'selected' : ''}>Female</option>
                                <option value="Other" ${user.sex == 'Other' ? 'selected' : ''}>Other</option>
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
                            <input type="date" name="birthday" class="form-control" id="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />" />
                        </div>

                        <!-- Submit Buttons -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-success">Cập nhật</button>
                            <a href="profileAdmin?id=${user.userID}" class="btn btn-secondary">Hủy</a>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>

        <!-- Bootstrap JS, Popper.js, and jQuery -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
    </body>
</html>
