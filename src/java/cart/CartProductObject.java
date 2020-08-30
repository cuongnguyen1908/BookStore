/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import dtos.ProductCartDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nguyen
 */
public class CartProductObject {
    private Map<Long, ProductCartDTO> cart;
    
//    public CartProductObject() {
//        cart = new HashMap<>();
//    }

    public Map<Long, ProductCartDTO> getCart() {
        return cart;
    }

    public boolean addItemToCard(Long id, ProductCartDTO productCartDTO) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (cart.containsKey(id)) {
            ProductCartDTO product = this.cart.get(id);
            product.setQuantity(product.getQuantity() + 1);
            this.cart.put(id, product);
            return true;
        } else { // doesn't exist
            productCartDTO.setQuantity(1);
            this.cart.put(id, productCartDTO);
            return true;
        }
    }

    public boolean removeItemToCard(Long id) {
        if (cart == null) {
            return true;
        } else {
            if (cart.containsKey(id)) {
                this.cart.remove(id);
                if (this.cart.isEmpty()) {
                    this.cart = null;
                }
                return true;
            }else {
                return false;
            }
        }
    }

    public int getQuantityInCart(Long id) {
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (this.cart.containsKey(id)) {
            return this.cart.get(id).getQuantity();
        }
        return -1;
    }

    public boolean updateQuantity(Long id, int quantity) {
        if (cart == null) {
            return false;
        }
        if (this.cart.containsKey(id)) {
            ProductCartDTO product = this.cart.get(id);
            product.setQuantity(quantity);
            this.cart.put(id, product);
        }else {
            return false;
        }
        return false;
    }


}
