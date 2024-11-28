<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Sản phẩm Bảo hiểm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mb-4 text-center">Quản lý Sản phẩm Bảo hiểm</h1>

        <!-- Nút thêm mới sản phẩm -->
        <div class="mb-3 text-end">
            <a href="addOrEditProduct.jsp" class="btn btn-primary">
                <i class="bi bi-plus"></i>
                Thêm mới Sản phẩm</a>
        </div>

        <!-- Bảng danh sách sản phẩm -->
        <h2 class="mb-3">Danh sách Sản phẩm</h2>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>Tên sản phẩm</th>
                    <th>Loại bảo hiểm</th>
                    <th>Chi phí</th>
                    <th>Quyền lợi</th>
                    <th>Điều kiện</th>
                    <th>Nhà cung cấp</th>
                    <th>Hành động</th>
                     
                </tr>
            </thead>
            <tbody>
                <!-- Dữ liệu sản phẩm giả lập -->
                <tr>
                    <td>1</td>
                    <td>BH001</td>
                    <td>Bảo hiểm Y tế ABC</td>
                    <td>Y tế</td>
                    <td>5,000,000 VNĐ/năm</td>
                    <td>Bảo hiểm y tế toàn diện</td>
                    <td>Áp dụng cho độ tuổi 18-60</td>
                    <td>Prudential</td>
                    <td>
                        <a href="addOrEditProduct.jsp?id=3" class="btn btn-warning btn-sm" title="Chỉnh sửa">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger btn-sm" title="Xóa">
                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                     <td>BH002</td>
                    <td>Bảo hiểm Xe XYZ</td>
                    <td>Xe</td>
                    <td>2,000,000 VNĐ/năm</td>
                    <td>Bảo hiểm xe toàn phần</td>
                    <td>Áp dụng cho xe dưới 10 năm sử dụng</td>
                    <td>Manulife</td>
                    <td>
                        <a href="addOrEditProduct.jsp?id=3" class="btn btn-warning btn-sm" title="Chỉnh sửa">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger btn-sm" title="Xóa">
                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                     <td>BH003</td>
                    <td>Bảo hiểm Nhân thọ LMN</td>
                    <td>Nhân thọ</td>
                    <td>10,000,000 VNĐ/năm</td>
                    <td>Đảm bảo tài chính gia đình</td>
                    <td>Áp dụng cho độ tuổi 25-50</td>
                    <td>Bảo Việt</td>
                   <td>
                        <a href="addOrEditProduct.jsp?id=3" class="btn btn-warning btn-sm" title="Chỉnh sửa">
                            <i class="bi bi-pencil-square"></i>
                        </a>
                        <button class="btn btn-danger btn-sm" title="Xóa">
                            <i class="bi bi-trash"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
