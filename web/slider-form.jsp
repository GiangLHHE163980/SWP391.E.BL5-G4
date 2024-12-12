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
        <h2>${slider != null ? "Edit Slider" : "Add New Slider"}</h2>
        <c:if test="${not empty errors}">
            <div class="alert alert-danger" role="alert">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                        </c:forEach>
                </ul>
            </div>
        </c:if>

        <form action="SliderController" method="post" enctype="multipart/form-data">
            <input type="hidden" name="sliderID" value="${slider.sliderID}">

            <div class="form-group">
                <label for="imageUrl">Media Upload:</label>
                <input type="hidden" name="old-media" value="${slider.imageUrl}">
                <input type="file" class="form-control" id="imageUrl" name="imageUrl" accept="image/*,video/*" onchange="previewMedia(event)">
            </div>

            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" value="${slider.title}">
            </div>

            <div class="form-group">
                <label for="backLink">BackLink:</label>
                <input type="text" class="form-control" id="backLink" name="backLink" value="${slider.backLink}">
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" class="form-control" id="description" name="description" value="${slider.description}">
            </div>

            <div class="form-group">
                <label for="publish">Publish:</label>
                <select class="form-control" id="publish" name="publish">
                    <option value="true" ${slider.publish ? "selected" : ""}>Yes</option>
                    <option value="false" ${!slider.publish ? "selected" : ""}>No</option>
                </select>
            </div>

            <button type="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
