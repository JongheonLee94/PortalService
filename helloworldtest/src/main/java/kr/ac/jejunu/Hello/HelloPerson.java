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
    @Autowired
    private  Hello hello;


    public String sayHello(){
        return hello.sayHello()+ name;
    }
}
