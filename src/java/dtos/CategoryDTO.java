package dtos;

import java.io.Serializable;

public class CategoryDTO extends AbstractDTO<CategoryDTO> implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
