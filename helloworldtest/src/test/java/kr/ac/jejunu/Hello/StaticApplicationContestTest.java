package kr.ac.jejunu.Hello;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class StaticApplicationContestTest {
    @Test
    public void staticApplicationContextTest(){
        StaticApplicationContext staticApplicationContext = new StaticApplicationContext(  );
        staticApplicationContext.registerSingleton( "hello",HelloImpl.class );
        Hello hello = staticApplicationContext.getBean( "hello" , Hello.class);
        assertThat(hello.sayHello(),is("Hello!!!"));
    }
    @Test
    public void staticApplicationContextTithDI(){
        StaticApplicationContext applicationContext = new StaticApplicationContext(  );
        applicationContext.registerSingleton( "hello" ,HelloPerson.class);
        BeanDefinition beanDefinition = new RootBeanDefinition( HelloPerson.class );
        beanDefinition.getPropertyValues().addPropertyValue( "name","허윤호" );
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("hello");  //이걸로 스태틱은 construct씀
        beanDefinition.getPropertyValues().addPropertyValue( "hello",new RuntimeBeanNameReference( "hello" ) );
        applicationContext.registerBeanDefinition( "helloPerson", beanDefinition );
        Hello hello =applicationContext.getBean( "helloPerson", Hello.class );
        assertThat( hello.sayHello(),is("hello!!! 허윤호") );
    }
}
