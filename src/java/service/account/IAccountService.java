/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.account;

import model.Account;
import service.IGeneralService;
import service.IGeneralService;

/**
 *
 * @author tuana
 */
public interface IAccountService extends IGeneralService<Account> {

    void updateAccountStatus(boolean status);

    Account findByUsername(String username);

    Account findByEmail(String email);

    Account findByPhone(String phone);
    
    void updateNewPassword(String password, int accountID);
}
