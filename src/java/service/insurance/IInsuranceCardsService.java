/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service.insurance;

import java.util.List;
import model.InsuranceCard;
import service.IGeneralService;

/**
 *
 * @author Admin
 */
public interface IInsuranceCardsService extends IGeneralService<InsuranceCard>{
    List<InsuranceCard> getInsuranceCardsByUser(int userId);
}
