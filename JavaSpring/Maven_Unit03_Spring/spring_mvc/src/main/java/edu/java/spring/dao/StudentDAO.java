package edu.java.spring.dao;

import edu.java.spring.model.Student;

import java.util.List;

public interface StudentDAO {
    public void insert(Student student);

    public void delete(int id);

    public Student get(int id);

    public void update(Student student);

    public List<Student> listSearch(String name);

    public List<Student> list();
}
