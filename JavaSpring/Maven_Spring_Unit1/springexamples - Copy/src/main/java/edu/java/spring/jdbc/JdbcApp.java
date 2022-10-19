package edu.java.spring.jdbc;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class JdbcApp {
    private static final Logger LOGGER = Logger.getLogger(JdbcApp.class);


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentJdbcDAO jdbc = (StudentJdbcDAO) context.getBean("studentJdbcDAO");
        LOGGER.info(" created bean: " + jdbc);
        System.out.println("===============================");
        jdbc.insert("Tran Van A", 24);

        LOGGER.info("Total students is " + jdbc.totalRecord());
        System.out.println("===============================");

//        jdbc.loadStudent("A").forEach(student -> LOGGER.info(student));
//        System.out.println("===============================");
//
//        jdbc.updateAgeByName("Tran Van A", 35);
//        System.out.println("===============================");
//
//        jdbc.deleNameById(601);
//        System.out.println("===============================");
//
//        jdbc.loadStudent("A").forEach(student -> LOGGER.info(student));
//        System.out.println("================================");
//
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Tran Thi A", 17));
        students.add(new Student("Tran Thi B", 17));
        students.add(new Student("Tran Thi C", 17));


        int[] values = jdbc.add(students);
        for (int i = 0; i < values.length; i++) {
            LOGGER.info("add record " + i + ":" + (values[i] == 0 ? "failed" : "success"));
        }
        LOGGER.info("Total students is " + jdbc.totalRecord());

        System.out.println("=============save=============");
     jdbc.save("Tran Thi A", "23");
    }
}
