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
        <style>
            .preview-img {
                max-width: 200px;
                margin-top: 10px;
                display: none;
            }
        </style>
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
            <h1>Chỉnh sửa Blog</h1>
            <form action="BlogManageController" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${blog.blogId}">
                <input type="hidden" name="action" value="update">

                <div class="form-group">
                    <label for="category">Thể loại</label>
                    <select class="form-control" id="category" name="categoryId" required>
                        <c:forEach var="category" items="${categoryBlogs}">
                            <option value="${category.categoryId}" 
                                    ${blog.categoryId == category.categoryId ? 'selected' : ''}>
                                ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="title">Tiêu đề</label>
                    <input type="text" class="form-control" id="title" name="title" value="${blog.title}" required>
                </div>

                <div class="form-group">
                    <label for="content">Nội dung</label>
                    <textarea class="form-control tinymce" id="content" name="content" rows="10"></textarea>
                </div>

                <div class="form-group">
                    <label for="status">Trạng thái</label>
                    <select class="form-control" id="status" name="status">
                        <option value="Draft" ${blog.status == 'Draft' ? 'selected' : ''}>Draft</option>
                        <option value="Published" ${blog.status == 'Published' ? 'selected' : ''}>Published</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="featuredImage">Blog Media</label>
                    <div>
                        <img id="currentImage" src="${blog.featuredImage}" alt="Current Image" style="max-width: 200px; display: block;">
                    </div>
                    <input type="file" class="form-control" id="featuredImage" name="featuredImage" onchange="previewImage(event)">
                    <input type="hidden" class="form-control" id="oldImage" name="oldImage" value="${blog.featuredImage}">
                    <div>
                        <img id="preview" class="preview-img" alt="Image Preview">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary mt-2">Cập nhật Blog</button>
            </form>
            <a href="BlogManageController" class="btn btn-secondary mt-2">Hủy bỏ</a>
        </div>
         <%@ include file="footer.jsp" %>
        <script>
            function previewImage(event) {
                const preview = document.getElementById('preview');
                preview.style.display = 'block';
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        preview.src = e.target.result;
                    }
                    reader.readAsDataURL(file);
                }
            }

            tinymce.init({
                selector: '.tinymce',
                plugins: 'advlist autolink lists link image charmap print preview hr anchor pagebreak media',
                toolbar_mode: 'floating'
            });
        </script>
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
