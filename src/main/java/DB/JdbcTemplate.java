package DB;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JdbcTemplate {

    private DataSource ds;

    public <T> List<T> query(String sql, Object[] params, RowMapper<T> mapper) throws SQLException {
        Connection connection=ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            statement.setObject(i+1,params[i]);
        }
        ResultSet rs = statement.executeQuery();
        List<T> list=new ArrayList<>();
        while (rs.next()){
            T t = mapper.map(rs);
            list.add(t);
        }
        connection.close();
        return list;
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper) throws SQLException {
        return query(sql,new Object[]{},mapper);
    }

    public <T> List<T> queryOne(String sql, Object[] params, RowMapper<T> mapper)throws SQLException{
        Connection connection=ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            statement.setObject(i+1,params[i]);
        }
        ResultSet rs = statement.executeQuery();
        List<T> list = new ArrayList<>();
        if (rs.next()){
            T t = mapper.map(rs);
            list.add(t);
        }
        connection.close();
        return list;
    }

    public <T> List<T> queryOne(String sql, RowMapper<T> mapper) throws SQLException{
        return queryOne(sql,new Object[]{}, mapper);
    }



    public void update(String sql, Object[] params) throws SQLException {
        Connection connection=ds.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            statement.setObject(i+1, params[i]);
        }
        statement.execute();
        connection.close();
    }

    public void update(String sql) throws SQLException {
        update(sql,new Object[]{});
    }
}
