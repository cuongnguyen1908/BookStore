package mapper;

import dtos.CategoryDTO;
import dtos.ProductDTO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<ProductDTO>{
    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public ProductDTO mapRow(ResultSet rs) {
        try {
            ProductDTO product = new ProductDTO();
            try {
                product.setId(rs.getLong("id"));
            }catch (Exception e ) {

            }
            product.setTitle(rs.getString("title"));
            product.setAuthor(rs.getString("author"));
            product.setQuantity(rs.getInt("quantity"));
            product.setPrice(rs.getFloat("price"));
            product.setPhoto(rs.getString("photo"));
            try {
                product.setStatus(rs.getBoolean("status"));
            }catch (Exception e ) {

            }
            try {
                CategoryDTO category = new CategoryDTO();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("category_name"));
                product.setCategory(category);
            }catch (Exception e ) {
                logger.warn("AbstractDAO_SQLException " + e.getMessage());
            }
            product.setDescription(rs.getString("description"));
            product.setAuthor(rs.getString("author"));
            return product;
        } catch (SQLException e) {
            logger.warn("AbstractDAO_SQLException " + e.getMessage());
        }
        return null;
    }
}
