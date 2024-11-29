package service.product;

import model.InsuranceProduct;
import java.util.List;

public interface IProductService {
    // Phương thức để lấy tất cả sản phẩm
    List<InsuranceProduct> findAll();
    
    List<InsuranceProduct> getProductByName(String searchName);
}
