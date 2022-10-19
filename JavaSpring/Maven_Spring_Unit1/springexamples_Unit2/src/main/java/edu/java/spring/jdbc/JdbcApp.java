package edu.java.spring.jdbc;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

public class JdbcApp {
    private final static Logger LOGGER = Logger.getLogger(JdbcApp.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentJdbcDAO jdbcDAO = (StudentJdbcDAO) context.getBean("studentJdbcDAO");
        LOGGER.info(" created bean " + jdbcDAO);

        jdbcDAO.insert("Tran Van C", 27);

        System.out.println("===============");
        LOGGER.info("Total students is " + jdbcDAO.totalRecord());
        System.out.println("=========FIND=======");
        jdbcDAO.loadStudent("C").forEach(student -> LOGGER.info(student));
        System.out.println("==========UPDATE======");
        jdbcDAO.updateAgeByName(21, "Tran Van C");
        System.out.println("=========FIND=======");
        jdbcDAO.loadStudent("C").forEach(student -> LOGGER.info(student));
        System.out.println("=========Delete=====");
        jdbcDAO.deleteByName("Tran Van C");
        System.out.println("=========FIND=======");
        jdbcDAO.loadStudent("C").forEach(student -> LOGGER.info(student));

        System.out.println("=========add=========");
        List<Student> student = new ArrayList<>();
        student.add(new Student("Tran Thi C", 17));
        student.add(new Student("Tran Thi D", 10));
        student.add(new Student("Tran Thi E", 37));
        int[] values = jdbcDAO.add(student);
        for (int i = 0; i < values.length; i++) {
            LOGGER.info("add record " + i + ": " + (values[i]==0?"failed":"success"));
        }
        LOGGER.info("Total student is " + jdbcDAO.totalRecord());


        System.out.println("========SAVE========");
        jdbcDAO.save("Tran Thi A", 23);
    }
}
