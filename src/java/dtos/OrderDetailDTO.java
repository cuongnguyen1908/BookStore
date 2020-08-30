package dtos;

import java.io.Serializable;

public class OrderDetailDTO extends AbstractDTO<OrderDetailDTO> implements Serializable {
    private String title;
    private float price;
    private int quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

   
}
