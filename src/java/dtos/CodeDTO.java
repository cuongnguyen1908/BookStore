package dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class CodeDTO extends AbstractDTO<CodeDTO> implements Serializable {
    private String code;
    private float percent;
    private Timestamp date;
    private Timestamp createdDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
