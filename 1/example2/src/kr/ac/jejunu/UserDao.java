package kr.ac.jejunu;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

/**
 * Created by ncl on 2017-04-20.
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public User get(Long id) throws ClassNotFoundException, SQLException {
        String sql = "select * from example where id = ? ";
        Object[] params = new Object[]{id};

        User user1 = null;

        try {
            user1 = jdbcTemplate.queryForObject(sql, params, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user1;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        String sql = "insert into example(id, name,password) values (?, ?,?)";
        Object[] params = new Object[] {user.getId(), user.getName(), user.getPassword()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from example where id = ?";
        Object[] params = new Object[] {id};
        jdbcTemplate.update(sql, params);
    }


}
