package services;

import dtos.UserProductDTO;

public interface IUserProductService {
    Long save(UserProductDTO dto);
}
