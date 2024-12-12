
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý bài viết</title>
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
            <h1>Blog Details</h1>

            <!-- Hiển thị chi tiết blog nếu tồn tại -->
            <c:if test="${not empty blog}">
                <div class="card">
                    <div class="card-body">
                        <img src="${blog.featuredImage}" width="100" alt="alt"/>
                        <p><strong>Tiêu đề:</strong>${blog.title}</p>
                        <p><strong>Nội dung:</strong>${blog.content}</p>
                        <p><strong>Trạng thái:</strong> ${blog.status}</p>
                        <p><strong>Ngày cập nhật:</strong> ${blog.updatedAt}</p>
                        <p><strong>Ngày tạo:</strong> ${blog.createdAt}</p>
                        <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-primary">Chỉnh sửa Blog</a>
                    </div>
                </div>
            </c:if>

            <!-- Hiển thị thông báo nếu có -->
            <c:if test="${not empty message}">
                <div class="alert alert-info mt-3">
                    ${message}
                </div>
            </c:if>

            <!-- Nút quay về trang chủ -->
            <a href="BlogManageController" class="btn btn-secondary mt-3">Quay lại</a>
        </div>

        <%@ include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>

