/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.impl;

import daos.IUserDAO;
import dtos.UserDTO;
import java.util.List;
import javax.inject.Inject;
import services.IUserService;

/**
 *
 * @author nguyen
 */
public class UserService implements IUserService{
    
    @Inject
    private IUserDAO userDAO;
    

    @Override
    public UserDTO findByUserNameAndPasswordAndStatus(String username, String password, boolean status) {
        return userDAO.findByUserNameAndPasswordAndStatus(username, password, true);
    }

    @Override
    public List<UserDTO> findAll(boolean status) {
        return userDAO.findAll(status);
    }

    @Override
    public List<UserDTO> findAllByFullNameAndRoleAndStatus(String textSearch, Long roleId, boolean status) {
        return userDAO.findAllByFullNameAndRoleAndStatus(textSearch, roleId, status);
    }

    @Override
    public List<UserDTO> findAllByFullNameAndStatus(String textSearch, boolean status) {
                return userDAO.findAllByFullNameAndStatus(textSearch, status);

    }

    @Override
    public boolean delete(Long id, boolean status) {
        return userDAO.delete(id, status);
    }

    @Override
    public UserDTO findUserByIdAndStatus(Long id, boolean status) {
        return userDAO.findUserByIdAndStatus(id, status);
    }

    @Override
    public Long save(UserDTO userDTO) {
        return userDAO.save(userDTO);
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update(userDTO);
    }

    @Override
    public boolean existUserByUsername(String username) {
        return userDAO.existUserByUserName(username);
    }
    
}