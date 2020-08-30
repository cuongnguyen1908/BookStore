/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import dtos.FlagDTO;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyen
 */
public class FlagMapper implements RowMapper<FlagDTO> {

    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public FlagDTO mapRow(ResultSet rs) {
        FlagDTO flag = new FlagDTO();

        try {
            flag.setFlag(rs.getBoolean("flag"));
        } catch (Exception e) {
            logger.error("RoleMapper_Exception " + e.getMessage());
        }
        return flag;
    }

}
