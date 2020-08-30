package daos;

import dtos.CategoryDTO;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryDTO> findAll();
}
