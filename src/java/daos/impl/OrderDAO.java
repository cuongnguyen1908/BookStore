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
        String sql = "SELECT order_name, total, createdDate FROM order_tb WHERE id = ?";
        return query(sql, new OrderMapper(), userId);
    }
}
