/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service.userrole;

public interface IUserRoleService {
    // Phương thức để gán vai trò cho người dùng
    boolean assignRoleToUser(int userID, int roleID);
    
    // Phương thức để xóa vai trò của người dùng
    boolean removeRoleFromUser(int userID, int roleID);
}
