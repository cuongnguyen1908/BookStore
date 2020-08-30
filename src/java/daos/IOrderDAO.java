package daos;

import dtos.OrderDTO;

import java.util.List;

public interface IOrderDAO {
    Long save(Long userId, String name, float total);
    List<OrderDTO> findAllByUserId(Long userId);
}
