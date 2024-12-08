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
    .container
    {
        margin-left: 250px;
        padding: 20px;
    }
    .row.mb-3.text-center {
        margin-bottom: 60px !important;
    }
</style>
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
//            n?u có login thì xóa dòng 77 còn chua thì d? dong 77 d? test
                <%@ include file="sidebar.jsp" %>
                
<% 
 if (session != null && session.getAttribute("user") != null) {  
 String role = (String) session.getAttribute("role");
     User user = (User) session.getAttribute("user");
     if ("admin".equalsIgnoreCase(role)) { 
%>
<%@ include file="sidebar.jsp" %>
<% 
        }
    }
%> 