/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "BlogController", urlPatterns = {"/blog"})
public class BlogController  extends HttpServlet{
    private IBlogService blogService = new BlogService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tất cả bài viết
        List<Blog> allBlogs = blogService.getAllBlogs();
        // Lấy 4 bài viết cho slider
        List<Blog> sliderBlogs = blogService.getSliderBlogs();

        // Gửi dữ liệu tới JSP
        request.setAttribute("allBlogs", allBlogs);
        request.setAttribute("sliderBlogs", sliderBlogs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listBlogForGuest.jsp");
        dispatcher.forward(request, response);
    }
}
