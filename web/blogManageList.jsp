

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
            <h2>Quản lý Blog</h2>
            <a href="BlogManageController?action=add" class="btn btn-primary">Thêm mới Blog</a>
            <table id="blogTable" class="table table-striped table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>Tiêu đề</th>
                        <th>Nội dung</th>
                        <th>Trạng thái</th>
                        <th>Ngày tạo</th>
                        <th>Ngày cập nhật</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="blog" items="${blogs}">
                        <tr>
                            <td>${blog.title}</td>
                            <td>${blog.content}</td>
                            <td>${blog.status}</td>
                            <td>${blog.createdAt}</td>
                            <td>${blog.updatedAt}</td>
                            <td>
                                <a href="BlogManageController?action=viewDetail&id=${blog.blogId}" class="btn btn-info btn-sm">Xem</a>
                                <a href="BlogManageController?action=edit&id=${blog.blogId}" class="btn btn-warning btn-sm">Sửa</a>
                                <a onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" href="BlogManageController?action=delete&id=${blog.blogId}" class="btn btn-danger btn-sm">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
        <%@ include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <!-- Include Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!-- Include DataTables JS -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
        <!-- Initialize DataTable -->
        <script>
                                    $(document).ready(function () {
                                        $('#blogTable').DataTable({
                                            "lengthMenu": [5, 10, 25, 50],
                                            "pageLength": 10
                                        });
                                    });
        </script>
    </body>
</html>
