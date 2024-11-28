<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Khách hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/style.css"/>
   
</head>
<body>
    
    <!-- Header -->
    <%@ include file="header.jsp" %>
    <div class="container">
        <h1 class="mb-4 text-center">Quản lý Khách hàng</h1>

        <!-- Bộ lọc và Tìm kiếm -->
        <form action="#" method="get" class="row mb-4">
            <div class="col-md-4">
                <input type="text" name="name" class="form-control" placeholder="Tìm theo tên">
            </div>
            <div class="col-md-4">
                <select name="status" class="form-control">
                    <option value="">-- Lọc trạng thái thẻ --</option>
                    <option value="active">Đã gia hạn</option>
                    <option value="expired">Hết hạn</option>
                    <option value="revoked">Bị thu hồi</option>
                </select>
            </div>
            <div class="col-md-4">
                <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
            </div>
        </form>

        <!-- Bảng danh sách khách hàng -->
        <table class="table table-bordered table-hover">
            <thead class="table-dark" >
                <tr>
                    <th>#</th>
                    <th>Tên</th>
                    <th>Số thẻ bảo hiểm</th>
                    <th>Yêu cầu bảo hiểm</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <!-- Dữ liệu khách hàng giả lập -->
                <tr>
                    <td>1</td>
                    <td>Nguyễn Văn A</td>
                    <td>BH123456</td>
                    <td>Y tế</td>
                    <td>Đã gia hạn</td>
                    <td>
                        <form action="#" method="post" class="d-inline">
                            <select name="cardStatus" class="form-select d-inline w-auto">
                                <option value="active" selected>Đã gia hạn</option>
                                <option value="expired">Hết hạn</option>
                                <option value="revoked">Bị thu hồi</option>
                            </select>
                            <button type="submit" class="btn btn-success btn-sm">Cập nhật</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Lê Thị B</td>
                    <td>BH654321</td>
                    <td>Xe</td>
                    <td>Hết hạn</td>
                    <td>
                        <form action="#" method="post" class="d-inline">
                            <select name="cardStatus" class="form-select d-inline w-auto">
                                <option value="active">Đã gia hạn</option>
                                <option value="expired" selected>Hết hạn</option>
                                <option value="revoked">Bị thu hồi</option>
                            </select>
                            <button type="submit" class="btn btn-success btn-sm">Cập nhật</button>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Trần Văn C</td>
                    <td>BH789123</td>
                    <td>Nhân thọ</td>
                    <td>Bị thu hồi</td>
                    <td>
                        <form action="#" method="post" class="d-inline">
                            <select name="cardStatus" class="form-select d-inline w-auto">
                                <option value="active">Đã gia hạn</option>
                                <option value="expired">Hết hạn</option>
                                <option value="revoked" selected>Bị thu hồi</option>
                            </select>
                            <button type="submit" class="btn btn-success btn-sm">Cập nhật</button>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
