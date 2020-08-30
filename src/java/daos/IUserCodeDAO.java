package daos;

import dtos.UserCodeDTO;

public interface IUserCodeDAO {
    boolean existCodeByUserIdAndCodeId(Long userId, Long codeId);
    Long save(UserCodeDTO dto);
}
