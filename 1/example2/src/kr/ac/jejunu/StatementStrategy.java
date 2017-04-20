package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ncl on 2017-04-21.
 */
public interface StatementStrategy {
    public abstract PreparedStatement makeStatement(Connection connection) throws SQLException;
}
