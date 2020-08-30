package daos.impl;

import daos.ICategoryDAO;
import dtos.CategoryDTO;
import mapper.CategoryMapper;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryDTO> implements ICategoryDAO {
    @Override
    public List<CategoryDTO> findAll() {
        String sql = "SELECT id, category_name FROM category_tb";
        List<CategoryDTO> category = query(sql, new CategoryMapper());
        return category;
    }
}
