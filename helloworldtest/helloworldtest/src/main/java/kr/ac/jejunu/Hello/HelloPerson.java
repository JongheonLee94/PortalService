package kr.ac.jejunu.Hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
//@AllArgsConstructor      //이건 전부 construct로
@RequiredArgsConstructor   //final필드에 있는 애만 construct로 만들어주고 autowired까지 해줌
public class HelloPerson implements Hello {
    @Value( "허윤호" )
    private  String name;
    private  final Hello hello;

    public String sayHello(){
        return hello.sayHello()+ name;
    }
}
