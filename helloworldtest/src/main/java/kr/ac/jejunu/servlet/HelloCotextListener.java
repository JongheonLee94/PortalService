package kr.ac.jejunu.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class HelloCotextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info( "************************ context listenner init***************************" );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info( "************************ context listenner destroy***************************" );
    }
}
