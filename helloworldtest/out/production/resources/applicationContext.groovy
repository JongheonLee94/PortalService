import kr.ac.jejunu.Hello.HelloImpl
import kr.ac.jejunu.Hello.HelloPerson

beans {
    hello(HelloImpl){

    }
    helloPerson(HelloPerson, hello){ //그루비는 파라미터에 넣어서 construct 인젝션함
        name = '허윤호'

    }
}