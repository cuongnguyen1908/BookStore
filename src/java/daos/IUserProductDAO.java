package daos;

import dtos.UserProductDTO;

public interface IUserProductDAO {
    Long save(UserProductDTO dto);
}
