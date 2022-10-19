package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import java.lang.annotation.Repeatable;

public class HelloWorld {
    private final static Logger LOGGER = Logger.getLogger(HelloWorld.class);
    private String message;

    @Qualifier("helloJavaClazz2")
    @Autowired(required = true)
    private HelloClazz clazz;

    public HelloClazz getClazz() {
        return clazz;
    }

    public void setClazz(HelloClazz clazz) {
        this.clazz = clazz;
    }

    public HelloWorld(String message) {
        this.message = message;
    }

    public HelloWorld() {
    }

    public String getMessage() {
        return message;
    }

    @Required
    public void setMessage(String message) {
        this.message = message;
    }

    public void sayHello(){
        clazz.printMessage();
        LOGGER.info("From Hello World: " + message);
    }

    public HelloWorld(String name, HelloClazz clazz){
        message = "From HelloWorld constructor:" + name + ":"+clazz.getMessage();
    }

}
