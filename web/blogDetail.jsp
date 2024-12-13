<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="../css/style.css"/>
        <title>Chi Tiết Bài Viết</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                color: #333;
                background-color: #f9f9f9;
            }
            /* Blog Detail Section */
            .blog-detail {
                padding: 20px 40px;
                text-align: center; /* Căn giữa nội dung */
            }

            .blog-title {
                font-size: 28px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .divider {
                border: 0;
                height: 1px;
                background: #ddd;
                margin: 20px auto; /* Căn giữa đường line */
                width: 80%; /* Chiều rộng đường line */
            }

            .blog-content {
                font-size: 16px;
                margin-bottom: 40px;
                margin-left: 400px;
                margin-right: 400px; /* Căn giữa nội dung */
                text-align: justify; /* Căn chỉnh nội dung đều hai bên (tùy chọn) */
            }

            /* Other News Section */
            .other-news-section {
                background-color: #eaf7e9;
                padding: 20px 40px;
                border-radius: 8px;
            }

            .other-news-title {
                font-size: 20px;
                color: #337733;
                font-weight: bold;
                margin-bottom: 20px;
                text-align: center; /* Căn giữa tiêu đề */
            }

            .other-news-grid {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                gap: 20px;
            }

            .news-card {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s ease;
            }

            .news-card:hover {
                transform: scale(1.03);
            }

            .news-card img {
                width: 100%;
                height: 150px;
                object-fit: cover;
            }

            .news-card .news-body {
                padding: 15px;
                text-align: center;
            }

            .news-card .news-title {
                font-size: 16px;
                margin-bottom: 10px;
            }

            .news-card .news-title a {
                color: #333;
                text-decoration: none;
            }

            .news-card .news-title a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <%@ include file="../footer.jsp" %>
        <!-- Blog Detail Section -->
        <div class="blog-detail">
            <!-- Blog Title -->
            <h1 class="blog-title">${currentBlog.title}</h1>

            <!-- Divider -->
            <hr class="divider">

            <!-- Blog Content -->
            <div class="blog-content">
                <p>${currentBlog.content}</p>
            </div>
        </div>

        <!-- Other News Section -->
        <div class="other-news-section">
            <h2 class="other-news-title">Tin Tức Khác</h2>
            <div class="other-news-grid">
                <c:forEach items="${otherBlogs}" var="otherBlogs">
                    <div class="news-card">
                        <img src="${otherBlogs.featuredImage}" alt="${otherBlogs.title}">
                        <div class="news-body">
                            <h3 class="news-title">
                                <a href="blogdetail?blogId=${otherBlogs.blogId}">${otherBlogs.title}</a>
                            </h3>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>

</html>