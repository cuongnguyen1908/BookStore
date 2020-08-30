package services.impl;

import cart.CartProductObject;
import dtos.UserProductDTO;

import javax.inject.Inject;
import services.IOrderDetailService;
import daos.IOrderDetailDAO;

public class OrderDetailService implements IOrderDetailService {

    @Inject
    private IOrderDetailDAO userProduct;

    @Override
    public boolean save(CartProductObject cart, Long userId, Long orderId) {
        return this.userProduct.save(cart, userId, orderId);
    }

    

    
}
