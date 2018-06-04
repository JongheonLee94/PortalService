package kr.ac.jejunu.Hello;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class HelloPerson implements Hello {
    @Value( "허윤호" )
    private  String name;
    private  final Hello hello;

    @Autowired
    public HelloPerson(Hello hello){ //실수를 줄이기 위해 명시적으로 construct를 만드는 게 일반적이라고 한다.
        this.hello = hello;
    }

    public String sayHello(){
        return hello.sayHello()+ name;
    }
}
