package dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OrderDTO extends AbstractDTO<OrderDTO> implements Serializable {
    private String name;
    private float total;
    private Timestamp createdDate;
    private List<OrderDetailDTO> orderDetail;

    public List<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }

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
