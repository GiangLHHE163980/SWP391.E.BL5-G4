/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.homepageForStaff;

import connection.DBContext;
import java.sql.Connection;

/**
 *
 * @author Lenovo
 */
public class HomepageForStaffService implements IHomepageForStaffService{
    private final Connection connection = DBContext.getConnection();
    
     private static final String GET_NUMBER_OF_CUSTOMER = "";
    
}
