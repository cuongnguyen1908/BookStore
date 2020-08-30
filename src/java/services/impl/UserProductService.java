package services.impl;

import daos.IUserProductDAO;
import dtos.UserProductDTO;
import services.IUserProductService;

import javax.inject.Inject;

public class UserProductService implements IUserProductService {

    @Inject
    private IUserProductDAO userProduct;

    @Override
    public Long save(UserProductDTO dto) {
        return this.userProduct.save(dto);
    }
}
