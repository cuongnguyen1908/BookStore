package daos;

import cart.CartProductObject;
import dtos.ProductDTO;

import java.util.List;

public interface IProductDAO {
    List<ProductDTO> findAll();
    List<ProductDTO> findAllByStatusAndQuantity(boolean status);
    List<ProductDTO> findAllBookByNameAndPriceAndCategory(String name, float priceMin, float priceMax, Long categoryId, boolean status);
    boolean delete(Long id, boolean status);
    boolean update(ProductDTO dto, String importDate);
    Long save(ProductDTO dto);
    ProductDTO findBookById(Long id);
    
    boolean existQuantity(CartProductObject cart);
    
    boolean updateQuantity(CartProductObject cart);
    
}
