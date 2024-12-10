<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Người Mua Bảo Hiểm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Danh Sách Người Mua Bảo Hiểm</h2>
    
    <table>
        <thead>
            <tr>
                <th>Tên</th>
                <th>Ngày sinh</th>
                <th>Giới tính</th>
                <th>CMND/CCCD</th>
                <th>Số điện thoại</th>
                <th>Thời hạn bảo hiểm</th>
                <th>Email</th>
                <th>Sản phẩm lựa chọn</th>
                <th>Giá trị</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Giả sử danh sách người mua bảo hiểm được lưu trong biến 'insuranceList'
                List<String[]> insuranceList = new ArrayList<>();
                insuranceList.add(new String[]{"Bob Johnson", "1990-01-01", "Nam", "123456789", "678951230", "1 năm", "bob.johnson@example.com", "Car Plan B", "300.000 VND/năm"});
                insuranceList.add(new String[]{"Alice Smith", "1985-05-15", "Nữ", "987654321", "678951231", "1 năm", "alice.smith@example.com", "Life Plan A", "500.000 VND/năm"});
                
                for (String[] insurance : insuranceList) {
            %>
            <tr>
                <td><%= insurance[0] %></td>
                <td><%= insurance[1] %></td>
                <td><%= insurance[2] %></td>
                <td><%= insurance[3] %></td>
                <td><%= insurance[4] %></td>
                <td><%= insurance[5] %></td>
                <td><%= insurance[6] %></td>
                <td><%= insurance[7] %></td>
                <td><%= insurance[8] %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

</body>
</html>