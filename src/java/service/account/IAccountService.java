/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.account;

import java.util.List;
import model.User;
import service.IGeneralService;
import service.IGeneralService;

/**
 *
 * @author tuana
 */
public interface IAccountService extends IGeneralService<User> {
    public User login(String username, String password);
    
    void updatePasswordByEmail(String email, String newPassword);

    boolean isUsernameExists(String username);
    
    boolean isEmailExists(String email);
    
    List<String> getUserRoles(int userId);
}
