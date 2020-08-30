package daos.impl;

import daos.IUserCodeDAO;
import dtos.FlagDTO;
import dtos.UserCodeDTO;
import mapper.FlagMapper;

import java.util.List;

public class UserCodeDAO extends AbstractDAO<FlagDTO> implements IUserCodeDAO {
    @Override
    public boolean existCodeByUserIdAndCodeId(Long userId, Long codeId) {
        String sql = "CAST(COUNT(1) AS BIT) AS flag FROM user_code_tb WHERE user_id = ? AND code_id = ?";
        List<FlagDTO> flag = query(sql, new FlagMapper(), userId, codeId);
        return flag.get(0).isFlag() == true ? true : false;
    }

    @Override
    public Long save(UserCodeDTO dto) {
        String sql = "INSERT INTO user_code_tb(user_id, code_id)";
        return insert(sql, dto.getUserId(), dto.getCode_id());
    }
}
