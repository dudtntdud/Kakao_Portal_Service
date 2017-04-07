package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ncl on 2017-04-07.
 */
public interface StatementStrategy {
    public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException;
}
