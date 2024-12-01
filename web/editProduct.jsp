<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm/Chỉnh sửa Sản phẩm</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="./css/style.css"/>
    </head>
    <body>

        <!-- Header -->
        <%@ include file="header.jsp" %>

        <div class="container">
            <h1 class="mb-4 text-center">Thêm/Chỉnh sửa Sản phẩm</h1>
            <div class="form-section">
                <form action="${pageContext.request.contextPath}/ProductController?action=editProduct" method="post">
                    <div class="mb-3">
                        <label  for="productId" class="form-label">ID sản phẩm</label>
                       <input value="${productList[0].productID}" type="text" id="productId" name="productId" class="form-control" placeholder="Nhập ID sản phẩm" required readonly>
                    </div>
                    <div class="mb-3">
                        <label for="productName" class="form-label">Tên sản phẩm</label>
                        <input value="${productList[0].productName}" type="text" id="productName" name="productName" class="form-control" placeholder="Nhập tên sản phẩm" required>
                    </div>
                    <div class="mb-3">
                        <label for="insuranceType" class="form-label">Loại bảo hiểm</label>
                        <select id="insuranceType" name="insuranceType" class="form-select" required>
                            <option value="">Chọn loại bảo hiểm</option>
                            <option value="Y tế" <c:if test="${productList[0].insuranceType == 'Y tế'}">selected</c:if>>Y tế</option>
                            <option value="Xe" <c:if test="${productList[0].insuranceType == 'Xe'}">selected</c:if>>Xe</option>
                            <option value="Nhân thọ" <c:if test="${productList[0].insuranceType == 'Nhân thọ'}">selected</c:if>>Nhân thọ</option>
                            </select>

                        </div>
                        <div class="mb-3">
                            <label for="cost" class="form-label">Chi phí</label>
                            <input value="${productList[0].cost}" type="text" id="cost" name="cost" class="form-control" placeholder="Nhập chi phí" required>
                    </div>
                    <div class="mb-3">
                        <label for="benefits" class="form-label">Quyền lợi</label>
                        <textarea id="benefits" name="benefits" class="form-control" rows="3" placeholder="Nhập quyền lợi bảo hiểm" required>${productList[0].description}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="conditions" class="form-label">Điều kiện</label>
                        <textarea id="conditions" name="conditions" class="form-control" rows="3" placeholder="Nhập điều kiện bảo hiểm" required>${productList[0].conditions}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="companyID" class="form-label">ID nhà cung cấp</label>
                        <input value="${productList[0].insuranceCompany.companyID}" type="text" id="companyID" name="companyID" class="form-control" placeholder="Nhập ID nhà cung cấp" readonly>
                    </div>

                    <div class="mb-3">
                        <label for="provider" class="form-label">Tên nhà cung cấp</label>
                        <input value="${productList[0].insuranceCompany.companyName}" type="text" id="provider" name="provider" class="form-control" placeholder="Nhập tên nhà cung cấp" required>
                    </div>
                    <!-- Thêm trường Địa chỉ -->
                    <div class="mb-3">
                        <label for="address" class="form-label">Địa chỉ nhà cung cấp</label>
                        <input value="${productList[0].insuranceCompany.address}" type="text" id="address" name="address" class="form-control" placeholder="Nhập địa chỉ" required>
                    </div>
                    <!-- Thêm trường Thông tin liên lạc -->
                    <div class="mb-3">
                        <label for="contactInfo" class="form-label">Thông tin liên lạc nhà cung cấp</label>
                        <input value="${productList[0].insuranceCompany.contactInfo}" type="text" id="contactInfo" name="contactInfo" class="form-control" placeholder="Nhập thông tin liên lạc" required>
                    </div>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-save"></i> Lưu sản phẩm
                    </button>
                    <a href="${pageContext.request.contextPath}/ProductController?action=showFullProduct" class="btn btn-secondary">
                        <i class="bi bi-arrow-left-circle"></i> Quay lại
                    </a>
                </form>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>
