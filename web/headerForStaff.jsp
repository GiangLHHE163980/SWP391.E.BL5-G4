<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<link rel="stylesheet" href="./assets/css/style.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        height: 100vh;
    }
    .sidebar {
        height: 100vh;
        position: fixed;
        top: 0px;
        left: 0;
        width: 250px;
        background-color: #308652;
        color: #fff;
    }
    .sidebar a {
        color: #fff;
        text-decoration: none;
    }
    .sidebar a:hover {
        background-color: #308342;
        padding-left: 10px;
        transition: all 0.3s;
    }
    .logo {
        text-align: center;
        padding: 20px;
    }
    .logo img {
        max-width: 100%;
        height: auto;
        border-radius: 50%;
    }
    .container {
        margin-left: 250px;
        padding: 20px;
    }
    .row.mb-3.text-center {
        margin-bottom: 60px !important;
    }
</style>

<!-- Header with Profile Dropdown -->
<div class="header bg-white d-flex justify-content-between align-items-center p-3 border-bottom">
    <h4 class="mb-0">Bảng điều khiển</h4>
    <div class="dropdown profile-dropdown">
        <% 
            if (session != null && session.getAttribute("user") != null) {  
                User user = (User) session.getAttribute("user");
        %>
        <a href="#" class="d-flex align-items-center text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://via.placeholder.com/40" alt="Profile Picture">
            <span class="ms-2">Hi, <%= user.getUsername() %></span>
        </a>
        <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href="#">My Profile</a></li>
            <li><a class="dropdown-item" href="#">Account Settings</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="account/logout">Logout</a></li>
        </ul>
        <% 
            } else { 
        %>
        <a href="account/login" class="btn btn-primary">Đăng nhập</a>
        <% 
            } 
        %>
    </div>
</div>
