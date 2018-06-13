package kr.ac.jejunu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc   //annotation-driven과 같은효과
@ComponentScan(basePackages = "kr.ac.jejunu")  //<context:component-scan base-package="kr.ac.jejunu"/>과 같은 것
public class WebConfig {

}
