<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #eef2f3;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            padding: 30px;
            max-width: 1200px;
            margin: 30px auto;
        }
        h1 {
            font-size: 32px;
            color: #4a4a4a;
            text-align: center;
            margin-bottom: 40px;
            font-weight: bold;
        }
        .table {
            margin-top: 20px;
        }
        .table th {
            background-color: #f7f7f7;
            color: #333333;
            font-weight: 600;
            vertical-align: middle;
        }
        .table td {
            color: #555555;
            vertical-align: middle;
        }
        .product-image {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-secondary {
            margin-top: 30px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .btn-secondary:hover {
            background-color: #0056b3;
        }
        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }
            h1 {
                font-size: 24px;
            }
            .btn-secondary {
                width: 100%;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Chi tiết sản phẩm</h1>
        <div class="row">
            <!-- Thông tin sản phẩm -->
            <div class="col-md-6">
                <h2 class="h5 text-primary mb-3">Thông tin sản phẩm</h2>
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <td>${productList[0].productID}</td>
                        </tr>
                        <tr>
                            <th>Tên</th>
                            <td>${productList[0].productName}</td>
                        </tr>
                        <tr>
                            <th>Ngày sinh</th>
                            <td>${productList[0].insuranceType}</td>
                        </tr>
                        <tr>
                            <th>Giới tính</th>
                            <td>${productList[0].description}</td>
                        </tr>
                        <tr>
                            <th>CMND/CCCD</th>
                            <td>${productList[0].cost} VNĐ/năm</td>
                        </tr>
                        <tr>
                            <th>Số điện thoại</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                        <tr>
                            <th>Thời hạn bảo hiểm</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                        <tr>
                            <th>Sản phẩm lựa chọn</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                        <tr>
                            <th>Giá trị</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                         <tr>
                            <th>Xác nhận thương tật</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                         <tr>
                            <th>Trạng thái</th>
                            <td>${productList[0].conditions}</td>
                        </tr>
                        <tr>
                            <th>Hình ảnh sản phẩm</th>
                            <td>
                                <img src="${product.image}" alt="Hình ảnh sản phẩm" class="product-image">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <!-- Thông tin nhà cung cấp -->
            <div class="col-md-6">
                <h2 class="h5 text-success mb-3">Thông tin nhà cung cấp</h2>
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <th>ID nhà cung cấp</th>
                            <td>${productList[0].insuranceCompany.companyID}</td>
                        </tr>
                        <tr>
                            <th>Tên nhà cung cấp</th>
                            <td>${productList[0].insuranceCompany.companyName}</td>
                        </tr>
                        <tr>
                            <th>Địa chỉ</th>
                            <td>${productList[0].insuranceCompany.address}</td>
                        </tr>
                        <tr>
                            <th>Thông tin liên lạc</th>
                            <td>${productList[0].insuranceCompany.contactInfo}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Nút quay lại -->
        <a href="${pageContext.request.contextPath}CustomerForStaffController?action=showAllCardRequest" 
           class="btn btn-secondary d-block mx-auto text-center">Quay lại</a>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>