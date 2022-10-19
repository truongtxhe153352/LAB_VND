package edu.java.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.registerShutdownHook();

//        HelloClazz obj = (HelloClazz) context.getBean("helloJavaClazz");
//        obj.printMessage();
//        System.out.println(obj);
//
//        HelloClazz obj2 = (HelloClazz) context.getBean("helloJavaClazz2");
//        obj2.printMessage();
//        System.out.println(obj2);
//        System.out.println(obj == obj2);

//        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
//        obj.sayHello();
//        System.out.println(obj);

//        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
//        obj.sayHello();
//        System.out.println(obj);


        //HelloClazz helloClazz = new HelloClazz();
//        JavaClazz clazz = (JavaClazz) context.getBean("jee01");
//        System.out.println("Map Implement is " + clazz.getStudents().getClass());
//        System.out.println("There are " + clazz.getStudents().size() + " in the class");
//        System.out.println("=====================");
//        clazz = (JavaClazz) context.getBean("jee01");
//        System.out.println("Total classes is " + clazz.);


//        System.out.println("=====================");
//        HelloClazz clazz = (HelloClazz) context.getBean("helloJavaClazz");
//        System.out.println("Total classes is " + clazz.getClazzes().size());

//        HelloWorld clazz = (HelloWorld) context.getBean("helloWorld");
//        clazz.sayHello();
//        System.out.println("=====================");
//        HelloClazz helloClazz = (HelloClazz) context.getBean("clazz");
//        helloClazz.printMessage();

        HelloWorld clazz = (HelloWorld) context.getBean("helloWorld");
        clazz.sayHello();
        System.out.println("=====================");

      //  context.start();

        // 34 doi byte -> type name
    }
}
