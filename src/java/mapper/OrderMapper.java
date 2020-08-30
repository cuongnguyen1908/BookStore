package mapper;

import dtos.OrderDTO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<OrderDTO> {
    static Logger logger = Logger.getLogger(Logger.class.getName());
    @Override
    public OrderDTO mapRow(ResultSet rs) {

        try {
            OrderDTO order = new OrderDTO();
            order.setId(rs.getLong("id"));
            order.setName(rs.getString("order_name"));
            order.setTotal(rs.getFloat("total"));
            order.setCreatedDate(rs.getTimestamp("createdDate"));
            return order;
        }catch (SQLException e) {
            logger.error("AbstractDAO_SQLException " + e.getMessage());
        }

        return null;
    }
}
