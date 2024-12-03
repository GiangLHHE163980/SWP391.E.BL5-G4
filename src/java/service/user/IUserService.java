/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.user;

import model.User;
import service.IGeneralService;

/**
 *
 * @author Admin
 */
public interface IUserService extends IGeneralService<User>{
    User getUserById(int userID);
}
