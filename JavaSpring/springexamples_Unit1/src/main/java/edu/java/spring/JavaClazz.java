package edu.java.spring;

import java.util.Map;

public class JavaClazz {
    private Map<String, Integer> students;

    public JavaClazz() {
    }

    public JavaClazz(Map<String, Integer> students) {
        this.students = students;
    }

    public Map<String, Integer> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Integer> students) {
        this.students = students;
    }

}
