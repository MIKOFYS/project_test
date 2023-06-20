package JDBC.DruidDBUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class druidDBUtils {
    private static DataSource dataSource;
    static{
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("D:\\IDEA_Code_Store_Position\\JavaWeb\\src\\Properties\\druid_property"));
            dataSource=DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement,Connection connection) {
        try{
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
