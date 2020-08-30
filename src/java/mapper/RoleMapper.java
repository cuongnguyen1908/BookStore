/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import dtos.RoleDTO;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyen
 */
public class RoleMapper implements RowMapper<RoleDTO>{

    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public RoleDTO mapRow(ResultSet rs) {
        try {
            RoleDTO role = new RoleDTO();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("role_name"));
            return role;
        } catch (Exception e) {
            logger.error("RoleMapper_Exception " + e.getMessage());
            return null;
        }
    }
    
    
}
