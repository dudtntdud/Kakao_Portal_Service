package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ncl on 2017-03-15.
 */
public class UserDaoTest {
//    DaoFactory daoFactory;
    UserDao userDao;

    @Before
    public void setup(){
        //daoFactory = new  DaoFactory();
        //ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        //위의 생성은 클래스 파일로 관리해야하기 때문에 아래와 같은 xml파일을 사용
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "강석원";
        String password = "1234";


        //UserDao userDao = daoFactory.getUserDao();
        //이게 스프링 framework임, 의존성주입(디펜던시 인젝션,DI)
        //클라이언트가 계속해서 new Jeju,HallaConnectionMaker를 선언해야하는것을 방지, 팩토리 패턴 -> 내가 필요한 인스턴스를 만들어내는 공장
        //UserDao userDao = new UserDao(new JejuConnectionMaker());
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        String name = "헐크";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
       //UserDao userDao = daoFactory.getUserDao();
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }
}
