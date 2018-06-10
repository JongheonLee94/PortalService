package kr.ac.jejunu.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends GenericServlet
{

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("*************************servlet init*****************************"); //필요한 환경정보
        super.init( config );
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("*************************servlet service*****************************");//이 안에 서비스 구현  브라우저 해더 등 http규약에 맞춘 정보들은 이 오브젝트에서 감쌈
        res.getWriter().println( "<h1> Hello World !!! </h1>" );
    }

    @Override
    public void destroy() {
        log.info("*************************servlet destroy*****************************");  //자원해제
        super.destroy();
    }
}
