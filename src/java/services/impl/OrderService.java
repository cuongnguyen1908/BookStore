package services.impl;

import daos.IOrderDAO;
import dtos.OrderDTO;
import services.IOrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderService implements IOrderService {
    @Inject
    private IOrderDAO orderDAO;

    @Override
    public Long save(Long userId, String name, float total) {
        return this.orderDAO.save(userId, name, total);
    }

    @Override
    public List<OrderDTO> findAllByUserId(Long userId) {
        return this.orderDAO.findAllByUserId(userId);
    }

    @Override
    public boolean deleteOrderById(Long id) {
        return this.orderDAO.deleteOrderById(id);
    }

    @Override
    public List<OrderDTO> findByNameAndUserId(Long userId, String textSearch) {
        return this.orderDAO.findByNameAndUserId(userId, textSearch);
    }
}
