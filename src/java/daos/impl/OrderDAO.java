package daos.impl;

import daos.IOrderDAO;
import dtos.OrderDTO;
import mapper.OrderMapper;

import java.util.List;

public class OrderDAO extends AbstractDAO<OrderDTO> implements IOrderDAO {
    @Override
    public Long save(Long userId, String name, float total) {
        String sql = "INSERT INTO order_tb (user_id, order_name, total) VALUES(?, ?, ?)";
        return insert(sql, userId, name, total);
    }

    @Override
    public List<OrderDTO> findAllByUserId(Long userId) {
        String sql = "SELECT id, order_name, total, createdDate FROM order_tb WHERE user_id = ?";
        return query(sql, new OrderMapper(), userId);
    }

    @Override
    public boolean deleteOrderById(Long id) {
        String sql = "DELETE FROM order_tb WHERE id = ?";
        return update(sql, id);
    }

    @Override
    public List<OrderDTO> findByNameAndUserId(Long userId, String textSearch) {
        String sql = "SELECT id, order_name, total, createdDate "
                + "FROM order_tb "
                + "WHERE user_id = ? AND LOWER(order_name) like ?";
        List<OrderDTO> order = query(sql, new OrderMapper(), userId, "%" + textSearch + "%");
        return order;
    }
    
}
