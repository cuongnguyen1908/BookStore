package services.impl;

import daos.IUserCodeDAO;
import dtos.UserCodeDTO;
import javax.inject.Inject;
import services.IUserCodeService;

public class UserCodeService implements IUserCodeService {
    @Inject
    private IUserCodeDAO userCode;
    
    @Override
    public boolean existCodeByUserIdAndCodeId(Long userId, Long codeId) {
        return this.userCode.existCodeByUserIdAndCodeId(userId, codeId);
    }

    @Override
    public Long save(UserCodeDTO dto) {
        return this.userCode.save(dto);
    }
}
