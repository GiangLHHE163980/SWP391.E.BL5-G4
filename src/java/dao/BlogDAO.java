/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.CategoryBlog;

/**
 *
 * @author ADMIN
 */
public class BlogDAO {

    private Connection connection;

    public BlogDAO() {
        DBContext db = new DBContext();
        this.connection = db.getConnection();
    }

    public List<Blog> getHostPost() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT TOP 6 * FROM Blogs where status='Published'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("BlogId"),
                        rs.getInt("UserId"),
                        rs.getInt("CategoryId"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("FeaturedImage"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getTimestamp("UpdatedAt")
                );
                blogs.add(blog);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return blogs;
    }

    public List<Blog> getBlogs(int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM Blogs ORDER BY UpdatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, (page - 1) * pageSize);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setTitle(rs.getString("Title"));
                blog.setContent(rs.getString("Content"));
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

    public int getTotalBlogCount() {
        String query = "SELECT COUNT(*) FROM Blogs";
        try (
                Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<CategoryBlog> getCategories() {
        List<CategoryBlog> categories = new ArrayList<>();
        String query = "SELECT * FROM CategoriesBlog";
        try (
                Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                CategoryBlog category = new CategoryBlog();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Blog> getLatestBlogs(int count) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT TOP (?) * FROM Blogs ORDER BY UpdatedAt DESC";
        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, count);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setTitle(rs.getString("Title"));
                blog.setFeaturedImage(rs.getString("FeaturedImage"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public List<Blog> searchBlogs(String keyword, int page, int pageSize) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM Blogs WHERE Title LIKE ? OR Content LIKE ? ORDER BY UpdatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            pstmt.setInt(3, (page - 1) * pageSize);
            pstmt.setInt(4, pageSize);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setUserId(rs.getInt("UserId"));
                    blog.setTitle(rs.getString("Title"));
                    blog.setContent(rs.getString("Content"));
                    blog.setStatus(rs.getString("Status"));
                    blog.setFeaturedImage(rs.getString("FeaturedImage"));
                    blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                    blogs.add(blog);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return blogs;
    }

    public int getSearchBlogCount(String keyword) {
        String query = "SELECT COUNT(*) FROM Blogs WHERE Title LIKE ? OR Content LIKE ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    public boolean updateBlog(Blog blog) {
        String query = "UPDATE Blogs SET Title = ?, Content = ?, Status = ?, FeaturedImage = ?, UpdatedAt = ? WHERE BlogId = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getStatus());
            pstmt.setString(4, blog.getFeaturedImage());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(6, blog.getBlogId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println("Error updating blog: " + e);
            return false;
        }
    }

    public boolean addBlog(Blog blog) {
        String query = "INSERT INTO Blogs (Title, Content, Status, FeaturedImage, CreatedAt, UpdatedAt) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContent());
            pstmt.setString(3, blog.getStatus());
            pstmt.setString(4, blog.getFeaturedImage());
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            pstmt.setTimestamp(5, currentTimestamp);
            pstmt.setTimestamp(6, currentTimestamp);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            System.out.println("Error adding blog: " + e);
            return false;
        }
    }

    public Blog viewBlogDetail(int blogId) {
        Blog blog = null;
        String query = "SELECT * FROM Blogs WHERE BlogId = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, blogId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setUserId(rs.getInt("UserId"));
                    blog.setTitle(rs.getString("Title"));
                    blog.setContent(rs.getString("Content"));
                    blog.setStatus(rs.getString("Status"));
                    blog.setFeaturedImage(rs.getString("FeaturedImage"));
                    blog.setCreatedAt(rs.getTimestamp("CreatedAt"));
                    blog.setUpdatedAt(rs.getTimestamp("UpdatedAt"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving blog details: " + e);
        }
        return blog;
    }

    public boolean deleteBlog(int blogId) {
        String query = "DELETE FROM Blogs WHERE BlogId = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, blogId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println("Error deleting blog: " + e);
        }
        return false;
    }

    public List<Blog> getAllPost() {
        List<Blog> blogs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Blogs order by BlogId desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("BlogId"),
                        rs.getInt("UserId"),
                        rs.getInt("CategoryId"),
                        rs.getString("Title"),
                        rs.getString("Content"),
                        rs.getString("Status"),
                        rs.getString("FeaturedImage"),
                        rs.getTimestamp("CreatedAt"),
                        rs.getTimestamp("UpdatedAt")
                );
                blogs.add(blog);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return blogs;
    }

}
