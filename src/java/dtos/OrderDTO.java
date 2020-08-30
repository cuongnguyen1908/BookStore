package dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderDTO extends AbstractDTO<OrderDTO> implements Serializable {
    private String name;
    private float total;
    private Timestamp createdDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
