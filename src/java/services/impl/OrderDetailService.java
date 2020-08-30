package services.impl;

import cart.CartProductObject;

import javax.inject.Inject;
import services.IOrderDetailService;
import daos.IOrderDetailDAO;
import dtos.OrderDetailDTO;
import java.util.List;

public class OrderDetailService implements IOrderDetailService {

    @Inject
    private IOrderDetailDAO orderDetailDAO;

    @Override
    public boolean save(CartProductObject cart, Long userId, Long orderId) {
        return this.orderDetailDAO.save(cart, userId, orderId);
    }

    @Override
    public List<OrderDetailDTO> findAllByOrderId(Long orderId) {
        return this.orderDetailDAO.findAllByOrderId(orderId);
    }

    

    
}
