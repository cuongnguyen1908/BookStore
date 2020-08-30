package dtos;


import java.io.Serializable;
import java.sql.Timestamp;

public class UserCodeDTO extends AbstractDTO<UserCodeDTO> implements Serializable {
    private Long userId;
    private Long code_id;
    private Timestamp createdDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCode_id() {
        return code_id;
    }

    public void setCode_id(Long code_id) {
        this.code_id = code_id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
