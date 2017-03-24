package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ncl on 2017-03-24.
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    private ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }

    //여기서 특정한 경우(jeju or halla)의 상황에서 분기를 줘야함(환경변수같은걸 사용해서)
    //추상화 시킬필요없음
}
