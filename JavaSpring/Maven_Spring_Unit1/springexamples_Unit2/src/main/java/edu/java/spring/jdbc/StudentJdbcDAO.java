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
    private static Logger LOGGER = Logger.getLogger(StudentJdbcDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private String insertQuery;


    public StudentJdbcDAO() {
    }

    public StudentJdbcDAO(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        StudentJdbcDAO.LOGGER = LOGGER;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    private void createTableIfNotExist() throws SQLException {
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet resultSet = dbmd.getTables(null, null, "STUDENT", null);
        if (resultSet.next()) {
            LOGGER.info("Table " + resultSet.getString("TABlE_NAME") + " already existed !");
            return;
        }
        jdbcTemplate.execute("CREATE TABLE STUDENT("
                + " id BIGINT PRIMARY KEY GENERATED ALWAYS " +
                "AS IDENTITY (START WITH 1, INCREMENT BY 1)."
                + " name VARCHAR(1000),"
                + " age INTEGER)");

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(String name, int age) {
        jdbcTemplate.update(insertQuery, name, age);
        LOGGER.info("Created Record Name = " + name + " Age = " + age);
    }

//    public int totalRecord(){
//        return jdbcTemplate.execute(new StatementCallback<Integer>() {
//            @Override
//            public Integer doInStatement(Statement statement) throws SQLException, DataAccessException {
//                ResultSet rs = statement.executeQuery("select count(*) from student");
//                return rs.next()?rs.getInt(1):0;
//            }
//        });
//    }

    public int totalRecord() {
        return jdbcTemplate.execute((Statement statement) -> {
            ResultSet rs = statement.executeQuery("select count(*) from student");
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    public final static class StudentRowMapper implements RowMapper<Student> {

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
        }
    }

    public String getUpdateAgeByNameSQL() {
        return updateAgeByNameSQL;
    }

    public void setUpdateAgeByNameSQL(String updateAgeByNameSQL) {
        this.updateAgeByNameSQL = updateAgeByNameSQL;
    }

    public List loadStudent(String name) {
        return jdbcTemplate.query("" +
                "SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'", new StudentRowMapper());
    }

    private String updateAgeByNameSQL;
    public void updateAgeByName(int age, String name){
        jdbcTemplate.update(updateAgeByNameSQL, age, name);
        LOGGER.info("UPDATE STUDENT SET AGE = " + age + " WHERE NAME = " + name);
    }

    private String deleteByNameSQL;

    public String getDeleteByNameSQL() {
        return deleteByNameSQL;
    }

    public void setDeleteByNameSQL(String deleteByNameSQL) {
        this.deleteByNameSQL = deleteByNameSQL;
    }

    public void deleteByName(String name){
        jdbcTemplate.update(deleteByNameSQL, name);
    }

    public  int[] add(List<Student> students){
        List<Object[]> batch = new ArrayList<>();
        students.forEach(student -> batch.add(new Object[]{student.getName(), student.getAge()}));
        return jdbcTemplate.batchUpdate(insertQuery, batch);
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Autowired
    private PlatformTransactionManager transactionManager;
    public void save(Object name, Object age) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        String countQuery = "SELECT COUNT(*) FROM STUDENT";
        try {
            Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("before save data , total records is " + total);

            LOGGER.info("before delete data , total records is " + total);
            String sql = "insert into Student (name, age) values (?, ?)";
            jdbcTemplate.update(sql, name, age);

            total  = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after save data, total record id " + total);

            String countQuery2 = "SELECT COUNT (* FROM STUDENT";
            total = jdbcTemplate.queryForObject(countQuery2, Integer.class);

            transactionManager.commit(status);
        } catch (Exception exp){
            transactionManager.rollback(status);

            Integer  total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after rollback, total record is " + total);
        }
    }

}
