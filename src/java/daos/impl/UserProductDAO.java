package daos.impl;

import daos.IUserProductDAO;
import dtos.UserProductDTO;

public class UserProductDAO extends AbstractDAO<UserProductDTO> implements IUserProductDAO {


    @Override
    public Long save(UserProductDTO dto) {
        String sql = "INSERT INTO user_product_tb (user_id, product_id, product_name, quantity, order_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        return insert(sql, dto.getUserId(), dto.getProductId(), dto.getProductName(), dto.getQuantity(), dto.getOrderId());
    }
}
