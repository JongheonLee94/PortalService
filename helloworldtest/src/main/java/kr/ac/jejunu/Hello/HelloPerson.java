package kr.ac.jejunu.Hello;

import lombok.Data;

@Data
public class HelloPerson implements Hello {
    private  String name;
    private  Hello hello;

    public String sayHello(){
        return hello.sayHello()+ name;
    }
}
