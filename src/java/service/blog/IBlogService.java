/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.blog;

import java.util.List;
import model.Blog;
import service.IGeneralService;

/**
 *
 * @author TH Computer
 */
public interface IBlogService extends IGeneralService<Blog>{
    
    List<Blog> getAllBlogs();
    
    List<Blog> getSliderBlogs();
            
    Blog getBlogById(int blogId);
    
    List<Blog> getOtherBlogs(int excludeBlogId);
}
