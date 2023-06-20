package MySSM.TransactionManager;

import MySSM.BASE.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public static void beginTransaction() throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        connection.setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        Connection connection = ConnectionUtils.getConnection();
        connection.commit();
        ConnectionUtils.closeConnection();
    }

    public static void rollback() {
        Connection connection = ConnectionUtils.getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ConnectionUtils.closeConnection();
    }
}
