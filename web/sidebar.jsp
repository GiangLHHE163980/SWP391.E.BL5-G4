<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<div class="sidebar">
    <div class="sidebar-content">
        <div class="logo mb-4">
            <img src="./assets/images/logo.png" class="logo-image">
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
    /* Sidebar styling from previous response */
    /* ... */
</style>

<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">