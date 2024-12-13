/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Blog;
import service.blog.BlogService;
import service.blog.IBlogService;

/**
 *
 * @author TH Computer
 */
@WebServlet(name = "BlogDetailController", urlPatterns = {"/blogdetail"})
public class BlogDetailController extends HttpServlet{
    private final IBlogService blogService = new BlogService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy blogId từ tham số URL
        int blogId = Integer.parseInt(request.getParameter("blogId"));

        // Lấy bài viết hiện tại
        Blog currentBlog = blogService.getBlogById(blogId);

        // Lấy 4 bài viết khác
        List<Blog> otherBlogs = blogService.getOtherBlogs(blogId);

        // Set dữ liệu lên request
        request.setAttribute("currentBlog", currentBlog);
        request.setAttribute("otherBlogs", otherBlogs);

        // Forward đến trang JSP
        request.getRequestDispatcher("/blogDetail.jsp").forward(request, response);
    }
}
