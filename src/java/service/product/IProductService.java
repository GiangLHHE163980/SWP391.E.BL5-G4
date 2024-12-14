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
    
    List<InsuranceProduct> getProductByNameWithAvatar(String searchName);
    
    List<InsuranceProduct> getAllProducts();
    
    List<InsuranceProduct> getDistinctInsuranceTypes();
    
    List<InsuranceProduct> getProductsByCategory(String category);

    List<InsuranceProduct> getProductByNameAndCategory(String searchName, String category);
    
    List<InsuranceProduct> getAllProductsWithSort(String sortBy);
    
    List<InsuranceProduct> getProductByNameAndCategoryWithSort(String searchName, String category, String sortBy);
            
    List<InsuranceProduct> getProductByNameWithAvatarAndSort(String searchName, String sortBy);
    
    List<InsuranceProduct> getProductsByCategoryWithSort(String category, String sortBy);
    
    InsuranceProduct getProductWithAvatarById(int productId);
    
    List<InsuranceProduct> getProductsByType(String insuranceType,int excludedProductId);
    
    boolean hasInsuranceCard(int userId, int productId);

    List<InsuranceProduct> getAllProductsWithPaging(int page, int pageSize);
    
    List<InsuranceProduct> getProductsByCategoryWithPaging(String category, int page, int pageSize);
    
    List<InsuranceProduct> getProductByNameAndCategoryWithPaging(String searchName, String category, int page, int pageSize);
    
    List<InsuranceProduct> getProductByNameWithPaging(String searchName, int page, int pageSize);
    
    List<InsuranceProduct> getProductByNameAndCategoryWithSortAndPaging(String searchQuery, String category, String sortBy, int offset, int pageSize);
    
    List<InsuranceProduct> getProductByNameWithAvatarAndSortAndPaging(String searchQuery, String sortBy, int offset, int pageSize);
    
    List<InsuranceProduct> getAllProductsWithSortAndPaging(String sortBy, int offset, int pageSize);
    
    List<InsuranceProduct> getProductsByCategoryWithSortAndPaging(String category, String sortBy, int offset, int pageSize);
}
