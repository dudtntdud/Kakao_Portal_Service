package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ncl on 2017-04-20.
 */
public class UserDaoTest {
    private UserDao userDao;

    @Before
    public void setup(){
        //userDao = new DaoFactory().getUserDao();
        ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Long id = Long.valueOf(new Random().nextInt());
        String name = "강석원";
        String password = "2222";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        //UserDao userDao = new UserDao(new DUserDao());
        //UserDao userDao = new DaoFactory().userDao();
        userDao.add(user);

        User resultUser = userDao.get(id);
        assertThat(id,is(resultUser.getId()));
        assertThat(name,is(resultUser.getName()));
        assertThat(password,is(resultUser.getPassword()));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Long id = Long.valueOf(new Random().nextInt());
        String name = "헐크";
        String password = "3333";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);

        userDao.add(user);

        userDao.delete(id);
        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());
    }
}
