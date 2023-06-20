package MySSM.BASE;

import JDBC.DruidDBUtils.druidDBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<>();

    public static Connection getConnection(){
        Connection connection=threadLocal.get();
        if(connection==null){
            connection= druidDBUtils.getConnection();
            threadLocal.set(connection);
        }
        return threadLocal.get();
    }

    public static void closeConnection(){
        Connection connection=threadLocal.get();
        if(connection==null){
            ;
        }else{
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            threadLocal.set(null);
        }
    }
}
