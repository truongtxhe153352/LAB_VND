package edu.java.spring.dao.impl;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {

    public static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    private void createTablefNotExist() throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exists!");
            return;
        }

        jdbcTemplate.execute("CREATE TABLE STUDENT ("
                + " id BIGINT PRIMARY KEY GENERATED ALWAYS " +
                "AS IDENTITY ( START WITH 1, INCREMENT BY 1),"
                + " name VARCHAR(1000),"
                + " age INTEGER)");
    }

    @Override
    public void insert(Student student) {
        jdbcTemplate.update("INSERT INTO STUDENT (name, age) VALUES (?, ?)",
                student.getName(), student.getAge());
        LOGGER.info("Created Record Name = " + student.getName());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.execute("DELETE FROM STUDENT WHERE ID ="+id);
    }

    @Override
    public Student get(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = " + id, new StudentRowMapper());
    }
    @Override
    public void update(Student student) {
        jdbcTemplate.update("UPDATE STUDENT SET NAME = ? WHERE ID = ?", student.getName(), student.getId());
    }

    @Override
    public List<Student> listSearch(String name) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE name like'%" + name + "%'",  new StudentRowMapper());
    }

//    @Override
//    public void listSearch(String name) {
//        jdbcTemplate.query("SELECT * FROM STUDENT WHERE name like'%" + name + "%'",  new StudentRowMapper());
//    }


    private final static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));

                return student;
            } catch (SQLException e) {
                LOGGER.error(e, e);
                return null;
            }
            // return null;
        }
    }

    @Override
    public List<Student> list() {
        return jdbcTemplate.query("Select * from Student",new StudentRowMapper());
    }



    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:derby:D:/Maven_Unit03_Spring/spring_mvc/sampledb_unit3;shutdown=true");
    }


}
