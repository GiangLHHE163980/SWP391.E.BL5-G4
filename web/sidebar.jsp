<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<div class="sidebar">
    <div class="sidebar-content">
        <div class="logo mb-4">
            <h5 class="logo-text mt-3">Admin Dashboard</h5>
        </div>
        <nav class="sidebar-nav">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="dashboard">
                        <i class="me-3 bi bi-speedometer2"></i>
                        <span>Bảng điều khiển</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="profileAdmin">
                        <i class="me-3 bi bi-person-circle"></i>
                        <span>Hồ sơ cá nhân</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="manage-customer">
                        <i class="me-3 bi bi-people"></i>
                        <span>Quản lý khách hàng</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="manage-staff">
                        <i class="me-3 bi bi-briefcase"></i>
                        <span>Quản lý nhân viên</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="SliderController">
                        <i class="me-3 bi bi-briefcase"></i>
                        <span>Slider</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link d-flex align-items-center" href="BlogManageController">
                        <i class="me-3 bi bi-briefcase"></i>
                        <span>Blog</span>
                    </a>
                </li>
                <li class="nav-item mt-auto">
                    <a class="nav-link d-flex align-items-center" href="account/logout">
                        <i class="me-3 bi bi-box-arrow-right"></i>
                        <span>Đăng xuất</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<style>
    body {
        height: 100vh;
        font-family: Arial, sans-serif;
        background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
        margin: 0;
    }

    .sidebar {
        height: 100vh;
        position: fixed;
        top: 0;
        left: 0;
        width: 250px;
        background-color: #343a40;
        color: #fff;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Đổ bóng nhẹ */
        display: flex;
        flex-direction: column;
        padding: 20px 0;
    }

    .sidebar a {
        color: #fff;
        text-decoration: none;
        padding: 15px 20px;
        display: block;
        font-size: 16px;
        transition: all 0.3s ease;
    }

    .sidebar a:hover {
        background-color: #287745; /* Màu nền khi hover */
        padding-left: 30px;
    }

    .logo {
        text-align: center;
        margin-bottom: 20px;
    }

    .logo img {
        max-width: 80%;
        height: auto;
        border-radius: 50%;
        border: 3px solid #fff; /* Viền xung quanh logo */
    }

    .container {
        margin-left: 250px;
        padding: 20px;
        background-color: #ffffff;
        min-height: 100vh;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Đổ bóng */
    }

    .navbar {
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Đổ bóng */
    }

    .navbar-brand img {
        height: 40px; /* Kích thước logo trên navbar */
        object-fit: contain;
    }

    .navbar-nav .nav-item .nav-link {
        font-size: 16px;
        padding: 10px 15px;
        transition: color 0.3s ease;
    }

    .navbar-nav .nav-item .nav-link:hover {
        color: #ffcc00; /* Màu chữ khi hover */
    }

    .row.mb-3.text-center {
        margin-bottom: 60px !important;
    }

    .row.mb-3.text-center h2 {
        font-size: 24px;
        font-weight: bold;
        color: #308652;
        margin-bottom: 15px;
    }

</style>

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">