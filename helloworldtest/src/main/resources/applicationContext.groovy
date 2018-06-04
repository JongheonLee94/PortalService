import kr.ac.jejunu.Hello.HelloImpl
import kr.ac.jejunu.Hello.HelloPerson

beans {
    hello(HelloImpl){

    }
    helloPerson(HelloPerson){
        name = '허윤호'
        hello = hello
    }
}