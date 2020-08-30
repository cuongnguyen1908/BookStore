package services.impl;

import daos.ICategoryDAO;
import dtos.CategoryDTO;
import services.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryDAO category;

    @Override
    public List<CategoryDTO> findAll() {

        return this.category.findAll();
    }
}
