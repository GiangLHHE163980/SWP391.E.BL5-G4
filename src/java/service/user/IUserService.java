/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<< HEAD
package service.user;

import model.User;
import service.IGeneralService;

/**
 *
 * @author Admin
 */
public interface IUserService extends IGeneralService<User>{
    User getUserById(int userID);
=======

package service.user;

import model.User;
import java.util.List;

public interface IUserService {
    // Phương thức để lấy tất cả người dùng
    List<User> findAll();
    
    // Phương thức để tìm người dùng theo tên
    User getUserById(int userID);
    
    // Phương thức để thêm một người dùng mới
    boolean addUser(User user);
    
    // Phương thức để cập nhật thông tin người dùng
    boolean updateUser(User user);
    
    // Phương thức để xóa người dùng
    boolean deleteUser(int userID);
>>>>>>> HuyPQHE171509
}
