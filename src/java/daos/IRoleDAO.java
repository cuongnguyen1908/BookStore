/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RoleDTO;
import java.util.List;

/**
 *
 * @author nguyen
 */
public interface IRoleDAO {
    List<RoleDTO> findAll();
}
