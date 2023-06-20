package MySSM.BASE;

import JDBC.BaseDAO.BaseDAO_Interface;
import JDBC.DruidDBUtils.druidDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NewBaseDAO<T> implements BaseDAO_Interface<T> {
    private Class<T> T_Class;
    private QueryRunner queryRunner;

    public NewBaseDAO(){
        ParameterizedType parameterizedType=(ParameterizedType) this.getClass().getGenericSuperclass();
        this.T_Class=(Class<T>) parameterizedType.getActualTypeArguments()[0];
        this.queryRunner=new QueryRunner();
    }


    public int execute(String sql,Object... parameters){
        Connection connection=null;
        connection= ConnectionUtils.getConnection();
        int affectedRow=0;

        try {
            affectedRow=queryRunner.execute(connection,sql,parameters);
            return affectedRow;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidDBUtils.close(null,null,null);
        }
    }

    //dml
    public int update(String sql,Object... parameters){
        Connection connection=null;
        connection= ConnectionUtils.getConnection();;
        int affectedRow=0;

        try {
            affectedRow=queryRunner.update(connection,sql,parameters);
            return affectedRow;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            //close转到事务管理的过滤去做了
            druidDBUtils.close(null,null,null);
        }
    }

    //query
    public List<T> queryMultiRow(String sql,Object... parameters){
        Connection connection=null;
        List<T> list=null;
        connection= ConnectionUtils.getConnection();;

        try {
            list=queryRunner.query(connection,sql,new BeanListHandler<T>(this.T_Class),parameters);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidDBUtils.close(null,null,null);
        }
    }

    public T querySingleRow(String sql, Object... parameters){
        Connection connection=null;
        T singleRow=null;
        connection= ConnectionUtils.getConnection();;

        try {
            singleRow=queryRunner.query(connection,sql,new BeanHandler<T>(this.T_Class),parameters);
            return singleRow;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidDBUtils.close(null,null,null);
        }
    }

    public Object queryOneValue(String sql,Object... parameters){
        Connection connection=null;
        Object OneValue=null;
        connection= ConnectionUtils.getConnection();;

        try {
            OneValue=queryRunner.query(connection,sql,new ScalarHandler<>(),parameters);
            return OneValue;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidDBUtils.close(null,null,null);
        }
    }

    public List<Object> queryOneColumnList(String sql,int index,Object... parameters){
        Connection connection=null;
        List<Object> OneColumnList=null;
        connection= ConnectionUtils.getConnection();;

        try {
            OneColumnList=queryRunner.query(connection,sql,new ColumnListHandler<>(index),parameters);
            return OneColumnList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            druidDBUtils.close(null,null,null);
        }
    }
}
