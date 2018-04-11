
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
//스태틱을 쓰면 클래스없이 자유롭게 쓸 수 있음
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

//예외처리를 스로우만 했는데 이유는 우리가 잘 알 때만 예외처리하고  모른다면 버려야함 버리면 알아서 처리시켜줌
//런타임 입세션은 안꺼짐
    public class UserDaoTest {

        private UserDao userDao;
        private DaoFactory daoFactory;
        @Before   //미리 실행하게 하는 것
        public  void setUp(){

            ApplicationContext applicationContext=new AnnotationConfigApplicationContext( DaoFactory.class );
            userDao = applicationContext.getBean( "userDao",UserDao.class );
        }




    @Test
    public void uppdate() throws SQLException, ClassNotFoundException {
        User user = new User();
        Integer id = insertUserTest(user);

        user.setId(id);
        user.setName("허윤호");
        user.setPassword("1234");
        userDao.update(user);

        User updatedUser = userDao.get(id);

        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));


    }
    private Integer insertUserTest(User user) throws SQLException, ClassNotFoundException {
            user.setName("헐크");
            user.setPassword("1111");
            return userDao.insert(user);
    }
    @Test
    public void delete() throws SQLException, ClassNotFoundException {
            User user = new User();
            Integer id = insertUserTest(user);

            userDao.delete(id);

            User deletedUser = userDao.get(id);
            assertThat(deletedUser, nullValue());
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        int id= 1;
        User user = userDao.get(id);
        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("이종헌"));
        assertThat(user.getPassword(), is("0000"));
//        Assert.assertEquals(); 기본메소드가 있음
    }

    @Test
    public  void add() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("헐크" );
        user.setPassword( "1111" );
        Integer id = userDao.insert(user); //이렇게 하거나 아니면
        User insertedUser =userDao.get(id);  //

        assertThat(insertedUser.getId(),is(id));
        assertThat( insertedUser.getName(),is(user.getName()) );
        assertThat( insertedUser.getPassword(),is(user.getPassword()) );
    }


}