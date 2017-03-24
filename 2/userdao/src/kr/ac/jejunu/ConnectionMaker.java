package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ncl on 2017-03-24.
 */
public interface ConnectionMaker {
    //JejuConnectionMaker 외에 Halla 등의 커넥션 메이커 클래스를 추상화 하기 위한 인터페이스
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
