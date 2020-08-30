package services.impl;

import daos.IProductDAO;
import dtos.ProductDTO;
import services.IProductService;

import javax.inject.Inject;
import java.util.List;

public class ProductService implements IProductService {
    @Inject
    private IProductDAO product;

    @Override
    public List<ProductDTO> findAllByStatusAndQuantity(boolean status) {
        return this.product.findAllByStatusAndQuantity(status);
    }

    @Override
    public List<ProductDTO> findAllBookByNameAndPriceAndCategory(String name, float priceMin, float priceMax, Long categoryId, boolean status) {

        return this.product.findAllBookByNameAndPriceAndCategory(name, priceMin, priceMax, categoryId, status);
    }

    @Override
    public boolean delete(Long id, boolean status) {
        return this.product.delete(id, status);
    }

    @Override
    public boolean update(ProductDTO dto, String importDate) {
        return this.product.update(dto, importDate);
    }

    @Override
    public Long save(ProductDTO dto) {
        return this.product.save(dto);
    }

    @Override
    public ProductDTO findBookById(Long id) {
        return this.product.findBookById(id);
    }

    @Override
    public List<ProductDTO> findAll() {
        return this.product.findAll();
    }
}
