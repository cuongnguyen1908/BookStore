/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import dtos.OrderDetailDTO;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyen
 */
public class OrderDetailMapper implements RowMapper<OrderDetailDTO>{

    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public OrderDetailDTO mapRow(ResultSet rs) {
        try {
            OrderDetailDTO orderDetail = new OrderDetailDTO();
            orderDetail.setTitle(rs.getString("title"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setPrice(rs.getFloat("price"));
            return orderDetail;
        } catch (Exception e) {
            logger.error("OderDetailMapper_Exception " + e.getMessage());
            return null;
        }
    }
    
    
}
