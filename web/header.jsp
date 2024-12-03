<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./assets/css/style.css"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="../image/logo-removebg-preview1.png" alt="alt"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <% 
                   if (session != null && session.getAttribute("user") != null) {  
                %>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="productManagement.jsp">Quản lý sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="account/logout">Đăng xuất</a>
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