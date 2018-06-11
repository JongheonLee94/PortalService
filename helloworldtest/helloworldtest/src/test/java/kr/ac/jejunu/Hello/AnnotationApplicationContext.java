package kr.ac.jejunu.Hello;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnnotationApplicationContext {
    @Test
    public void test(){
        AnnotationConfigApplicationContext annotationApplicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.Hello");
        Hello hello =annotationApplicationContext.getBean( "helloPerson", Hello.class );
        assertThat(hello.sayHello(),is("Hello!!!허윤호"));
    }
}
