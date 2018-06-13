package kr.ac.jejunu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

@Configuration
@EnableWebMvc   //annotation-driven과 같은효과
@ComponentScan(basePackages = "kr.ac.jejunu")  //<context:component-scan base-package="kr.ac.jejunu"/>과 같은 것
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/images/**" ).addResourceLocations( "/WEB-INF/static/" );
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType( "js", MediaType.APPLICATION_JSON ); // js=application/json
        configurer.mediaType( "x",MediaType.APPLICATION_XML ); // x=application/xml
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp( "/WEB-INF/views/", ".jsp" );
        registry.enableContentNegotiation( new MappingJackson2JsonView() );
        registry.enableContentNegotiation( new MappingJackson2XmlView() );
    }
}
