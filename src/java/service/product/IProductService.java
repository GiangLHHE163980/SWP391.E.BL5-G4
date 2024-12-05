package service.product;

import model.InsuranceProduct;
import java.util.List;
import model.InsuranceCompany;

public interface IProductService {
    // Phương thức để lấy tất cả sản phẩm
    List<InsuranceProduct> findAll();
    
    List<InsuranceProduct> getProductByName(String searchName);

    public void addCompanyAndProduct(InsuranceCompany company, InsuranceProduct product);

    public Object findById(int id);

    public void editCompanyAndProduct(int id, InsuranceCompany company, InsuranceProduct product);

    public void delete(int id);
    
    List<InsuranceProduct> getTopProducts(int limit);
}
