/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.homepageForStaff;

import connection.DBContext;
import java.sql.Connection;

/**
 *
 * @author Lenovo
 */
public interface IHomepageForStaffService {
     public int getCountOfCustomer();
     
     public int getCountOfInsuranceCard();
     
     public int getCountOfRequest() ;
}
