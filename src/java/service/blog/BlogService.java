/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.blog;

import connection.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;

/**
 *
 * @author TH Computer
 */
public class BlogService implements IBlogService {

    private static final Connection connection = DBContext.getConnection();

    @Override
    // Lấy tất cả bài viết
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM Blogs";
        try ( PreparedStatement stmt = connection.prepareStatement(query);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setUserId(rs.getInt("UserId"));
                blog.setCategoryId(rs.getInt("CategoryId"));
                blog.setTitle(rs.getString("Title"));
                blog.setContent(rs.getString("Content"));
                blog.setStatus(rs.getString("Status"));
                blog.setFeaturedImage(rs.getString("FeaturedImage"));
                blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    // Lấy 4 bài viết cho slider
    public List<Blog> getSliderBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT TOP 4 * FROM Blogs";
        try ( PreparedStatement stmt = connection.prepareStatement(query);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setUserId(rs.getInt("UserId"));
                blog.setCategoryId(rs.getInt("CategoryId"));
                blog.setTitle(rs.getString("Title"));
                blog.setContent(rs.getString("Content"));
                blog.setStatus(rs.getString("Status"));
                blog.setFeaturedImage(rs.getString("FeaturedImage"));
                blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public Blog getBlogById(int blogId) {
        String query = "SELECT * FROM Blogs WHERE BlogId = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, blogId);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setUserId(rs.getInt("UserId"));
                    blog.setCategoryId(rs.getInt("CategoryId"));
                    blog.setTitle(rs.getString("Title"));
                    blog.setContent(rs.getString("Content"));
                    blog.setStatus(rs.getString("Status"));
                    blog.setFeaturedImage(rs.getString("FeaturedImage"));
                    blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    return blog;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Blog> getOtherBlogs(int excludeBlogId) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT TOP 4 * FROM Blogs WHERE BlogId != ? ORDER BY CreatedAt DESC";
        try ( PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, excludeBlogId);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setUserId(rs.getInt("UserId"));
                    blog.setCategoryId(rs.getInt("CategoryId"));
                    blog.setTitle(rs.getString("Title"));
                    blog.setContent(rs.getString("Content"));
                    blog.setStatus(rs.getString("Status"));
                    blog.setFeaturedImage(rs.getString("FeaturedImage"));
                    blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public void add(Blog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Blog> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Blog findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(int id, Blog t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
