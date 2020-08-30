package mapper;

import dtos.CodeDTO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeMapper implements RowMapper<CodeDTO> {
    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public CodeDTO mapRow(ResultSet rs) {
        try {
            CodeDTO code = new CodeDTO();
            code.setId(rs.getLong("id"));
            code.setCode(rs.getString("code"));
            code.setPercent(rs.getFloat("percent_number"));
            code.setDate(rs.getTimestamp("date"));
            code.setCreatedDate(rs.getTimestamp("createdDate"));
            return code;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("CodeMapper_SQLException " + e.getMessage());
        }
        return null;
    }
}
