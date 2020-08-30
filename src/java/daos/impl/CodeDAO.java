package daos.impl;

import daos.ICodeDAO;
import dtos.CodeDTO;

public class CodeDAO extends AbstractDAO<CodeDTO> implements ICodeDAO {
    @Override
    public Long save(CodeDTO dto) {
        String sql = "INSERT INTO code_tb (code, percent_number, date) VALUES (?, ?, ?, ?)";
        return insert(sql, dto.getCode(), dto.getPercent(), dto.getDate());
    }
}
