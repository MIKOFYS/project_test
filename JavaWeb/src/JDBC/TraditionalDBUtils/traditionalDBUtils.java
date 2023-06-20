package JDBC.TraditionalDBUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class traditionalDBUtils {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;


    static {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src\\Properties\\mysql_connect_property"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        url=properties.getProperty("url");
        user=properties.getProperty("user");
        password=properties.getProperty("password");
        driver=properties.getProperty("driver");
    }

    public static Connection getConnection(){
        Connection connection;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
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
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnectionMethod01(){
        Connection connection;
        Driver driver;

        try {
            driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Properties info=new Properties();
        info.setProperty("user",user);
        info.setProperty("password",password);

        try{
            connection = driver.connect(url, info);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static Connection getConnectionMethod02(){
        Connection connection;
        Driver driv;
        Class<?> aClass;
        try {
            aClass = Class.forName(driver);
            driv = (Driver) aClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            DriverManager.registerDriver(driv);//这句话不加也行，因为IDEA会自动注册driver
            connection=DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
