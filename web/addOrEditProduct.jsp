<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm/Chỉnh sửa Sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            padding-bottom: 50px; /* Thêm khoảng cách dưới */
        }
        .container {
            margin-top: 30px;
        }
        .form-section {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .table-section {
            margin-top: 40px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4 text-center">Thêm/Chỉnh sửa Sản phẩm</h1>
        <div class="form-section">
            <form action="#" method="post">
                <div class="mb-3">
                    <label for="productId" class="form-label">ID sản phẩm</label>
                    <input type="text" id="productId" name="productId" class="form-control" placeholder="Nhập ID sản phẩm" required>
                </div>
                <div class="mb-3">
                    <label for="productName" class="form-label">Tên sản phẩm</label>
                    <input type="text" id="productName" name="productName" class="form-control" placeholder="Nhập tên sản phẩm" required>
                </div>
                <div class="mb-3">
                    <label for="insuranceType" class="form-label">Loại bảo hiểm</label>
                    <select id="insuranceType" name="insuranceType" class="form-select" required>
                        <option value="Y tế">Y tế</option>
                        <option value="Xe">Xe</option>
                        <option value="Nhân thọ">Nhân thọ</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cost" class="form-label">Chi phí</label>
                    <input type="text" id="cost" name="cost" class="form-control" placeholder="Nhập chi phí" required>
                </div>
                <div class="mb-3">
                    <label for="benefits" class="form-label">Quyền lợi</label>
                    <textarea id="benefits" name="benefits" class="form-control" rows="3" placeholder="Nhập quyền lợi bảo hiểm" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="conditions" class="form-label">Điều kiện</label>
                    <textarea id="conditions" name="conditions" class="form-control" rows="3" placeholder="Nhập điều kiện bảo hiểm" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="provider" class="form-label">Nhà cung cấp</label>
                    <input type="text" id="provider" name="provider" class="form-control" placeholder="Nhập tên nhà cung cấp" required>
                </div>
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-save"></i> Lưu sản phẩm
                </button>
                <a href="productManagement.jsp" class="btn btn-secondary">
                    <i class="bi bi-arrow-left-circle"></i> Quay lại
                </a>
            </form>
        </div>
    </div>
</body>
</html>
