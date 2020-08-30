package daos;

import cart.CartProductObject;
import dtos.OrderDetailDTO;
import java.util.List;

public interface IOrderDetailDAO {
    boolean save(CartProductObject cart, Long userId , Long orderId);
    List<OrderDetailDTO> findAllByOrderId(Long orderId);

}
