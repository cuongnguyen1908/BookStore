package utils;

import cart.CartProductObject;
import dtos.ProductCartDTO;

import java.util.Map;

public class CalculateTotal {

    public static float calculate(CartProductObject cart) {
        float total = 0;

        for (Map.Entry<Long, ProductCartDTO> entry : cart.getCart().entrySet()) {
            total += (entry.getValue().getQuantity() * entry.getValue().getPrice());
        }
        return total;
    }
}
