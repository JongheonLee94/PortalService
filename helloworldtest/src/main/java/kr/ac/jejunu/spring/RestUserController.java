package kr.ac.jejunu.spring;

import kr.ac.jejunu.Hello.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController  //@RestController =@controller + responsebody 각메소드에 @ResponseBddy가 있다고 생각하면 됨
@RequestMapping("/rest")
public class RestUserController {
    @GetMapping("{id}")
    public User get(@PathVariable Integer id) { //스프링에서 httpmessageconvertor가 있는데 response를 전달할때 오브젝트를 json이나 xml로 변환해줌 request도 마찬기지
        User user = new User();
        user.setId( id );
        user.setName( "hulk" );   //아이디로 디비에서 가져왔다는 가정하
        user.setPassword( "1111" );
        return user;
    }//스프링에서 xml을 우선시하기 때문에 json을 하고 싶다면 xml 라이브러리를 지우거나

    @PostMapping
    public User create(@RequestBody User user) {//create는 post로 받는 게 rest규칙임 url은 get과 같음
        return user;
    }

    @PutMapping
    public User update(@RequestBody User user) {//rest방식에서 동일한 url에 put매핑으로 보내는 것으로 약속함
        return user;
    }

    @DeleteMapping
    public void delete(@PathVariable Integer id) {

    }

}
