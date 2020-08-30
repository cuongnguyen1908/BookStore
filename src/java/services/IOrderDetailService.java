package services;

import cart.CartProductObject;

public interface IOrderDetailService {
    boolean save(CartProductObject cart, Long userId , Long orderId);
}
