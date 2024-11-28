/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.account;

import model.User;
import service.IGeneralService;
import service.IGeneralService;

/**
 *
 * @author tuana
 */
public interface IAccountService extends IGeneralService<User> {

    void updateAccountStatus(boolean status);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhone(String phone);
    
    void updateNewPassword(String password, int accountID);
}
