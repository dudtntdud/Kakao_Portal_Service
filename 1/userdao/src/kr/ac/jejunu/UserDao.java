package kr.ac.jejunu;

import java.sql.*;

/**
 * Created by ncl on 2017-03-15.
 */
public class UserDao {
    public static User get(Long id) throws SQLException, ClassNotFoundException {
        //kr.ac.jejunu.User 어디? Mysql
        //Class를 로딩해야 함
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/portal", "root", "rkdehfdl03");
        //쿼리를 만들어야함
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setLong(1, id);
        //쿼리를 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        //실행된 결과를 객체에 매핑
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //결과를 리턴
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/portal", "root", "rkdehfdl03");

        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name, password) VALUES (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
}
