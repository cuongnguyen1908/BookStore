/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author nguyen
 */
public class CartObject {
    private Map<Long, String> cart;

    public Map<Long, String> getCart() {
        return cart;
    }
    
    public boolean addItemToCard(Long id, String fullName){
        
        if (cart == null) {
            cart = new HashMap<>();
        }
        if (cart.containsKey(id)){
            return true; // da ton tai trong cart
        }
        cart.put(id, fullName);
        return false; //chua ton tai trong cart
    }
    
    public boolean removeItemToCard(Long id){
        if (cart == null) {
            return true;
        }
        else {
            if (cart.containsKey(id)){
                this.cart.remove(id);
            }
            if (cart.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
