package kr.ac.jejunu.Hello;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ResourceApplicationContextTest {
    @Test
    public void classpathXmlApplicationContextTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext( "applicationContext.xml" );
        Hello hello = applicationContext.getBean( "helloPerson", Hello.class );
        assertThat(hello.sayHello(),is("Hello!!!허윤호"));
    }
}
