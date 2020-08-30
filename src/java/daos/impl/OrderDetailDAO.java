package daos.impl;

import cart.CartProductObject;
import daos.IOrderDetailDAO;
import dtos.ProductCartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.MyConnection;
import java.util.Map;

public class OrderDetailDAO implements IOrderDetailDAO {

    @Override
    public boolean save(CartProductObject cart, Long userId, Long orderId) {
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            connection = MyConnection.getMyConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO order_detail_tb (user_id, product_id, quantity, order_id) "
                    + "VALUES (?, ?, ?, ?)";
            preStm = connection.prepareStatement(sql);
            for (Map.Entry<Long, ProductCartDTO> entry : cart.getCart().entrySet()) {
                preStm.setLong(1, userId);
                preStm.setLong(2, entry.getKey());
                preStm.setInt(3, entry.getValue().getQuantity());
                preStm.setLong(4, orderId);
                if (preStm.executeUpdate() == 0) {
                    return false;
                }
                
            }
            connection.commit();
            return true;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preStm != null) {
                    preStm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

//    @Override
//    public Long save(UserProductDTO dto) {
//        String sql = "INSERT INTO user_product_tb (user_id, product_id, product_name, quantity, order_id) " +
//                "VALUES (?, ?, ?, ?, ?)";
//        return insert(sql, dto.getUserId(), dto.getProductId(), dto.getProductName(), dto.getQuantity(), dto.getOrderId());
//    }
}
