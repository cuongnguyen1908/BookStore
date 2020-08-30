/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import daos.IRoleDAO;
import dtos.RoleDTO;
import java.util.List;
import javax.inject.Inject;
import services.IRoleService;

/**
 *
 * @author nguyen
 */
public class RoleService implements IRoleService{
    
    @Inject
    private IRoleDAO roleDAO;

    @Override
    public List<RoleDTO> findAll() {
        return roleDAO.findAll();
    }
    
}
