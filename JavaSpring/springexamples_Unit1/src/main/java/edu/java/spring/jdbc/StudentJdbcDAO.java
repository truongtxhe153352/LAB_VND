package edu.java.spring.jdbc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDAO {
    public static Logger LOGGER = Logger.getLogger(StudentJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    private String insertQuery;

    public StudentJdbcDAO() {
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public StudentJdbcDAO(JdbcTemplate jdbcTemplate, DataSource dataSource, String insertQuery, String updateAgeByNameSQL) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.insertQuery = insertQuery;
        this.updateAgeByNameSQL = updateAgeByNameSQL;
    }

    public String getUpdateAgeByNameSQL() {
        return updateAgeByNameSQL;
    }

    public void setUpdateAgeByNameSQL(String updateAgeByNameSQL) {
        this.updateAgeByNameSQL = updateAgeByNameSQL;
    }

    public StudentJdbcDAO(JdbcTemplate jdbcTemplate, DataSource dataSource, String insertQuery) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.insertQuery = insertQuery;
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        StudentJdbcDAO.LOGGER = LOGGER;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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


    public void insert(String name, int age) {
        jdbcTemplate.update(insertQuery, name, age);
        LOGGER.info("Created Record Name = " + name + " Age = " + age);
    }

    //    UPDAT
    private String updateAgeByNameSQL;
    private String deleNameByIdSQL;

    public String getDeleNameByIdSQL() {
        return deleNameByIdSQL;
    }

    public StudentJdbcDAO(JdbcTemplate jdbcTemplate, DataSource dataSource, String insertQuery, String updateAgeByNameSQL, String deleNameByIdSQL) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
        this.insertQuery = insertQuery;
        this.updateAgeByNameSQL = updateAgeByNameSQL;
        this.deleNameByIdSQL = deleNameByIdSQL;
    }

    public void setDeleNameByIdSQL(String deleNameByIdSQL) {
        this.deleNameByIdSQL = deleNameByIdSQL;
    }

    public void deleNameById(int id) {
        jdbcTemplate.update(deleNameByIdSQL, id);
        LOGGER.info("Delete student where id = " + id);
    }

    public void updateAgeByName(String name, int age) {
        jdbcTemplate.update(updateAgeByNameSQL, age, name);
        LOGGER.info("Update student set age = " + age + "WHERE name = " + name);
    }

//    public int totalRecord(){
//        return jdbcTemplate.execute(new StatementCallback<Integer>() {
//            @Override
//            public Integer doInStatement(Statement statement) throws SQLException, DataAccessException {
//               ResultSet rs = statement.executeQuery("select count(*) from student");
//               return rs.next()?rs.getInt(1):0;
//            }
//        });
//    }


    public int totalRecord() {
        return jdbcTemplate.execute((Statement statement) -> {
            ResultSet rs = statement.executeQuery("select count(*) from student");
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private final static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                Student student = new Student("Tran Thi A", 17);
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

    public List loadStudent(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'", new StudentRowMapper()
        );
    }

    public int[] add(List<Student> students) {
        List<Object[]> batch = new ArrayList<>();
        students.forEach(student -> batch.add(new Object[]{
                student.getName(), student.getAge()
        }));
        return jdbcTemplate.batchUpdate(insertQuery, batch);
    }


    @Autowired
    private PlatformTransactionManager transactionManager;

    public void save(Object name, Object age) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String countQuery = "SELECT COUNT(*) FROM STUDENT";
        try {
            Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("before save data, total record is " + total);

            LOGGER.info("before delete data, total record is " + total);
            String sql = "insert into Student(name, age) values(?,?)";
            jdbcTemplate.update(sql, name, age);

            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after save data, total record is" + total);

            String countQuery2 = "SELECT COUNT(* FROM STUDENT";
            total = jdbcTemplate.queryForObject(countQuery2, Integer.class);

            transactionManager.commit(status);
        } catch (Exception exp) {
            transactionManager.rollback(status);

            Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after rollback, total record is" + total);
        }

    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
