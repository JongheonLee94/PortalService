package kr.ac.jejunu.spring;

import kr.ac.jejunu.Hello.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/user")
@Controller
@Slf4j
public class UserController {
    @GetMapping("/servlet")
    public void servlet(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        Integer id = Integer.parseInt( request.getParameter( "id" ) );
        String name = request.getParameter( "name" );
        String password = request.getParameter( "password" );
        User user = new User();
        user.setId( id );
        user.setName( name );
        user.setPassword( password );
        session.setAttribute( "user", user );
        response.getWriter().println( String.format( "<h1> ID :%s </h1><br/>", id ) );
        response.getWriter().println( String.format( "<h1> %s :%s </h1><br/>", "Name", name ) );
        response.getWriter().println( String.format( "<h1> %s :%s </h1><br/>", "Password", password ) );
    }

    @GetMapping("/session")
    public ModelAndView session(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView( "user" );
        modelAndView.addObject( "user", session.getAttribute( "user" ) );
        return modelAndView;
    }
//    @GetMapping("/path/{id}")
//    public ModelAndView user(@PathVariable Integer id, @RequestParam String name, @RequestParam String password){
//        ModelAndView modelAndView = new ModelAndView( "user" );
//        User user = new User();
//        user.setId( id );
//        user.setName( name );
//        user.setPassword( password );
//        modelAndView.addObject( "user",user );
//        return modelAndView;
//    }

//    @GetMapping("/path/{id}")
//    public ModelAndView user(@PathVariable Integer id, @MatrixVariable("name") String name, @MatrixVariable("password") String password){
//        //매트릭스 variable 쓰려면 annotation driven에 enable-matrix-variables="true"설정해 줘야함
//        //http://localhost:8080/user/path/45;name=hulk;password=1234                참고http://localhost:8080/user/path/45;name=hulk;password=1234&33;name=aa;password=1212
//        ModelAndView modelAndView = new ModelAndView( "user" );
//        User user = new User();
//        user.setId( id );
//        user.setName( name );
//        user.setPassword( password );
//        modelAndView.addObject( "user",user );
//        return modelAndView;
//    }

    @GetMapping("/path/{id}/{name:[a-z]+}") //문자 제한도 걸 수 있음
    public ModelAndView user(@PathVariable Integer id, @PathVariable("name") String name,
                             @RequestParam(value = "password", required = false, defaultValue = "1234") String password,
                             HttpServletResponse response) { //쿠키는 response에 의해서 구워짐
        //RequestParam은 기본적으로 required가 true이며 false로 할 경우 누락이 가능하게 된다.
        ModelAndView modelAndView = new ModelAndView( "user" );
        User user = new User();
        user.setId( id );
        user.setName( name );
        user.setPassword( password );
        modelAndView.addObject( "user", user );
        Cookie idCookie = new Cookie( "id", String.valueOf( id ) );
        Cookie nameCookie = new Cookie( "name", name );
        Cookie password1Cookie = new Cookie( "password", password );
        idCookie.setPath( "/user/cookie" );
        nameCookie.setPath( "/user/cookie" ); //path가 없으면 현재 path쿠키저장됨
        password1Cookie.setPath( "/user/cookie" );
//        response.addCookie(new Cookie( "id",String.valueOf( id ) ));
        response.addCookie( idCookie );
        response.addCookie( nameCookie );
        response.addCookie( password1Cookie ); //쿠키도 서버에서 구워주는 것
        return modelAndView;
    }

    @GetMapping("/cookie") //문자 제한도 걸 수 있음
    public ModelAndView cookie(@CookieValue("id") Integer id, @CookieValue("name") String name,
                               @CookieValue("password") String password) {
        //RequestParam은 기본적으로 required가 true이며 false로 할 경우 누락이 가능하게 된다.
        ModelAndView modelAndView = new ModelAndView( "user" );
        User user = new User();
        user.setId( id );
        user.setName( name );
        user.setPassword( password );
        modelAndView.addObject( "user", user );
        return modelAndView;
    }
//    @GetMapping
////    public void user(Model model){//void에 뷰네임이 없기 떄문에 상위 매핑된 것 씀 패스로 jsp찾음
////        User user = new User();
////        user.setId( 1 );
////        user.setName( "name" );
////        user.setPassword( "aaaa" );
////        model.addAttribute( user );
////    }

//    @GetMapping
//    public void user(@ModelAttribute User user){ //파라미터로 가져오고 파라미터로 리턴함 @ModelAttribute생략가능
//    }

    @GetMapping
    public User user() {
        return new User();  //attributename이 없지만 오브젝트명을 따라감, urlpath가 뷰네임
    }
    @PostMapping
    public void user(User user){
        log.info( "************* void test user ****************" );
    } //위메소드와 post get차이로 찾아줌

    @GetMapping("/string")
    public String returnStringTest(){  //string은 리턴하면 뷰네임이 됨 path가 string이지만 user.jsp가져옴
        return "user";
    }
    @GetMapping("/redirect")
    public String redirectStringTest(){
        return "redirect:/user";  //리다이렉트로 user불러옴
        //리다이렉트: url을 지정한 곳으로 바꿔줌, 리다이렉트와 관련된 리퀘스트 리스펀스 여러가지 정보들을 절단하고 지정된 곳으로 새로 요청
    }
    @GetMapping("/forward")
    public String forwardStringTest(){
        return "forward:/user";
        //주소창은 foward그대로인데 화면은 /user로 해줌   , 받은 요청을 그대로 지정된 곳으로 보냄
    }

}
