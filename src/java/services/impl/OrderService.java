package services.impl;

import daos.IOrderDAO;
import dtos.OrderDTO;
import services.IOrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IOrderDAO order;

    @Override
    public Long save(Long userId, String name, float total) {
        return this.order.save(userId, name, total);
    }

    @Override
    public List<OrderDTO> findAllByUserId(Long userId) {
        return this.order.findAllByUserId(userId);
    }
}
