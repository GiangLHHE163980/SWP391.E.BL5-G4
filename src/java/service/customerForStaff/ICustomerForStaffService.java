/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.customerForStaff;

import java.util.List;
import model.User;

/**
 *
 * @author Lenovo
 */
public interface ICustomerForStaffService {
    public List<User> findAllCustomers();
    
    public Object findCustomerInforById(int id);
}
