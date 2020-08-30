package dtos;

import java.sql.Timestamp;

public class CodeEditErrorDTO {
    private float discountPercent;
    private Timestamp date;

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
