<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trang Tin Tức</title>
        <!-- Thêm Swiper CSS -->
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"> <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="../css/style.css"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                color: #333;
                background-color: #f9f9f9;
            }

            /* Slider */
            .swiper-container {
                position: relative;
                width: 100%;
                max-width: 100%;
                overflow: hidden;
                margin-bottom: 40px;
            }

            .swiper-slide img {
                width: 100%;
                height: 400px;
                object-fit: cover;
            }

            .swiper-slide .caption {
                position: absolute;
                bottom: 20px;
                left: 20px;
                background-color: rgba(0, 0, 0, 0.5);
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 18px;
            }

            /* News Section */
            .news-section {
                padding: 20px 40px;
            }

            .news-title {
                font-size: 24px;
                margin-bottom: 20px;
            }

            .news-grid {
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

            /* Thêm các nút điều khiển */
            .swiper-button-next, .swiper-button-prev {
                color: #fff;
                background-color: rgba(0, 0, 0, 0.5);
                border-radius: 50%;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <%@ include file="../footer.jsp" %>
        <!-- Slider -->
        <div class="swiper-container slider">
            <div class="swiper-wrapper">
                <!-- Các Slide được load từ controller -->
                <c:forEach var="blog" items="${sliderBlogs}">
                    <div class="swiper-slide">
                        <a href="blogdetail?blogId=${blog.blogId}">
                            <img src="<c:out value='${blog.featuredImage}' />" alt="Slider Image">
                        </a>
                        <div class="caption"><c:out value="${blog.title}" /></div>
                    </div>
                </c:forEach>
            </div>
            <!-- Nút điều khiển -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>

        <!-- News Section -->
        <div class="news-section">
            <h2 class="news-title">Tin Tức</h2>
            <div class="news-grid">
                <!-- News Cards -->
                <c:forEach var="blog" items="${allBlogs}">
                    <div class="news-card">
                        <img src="<c:out value='${blog.featuredImage}' />" alt="<c:out value='${blog.title}' />">
                        <div class="news-body">
                            <h3 class="news-title">
                                <a href="<c:out value='blogdetail?blogId=${blog.blogId}' />"><c:out value="${blog.title}" /></a>
                            </h3>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Thêm Swiper JS -->
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <script>
            // Khởi tạo Swiper
            var swiper = new Swiper('.swiper-container', {
                slidesPerView: 1, // Hiển thị 1 slide tại một thời điểm
                spaceBetween: 10, // Khoảng cách giữa các slide
                loop: true, // Lặp lại slider khi đến cuối
                autoplay: {// Tự động lướt qua lại
                    delay: 3000, // Tự động chuyển slide sau 3 giây
                },
                navigation: {// Thêm nút điều khiển
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            });
        </script>
    </body>
</html>
