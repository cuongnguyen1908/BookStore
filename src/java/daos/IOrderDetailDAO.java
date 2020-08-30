package daos;

import cart.CartProductObject;

public interface IOrderDetailDAO {
    boolean save(CartProductObject cart, Long userId , Long orderId);
}
