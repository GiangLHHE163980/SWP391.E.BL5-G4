<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.account.AccountService" %>
<%@ page import="service.account.IAccountService" %>
<link rel="stylesheet" href="./assets/css/style.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
    background-color: #308652;
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
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="/image/logo-removebg-preview1.png" alt="alt"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <% 
                   if (session != null && session.getAttribute("user") != null) {  
                        User user = (User) session.getAttribute("user");
                %>
                <li class="nav-item">
                    <a class="nav-link" href="account/logout">Đăng xuất</a>
                </li>
                <li class="nav-item">
                    <span class="nav-link">Hi, <%= user.getUsername() %></span>
                </li>
                <% 
                    } else { 
                %>
                <li class="nav-item">
                    <a class="nav-link" href="account/login">Đăng nhập</a>
                </li>
                <% 
                    } 
                %>
            </ul>
        </div>
    </div>
</nav>     
<%
    if (session != null && session.getAttribute("user") != null) {  
        User user = (User) session.getAttribute("user");
        IAccountService accountService = new AccountService();
        List<String> roles = accountService.getUserRoles(user.getUserID());
        if (roles != null && roles.contains("Admin")) { 
%>
            <%@ include file="sidebar.jsp" %>
<% 
        }
    }
%>