package kr.ac.jejunu.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class SpringWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.scan( "kr.ac.jejunu" ); //@Configuration이 여러개 있을 수도 있기 때문에 스캔해서 찾아줌.
        ServletRegistration.Dynamic registration = servletContext.addServlet( "dispatcher",new DispatcherServlet( applicationContext ) );
        registration.setLoadOnStartup( 1 );
        registration.addMapping( "/" ); //이 패스 하위에서 찾음
    }
}
