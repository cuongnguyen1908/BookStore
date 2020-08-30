/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.impl;

import daos.GenericDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import mapper.RowMapper;
import org.apache.log4j.Logger;
import utils.MyConnection;

/**
 *
 * @author nguyen
 */
public class AbstractDAO<T> implements GenericDAO<T> {

    static Logger logger = Logger.getLogger(Logger.class.getName());

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<T>();
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            connection = MyConnection.getMyConnection();
            preStm = connection.prepareStatement(sql);
            //set param
            setParameter(preStm, parameters);

            rs = preStm.executeQuery();
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        } catch (SQLException | NamingException e) {
            logger.error("AbstractDAO_SQLException | NamingException" + e.getMessage());
            return null;
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
                logger.error("AbstractDAO_SQLException " + e.getMessage());
                return null;
            }
        }
    }

    @Override
    public boolean update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preStm = null;
        try {
            connection = MyConnection.getMyConnection();
            connection.setAutoCommit(false);
            preStm = connection.prepareStatement(sql);
            setParameter(preStm, parameters);
            if (preStm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }

        } catch (SQLException | NamingException e) {
            logger.error("AbstractDAO_SQLException " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
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
                logger.error("AbstractDAO_SQLException " + e2.getMessage());
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            Long id = null;
            connection = MyConnection.getMyConnection();
            connection.setAutoCommit(false);
            preStm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(preStm, parameters);
            preStm.executeUpdate();
            rs = preStm.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException | NamingException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error("AbstractDAO_SQLException " + e1.getMessage());
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preStm != null) {
                    preStm.close();
                }
                if (preStm != null) {
                    preStm.close();
                }
            } catch (SQLException e2) {
                logger.error("AbstractDAO_SQLException " + e2.getMessage());
            }
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            int count = 0;
            connection = MyConnection.getMyConnection();
            preStm = connection.prepareStatement(sql);
            setParameter(preStm, parameters);
            rs = preStm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException | NamingException e) {
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preStm != null) {
                    preStm.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                logger.error("AbstractDAO_SQLException " + e.getMessage());
                return 0;
            }
        }
    }

    //set param
    private void setParameter(PreparedStatement preStm, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    preStm.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    preStm.setString(index, (String) parameter);
                } else if (parameter instanceof Float) {
                    preStm.setFloat(index, (float) parameter);
                } else if (parameter instanceof Integer) {
                    preStm.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    preStm.setTimestamp(index, (Timestamp) parameter);
                } else if (parameter instanceof Boolean) {
                    preStm.setBoolean(index, (Boolean) parameter);
                }else if (parameter == null) {
                    preStm.setNull(index, java.sql.Types.INTEGER);
                }

            }
        } catch (SQLException e) {
            logger.error("AbstractDAO_SQLException " + e.getMessage());
        }

    }
}
