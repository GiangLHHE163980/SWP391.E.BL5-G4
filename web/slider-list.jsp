<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý slider</title>
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
            <h2>Quản lý Sliders</h2>
            <a href="slider-form.jsp" class="btn btn-primary">Thêm mới Slider</a>
            <table id="sliderTable" class="table table-striped table-bordered">
                <thead>
                    <tr class="text-center">
                        <th>ID</th>
                        <th>Media</th>
                        <th>Tiêu đề</th>
                        <th>BackLink</th>
                        <th>Mô tả</th>
                        <th>Hiển thị</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="slider" items="${sliders}">
                        <tr class="align-middle text-center">
                            <td>${slider.sliderID}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:contains(slider.imageUrl, '.mp4') || fn:contains(slider.imageUrl, '.avi') || fn:contains(slider.imageUrl, '.mov')}">
                                        <video width="100" controls>
                                            <source src="${slider.imageUrl}" type="video/mp4">
                                            Trình duyệt của bạn không hỗ trợ thẻ video.
                                        </video>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${slider.imageUrl}" width="100" alt="alt"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${slider.title}</td>
                            <td>${slider.backLink}</td>
                            <td class="text-wrap">${slider.description}</td>
                            <td>${slider.publish ? "Hiển thị" : "Ẩn"}</td>
                            <td>
                                <a href="SliderController?action=edit&id=${slider.sliderID}" class="btn btn-warning btn-sm">Sửa</a>
                                <button class="btn btn-danger btn-sm" onclick="confirmDelete(${slider.sliderID})">Xóa</button>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(sliders) == 0}">
                        <tr>
                            <td colspan="7" class="text-center">Không có sliders nào.</td>
                        </tr>
                    </c:if>
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
        <script>
            $(document).ready(function () {
                $('#sliderTable').DataTable();
            });

            function confirmDelete(sliderID) {
                if (confirm('Are you sure you want to delete this slider?')) {
                    window.location.href = 'SliderController?action=delete&id=' + sliderID;
                }
            }
        </script>
    </body>
</html>
