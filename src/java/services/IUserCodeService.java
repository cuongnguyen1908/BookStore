package services;

import dtos.UserCodeDTO;

public interface IUserCodeService {
    boolean existCodeByUserIdAndCodeId(Long userId, Long codeId);
    Long save(UserCodeDTO dto);
}
