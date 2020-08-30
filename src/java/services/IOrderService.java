package services;

import dtos.OrderDTO;

import java.util.List;

public interface IOrderService {
    Long save(Long userId, String name, float total);
    List<OrderDTO> findAllByUserId(Long userId);
}
