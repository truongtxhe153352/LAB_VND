package edu.hanoi.spring.jax.ws;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.print.attribute.standard.MediaSize;

@WebService
@Component
public class UserService {
    @WebMethod(operationName = "say")
    public String sayHello(String name){
        return "Hanoi Java say hello to " + name;
    }
}
