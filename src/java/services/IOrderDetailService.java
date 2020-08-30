package services;

import cart.CartProductObject;
import dtos.OrderDetailDTO;
import java.util.List;

public interface IOrderDetailService {
    boolean save(CartProductObject cart, Long userId , Long orderId);
    List<OrderDetailDTO> findAllByOrderId(Long orderId);
}
