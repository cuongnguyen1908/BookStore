/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.IRoleDAO;
import dtos.RoleDTO;
import java.util.List;
import mapper.RoleMapper;

/**
 *
 * @author nguyen
 */
public class RoleDAO extends AbstractDAO<RoleDTO> implements IRoleDAO{

    @Override
    public List<RoleDTO> findAll() {
        String sql = "SELECT id, role_name FROM role_tb";
        return query(sql, new RoleMapper());
    }
}
