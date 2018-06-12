package kr.ac.jejunu.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HelloInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info( "******************** prehandle *********************" );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //인터셉트해서 뷰 등 바꿀 수 있음
        log.info( "******************* postHandle *************************" );
        if(modelAndView!=null)
        log.info( (String)modelAndView.getModel().get( "hello" ) );

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info( "************* afterCompletion *********************" );
        //로그인 취소등 가능
    }
}
