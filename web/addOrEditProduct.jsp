<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm/Chỉnh sửa Sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
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
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4 text-center">Thêm/Chỉnh sửa Sản phẩm</h1>
        <div class="form-section">
            <form action="#" method="post">
                <div class="mb-3">
                    <label for="productName" class="form-label">Tên sản phẩm</label>
                    <input type="text" id="productName" name="productName" class="form-control" placeholder="Nhập tên sản phẩm">
                </div>
                <div class="mb-3">
                    <label for="insuranceType" class="form-label">Loại bảo hiểm</label>
                    <select id="insuranceType" name="insuranceType" class="form-select">
                        <option value="Y tế">Y tế</option>
                        <option value="Xe">Xe</option>
                        <option value="Nhân thọ">Nhân thọ</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="cost" class="form-label">Chi phí</label>
                    <input type="text" id="cost" name="cost" class="form-control" placeholder="Nhập chi phí">
                </div>
                <div class="mb-3">
                    <label for="benefits" class="form-label">Quyền lợi</label>
                    <textarea id="benefits" name="benefits" class="form-control" rows="3" placeholder="Nhập quyền lợi bảo hiểm"></textarea>
                </div>
                <div class="mb-3">
                    <label for="conditions" class="form-label">Điều kiện</label>
                    <textarea id="conditions" name="conditions" class="form-control" rows="3" placeholder="Nhập điều kiện bảo hiểm"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Lưu sản phẩm</button>
                <a href="productManagement.jsp" class="btn btn-secondary">Quay lại</a>
            </form>
        </div>
    </div>
</body>
</html>
