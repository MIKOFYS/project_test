package JDBC.BaseDAO;

import java.util.List;

public interface BaseDAO_Interface<T>{

    public int execute(String sql,Object... parameters);

    //dml
    public int update(String sql,Object... parameters);

    //query
    public List<T> queryMultiRow(String sql, Object... parameters);


    public T querySingleRow(String sql, Object... parameters);


    public Object queryOneValue(String sql,Object... parameters);


    public List<Object> queryOneColumnList(String sql,int index,Object... parameters);

}
