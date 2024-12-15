<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="model.User" %> <%@ page
import="java.util.List" %> <%@ page import="service.account.AccountService" %>
<%@ page import="service.account.IAccountService" %>
<link rel="stylesheet" href="./assets/css/style.css" />
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
  rel="stylesheet"
/>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/homepage"
      ><img src="${pageContext.request.contextPath}/image/logo-removebg-preview1.png" alt="alt" style="width: 80px; height: auto;" />
</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarNav"
      aria-controls="navbarNav"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <% if (session != null && session.getAttribute("user") != null) { User
        user = (User) session.getAttribute("user"); %>
         <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/blog">Bản tin</a>
        </li>
         <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/ProductController?action=showAllProduct">Sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/CustomerDashboardController">Tổng quan cá nhân</a>
        </li>
        <li class="nav-item">
          <span class="nav-link">Hi, <%= user.getUsername() %></span>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/account/logout">Đăng xuất</a>
        </li>
        <% } else { %>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/account/login">Đăng nhập</a>
        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
<% if (session != null && session.getAttribute("user") != null) { User user =
(User) session.getAttribute("user"); IAccountService accountService = new
AccountService(); List<String>
  roles = accountService.getUserRoles(user.getUserID()); if (roles != null &&
  roles.contains("Admin")) { %> <%@ include file="sidebar.jsp" %> <% } }
  %></String
>
