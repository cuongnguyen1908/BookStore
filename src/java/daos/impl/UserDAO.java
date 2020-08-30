/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.IUserDAO;
import dtos.FlagDTO;
import dtos.UserDTO;
import java.util.List;
import mapper.FlagMapper;
import mapper.UserMapper;

/**
 *
 * @author nguyen
 */
public class UserDAO extends AbstractDAO<UserDTO> implements IUserDAO {

    @Override
    public UserDTO findByUserNameAndPasswordAndStatus(String username, String password, boolean status) {
        String sql = "SELECT u.id, u.username, u.full_name, u.email, u.phone, u.role_id, r.role_name as roname "
                + "FROM [user_tb] as u "
                + "INNER JOIN role_tb as r on u.role_id = r.id "
                + "WHERE username = ? AND password = ? AND status = ?";
        List<UserDTO> user = query(sql, new UserMapper(), username, password, status);
        return user.isEmpty() ? null : user.get(0);
    }

    @Override
    public List<UserDTO> findAll(boolean status) {
        String sql = "SELECT u.id, u.username, u.full_name, u.email, u.phone, role_id, r.role_name as roname "
                + "FROM [user_tb] as u "
                + "INNER JOIN role_tb as r on u.role_id = r.id "
                + "WHERE u.[status] = ?";
        List<UserDTO> user = query(sql, new UserMapper(), status);
        return user;
    }

    @Override
    public List<UserDTO> findAllByFullNameAndRoleAndStatus(String textSearch, Long roleId, boolean status) {
        String sql = "SELECT u.id, u.username, u.fullname, u.email, u.phone, u.role_id, r.role_name as roname "
                + "FROM [user_tb] as u "
                + "INNER JOIN role_tb as r on r.id = u.role_id "
                + "WHERE u.[status] = ? AND LOWER(u.full_name) like ? AND u.role_id = ?";
        List<UserDTO> user = query(sql, new UserMapper(), status, "%" + textSearch + "%", roleId);
        return user;
    }

    @Override
    public List<UserDTO> findAllByFullNameAndStatus(String textSearch, boolean status) {
        String sql = "SELECT u.id, u.username, u.full_name, u.email, u.phone, role_id, r.role_name as roname  "
                + "FROM [user_tb] as u "
                + "INNER JOIN role_tb as r on u.role_id = r.id  "
                + "WHERE u.[status] = ? AND LOWER(u.full_name) like ?";
        List<UserDTO> user = query(sql, new UserMapper(), status, "%" + textSearch + "%");
        return user;
    }

    @Override
    public boolean delete(Long id, boolean status) {
        String sql = "UPDATE [user_tb] SET status = ? WHERE id = ?";
        return update(sql, status, id);
    }

    @Override
    public UserDTO findUserByIdAndStatus(Long id, boolean status) {
        String sql = "SELECT u.id, u.username, u.full_name, u.email, u.phone, role_id, r.role_name as roname "
                + "FROM [user_tb] as u "
                + "INNER JOIN role_tb as r on u.role_id = r.id "
                + "WHERE u.[status] = ? AND u.id = ?";
        List<UserDTO> user = query(sql, new UserMapper(), status, id);
        return user.isEmpty() ? null : user.get(0);

    }

    @Override
    public Long save(UserDTO userDTO) {
        String sql = "INSERT INTO [user_tb] (username, password, fullname, role_id, status, email, phone) values (?, ?, ?, ?, ?, ?, ?)";

        return insert(sql, userDTO.getUsername(), userDTO.getPassword(), userDTO.getFullName(), userDTO.getRoleId(), userDTO.isStatus(), userDTO.getEmail(), userDTO.getPhone());
    }

    @Override
    public boolean update(UserDTO userDTO) {
        String sql = "UPDATE [user_tb] SET password = ?, fullname = ?, role_id = ?, status = ?, email = ?, phone = ?, photo = ? WHERE id = ?";
        return update(sql, userDTO.getPassword(), userDTO.getFullName(), userDTO.getRoleId(), userDTO.isStatus(), userDTO.getEmail(), userDTO.getPhone(), userDTO.getId());

    }

    @Override
    public boolean existUserByUserName(String username) {
        String sql = "SELECT CAST(COUNT(1) AS BIT) AS flag FROM [user_tb] as u "
                + "WHERE u.username = ?";
        List<FlagDTO> flag = query(sql, new FlagMapper(), username);
        return flag.get(0).isFlag() == true ? true : false;
    }


}
