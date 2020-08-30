package daos.impl;

import daos.ICodeDAO;
import dtos.CodeDTO;
import dtos.FlagDTO;
import mapper.CodeMapper;
import mapper.FlagMapper;

import java.util.List;

public class CodeDAO extends AbstractDAO<CodeDTO> implements ICodeDAO {
    @Override
    public Long save(CodeDTO dto, String date) {
        if (date.equals("")) { //n o exist date
            String sql = "INSERT INTO code_tb (code, percent_number) VALUES (?, ?)";
            return insert(sql, dto.getCode(), dto.getPercent());
        }else {
            String sql = "INSERT INTO code_tb (code, percent_number, date) VALUES (?, ?, ?)";
            return insert(sql, dto.getCode(), dto.getPercent(), date);
        }

    }

    @Override
    public List<CodeDTO> findAll() {
        String sql = "SELECT id, code, percent_number, date, createdDate FROM code_tb";
        return query(sql, new CodeMapper());
    }

    @Override
    public boolean existCodeByName(String name) {
        String sql = "SELECT CAST(COUNT(1) AS BIT) AS flag FROM [code_tb] as c "
                + "WHERE c.code = ?";
        List<FlagDTO> flag = query(sql, new FlagMapper(), name);
        return flag.get(0).isFlag() == true ? true : false;
    }

    @Override
    public CodeDTO findCodeByName(String name) {
        String sql = "SELECT id, code, percent_number, date, createdDate FROM code_tb "
                + "WHERE code = ?";
        List<CodeDTO> code = query(sql, new CodeMapper(), name);
        return code.isEmpty() ? null : code.get(0);
    }
}
