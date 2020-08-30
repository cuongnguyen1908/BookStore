package daos.impl;

import cart.CartProductObject;
import daos.IProductDAO;
import dtos.ProductCartDTO;
import dtos.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import mapper.ProductMapper;
import java.util.Map;

import java.util.List;
import java.util.Set;
import javax.naming.NamingException;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.Logger;
import utils.MyConnection;

public class ProductDAO extends AbstractDAO<ProductDTO> implements IProductDAO {

    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public List<ProductDTO> findAll() {
        String sql = "SELECT p.id, p.title,p.status, p.author, p.quantity, p.price, p.photo, p.[description], p.status, p.category_id, c.category_name "
                + "FROM product_tb as p "
                + "INNER JOIN category_tb as c "
                + "ON p.category_id = c.id";
        List<ProductDTO> product = query(sql, new ProductMapper());
        return product;
    }

    @Override
    public List<ProductDTO> findAllByStatusAndQuantity(boolean status) {
        String sql = "SELECT p.id, p.title,p.status, p.author, p.quantity, p.price, p.photo, p.[description], p.status, p.category_id, c.category_name "
                + "FROM product_tb as p "
                + "INNER JOIN category_tb as c "
                + "ON p.category_id = c.id "
                + "WHERE quantity > 0 AND p.status = ?";
        List<ProductDTO> product = query(sql, new ProductMapper(), status);
        return product;
    }

    @Override
    public List<ProductDTO> findAllBookByNameAndPriceAndCategory(String name, float priceMin, float priceMax, Long categoryId, boolean status) {

        String sql = "SELECT p.id, p.title, p.author, p.status, p.quantity, p.price, p.photo, p.[description], p.status, p.category_id, c.category_name "
                + "FROM product_tb as p "
                + "INNER JOIN category_tb as c "
                + "ON p.category_id = c.id "
                + "WHERE p.status = ? AND p.quantity > 0 AND LOWER(p.title) like ? ";
        boolean flagPriceMin = false;
        boolean flagPriceMax = false;
        boolean flagCategory = false;

        if (priceMin > 0) {
            flagPriceMin = true;
            sql += "AND p.price > ? ";
        }
        if (priceMax > 0) {
            flagPriceMax = true;
            sql += "AND p.price < ? ";
        }
        if (categoryId > 0) {
            flagCategory = true;
            sql += "AND p.category_id = ? ";
        }

        List<ProductDTO> product = null;

        if (flagPriceMin == true && flagPriceMax == true && flagCategory == true) {// exist all
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMin, priceMax, categoryId);
            System.out.println("worked exist all ");
        } else if (flagPriceMin == true && flagPriceMax == false && flagCategory == false) {// exist  min
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMin);
            System.out.println("worked exist exist min ");
        } else if (flagPriceMin == true && flagPriceMax == true && flagCategory == false) { //exist min, max
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMin, priceMax);
            System.out.println("sql: " + sql);
            System.out.println("worked exist  min, max ");
        } else if (flagPriceMin == true && flagCategory == true && flagPriceMax == false) { // exist min, category
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMin, categoryId);
            System.out.println("worked exist  min, category ");
        } else if (flagPriceMax) {  // exist max
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMax);
            System.out.println("worked exist max ");
        } else if (flagPriceMax == true && flagCategory == true && flagPriceMin == false) { // exist max, category
            product = query(sql, new ProductMapper(), status, "%" + name + "%", priceMax, categoryId);
            System.out.println("worked exist max, category ");
        } else if (flagCategory == true && flagPriceMin == false && flagPriceMax == false) { //exist category
            product = query(sql, new ProductMapper(), status, "%" + name + "%", categoryId);
            System.out.println("sql: " + sql);
            System.out.println("worked exist category ");
        } else if (flagPriceMin == false && flagPriceMax == false && flagCategory == false) { // none
            product = query(sql, new ProductMapper(), status, "%" + name + "%");
            System.out.println("worked seach all");
        }
        return product;
    }

    @Override
    public boolean delete(Long id, boolean status) {
        String sql = "UPDATE product_tb SET status = ? WHERE id = ?";
        return update(sql, status, id);
    }

    @Override
    public boolean update(ProductDTO dto, String importDate) {
        System.out.println("photo: " + dto.getPhoto());
        System.out.println("importdate: " + importDate);

        if (dto.getPhoto().equals("") && !importDate.equals("")) { // exist photo
            System.out.println("workd exist photo");

            String sql = "UPDATE product_tb "
                    + "SET title = ?, author = ?, quantity = ?, category_id = ? , price = ?, "
                    + "[description] = ?, photo = ?, status = ?, "
                    + "import_date = ? "
                    + "WHERE id = ?";
            return update(sql, dto.getTitle(), dto.getAuthor(), dto.getQuantity(), dto.getCategoryId(), dto.getPrice(), dto.getDescription(), dto.getPhoto(), dto.isStatus(),
                    importDate, dto.getId());

        } else if (dto.getPhoto().equals("") && importDate.equals("")) { // not exist photo, import date
            System.out.println("workd not exist photo, import date");
            String sql = "UPDATE product_tb "
                    + "SET title = ?, author = ?, quantity = ?, category_id = ? , price = ?, "
                    + "[description] = ?, status = ? "
                    + "WHERE id = ?";
            return update(sql, dto.getTitle(), dto.getAuthor(), dto.getQuantity(),
                    dto.getCategoryId(), dto.getPrice(), dto.getDescription(), dto.isStatus(), dto.getId());
        } else if (!dto.getPhoto().equals("") && importDate.equals("")) { //photo, no import
            System.out.println("workd has photo, no import");

            String sql = "UPDATE product_tb "
                    + "SET title = ?, author = ?, quantity = ?, category_id = ? , price = ?, "
                    + "[description] = ?, photo = ?, status = ? "
                    + "WHERE id = ?";
            return update(sql, dto.getTitle(), dto.getAuthor(), dto.getQuantity(),
                    dto.getCategoryId(), dto.getPrice(),
                    dto.getDescription(), dto.getPhoto(), dto.isStatus(), dto.getId());
        } else { // exist both
            System.out.println("workd eixist both");
            String sql = "UPDATE product_tb "
                    + "SET title = ?, author = ?, quantity = ?, category_id = ? , price = ?, "
                    + "photo = ?, "
                    + "[description] = ?, status = ?, "
                    + "import_date = ? "
                    + "WHERE id = ?";
            return update(sql, dto.getTitle(), dto.getAuthor(), dto.getQuantity(),
                    dto.getCategoryId(), dto.getPrice(), dto.getPhoto(), dto.getDescription(),
                    dto.isStatus(),
                    importDate, dto.getId());
        }

    }

    @Override
    public Long save(ProductDTO dto) {
        String sql = "INSERT INTO product_tb(title, author, quantity, price, photo, [description], category_id) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, dto.getTitle(), dto.getAuthor(), dto.getQuantity(), dto.getPrice(), dto.getPhoto(), dto.getDescription(), dto.getCategoryId());
    }

    @Override
    public ProductDTO findBookById(Long id) {
        String sql = "SELECT p.id, p.title, p.author, p.quantity, p.status, p.price, p.photo, p.[description], p.import_date, p.category_id, c.category_name "
                + "FROM product_tb as p "
                + "INNER JOIN category_tb as c "
                + "ON p.category_id = c.id "
                + "WHERE p.id = ?";
        List<ProductDTO> product = query(sql, new ProductMapper(), id);
        return product.isEmpty() ? null : product.get(0);
    }

    @Override
    public boolean existQuantity(CartProductObject cart) {
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.getMyConnection();
            String sql = "DECLARE @flag int; "
                    + "SELECT @flag = (p.quantity - ?) "
                    + "FROM product_tb p "
                    + "WHERE p.id = ?; "
                    + "SELECT CAST(COUNT(1) AS BIT) AS flag "
                    + "WHERE @flag > 0";
            boolean flag = true;
            for (Map.Entry<Long, ProductCartDTO> entry : cart.getCart().entrySet()) {
                preStm = connection.prepareStatement(sql);
                preStm.setInt(1, entry.getValue().getQuantity());
                preStm.setLong(2, entry.getKey());
                rs = preStm.executeQuery();
                if (rs.next()) {
                    flag = rs.getBoolean(1);
                }
                if (flag == false) {
                    return false;
                }
            }
            return flag;

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            return false;
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
                return false;

            }
        }
    }

