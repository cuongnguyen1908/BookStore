package mapper;

import dtos.CategoryDTO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryDTO> {
    static Logger logger = Logger.getLogger(Logger.class.getName());
    @Override
    public CategoryDTO mapRow(ResultSet rs) {
        try {
            CategoryDTO category = new CategoryDTO();
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("category_name"));
            return category;
        }catch (SQLException e ) {
            e.printStackTrace();
            logger.error("AbstractDAO_SQLException " + e.getMessage());
        }
        return null;
    }
}
