package kr.ac.jejunu.spring;

import kr.ac.jejunu.Hello.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
public class RestUserController {
    @GetMapping("{id}")
@ResponseBody //여기다 해도 됨
    public User get(@PathVariable Integer id){ //스프링에서 httpmessageconvertor가 있는데 response를 전달할때 오브젝트를 json이나 xml로 변환해줌 request도 마찬기지
        User user= new User();
        user.setId( id );
        user.setName( "hulk" );   //아이디로 디비에서 가져왔다는 가정하
        user.setPassword( "1111" );
        return user;
    }//스프링에서 xml을 우선시하기 때문에 json을 하고 싶다면 xml 라이브러리를 지우거나
}
