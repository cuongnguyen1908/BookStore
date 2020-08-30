package services;

import dtos.OrderDTO;

import java.util.List;

public interface IOrderService {
    Long save(Long userId, String name, float total);
    List<OrderDTO> findAllByUserId(Long userId);
    boolean deleteOrderById(Long id);
        List<OrderDTO> findByNameAndUserId(Long userId, String textSearch);

}