//    @Override
//    public boolean updateQuantity(CartProductObject cart) {
//        Connection connection = null;
//        PreparedStatement preStm = null;
//        ResultSet rs = null;
//        try {
//            connection.setAutoCommit(false);
//            String sql = "DECLARE @quantity int; "
//                    + "SELECT @quantity = p.quantity "
//                    + "FROM product_tb p "
//                    + "WHERE id = ?; "
//                    + "UPDATE product_tb set quantity = (@quantity - ?) where id = ?";
//            boolean flag = false;
//            for (Map.Entry<Long, ProductCartDTO> entry : cart.getCart().entrySet()) {
//                preStm.setLong(1, entry.getKey()); //product id
//                preStm.setInt(2, entry.getValue().getQuantity()); // quantity
//                preStm.setLong(3, entry.getKey()); //product id
//                flag = preStm.executeUpdate() > 0;
//                if (flag == false) {
//                    
//                    return false;
//                }
//            }
//            connection.commit();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    @Override
    public boolean updateQuantity(CartProductObject cart) {
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.getMyConnection();
            connection.setAutoCommit(false);
            boolean flag = false;
            String sql = "DECLARE @rootQuantity int; "
                    + "DECLARE @finalQuantity int; "
                    + "SELECT @rootQuantity = p.quantity FROM product_tb p WHERE p.id = ? "
                    + "SELECT @finalQuantity = @rootQuantity - ? FROM product_tb p "
                    + "WHERE p.id = ? AND (@rootQuantity - ?) >= 0 "
                    + "UPDATE product_tb SET quantity = @finalQuantity "
                    + "WHERE id = ? AND @finalQuantity  IS NOT NULL";

//            Set<Long> key = cart.getCart().keySet();
            preStm = connection.prepareStatement(sql);

//            for (Long long1 : key) {
//                preStm.setLong(1, long1);
//                preStm.setInt(2, cart.getCart().get(long1).getQuantity()); // quantity
//                preStm.setLong(3, long1); //product id
//                preStm.setInt(4, cart.getCart().get(long1).getQuantity()); // quantity
//                preStm.setLong(5, long1); //product id
//                flag = preStm.executeUpdate() > 0;
//                if (flag == false) {
//                    return false;
//                }
//            }
            for (Map.Entry<Long, ProductCartDTO> entry :cart.getCart().entrySet()) {
                preStm.setLong(1, entry.getKey()); //product id
                preStm.setInt(2, entry.getValue().getQuantity()); // quantity
                preStm.setLong(3, entry.getKey()); //product id
                preStm.setInt(4, entry.getValue().getQuantity()); // quantity
                preStm.setLong(5, entry.getKey()); //product id
                flag = preStm.executeUpdate() > 0;
                if (flag == false) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    logger.error("ProductDAO_SQLException " + e1.getMessage());
                }
            }
        } finally {
            try {
                if (preStm != null) {
                    preStm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
                logger.error("ProductDAO_SQLException " + e2.getMessage());
            }
        }

        return false;
    }

}
