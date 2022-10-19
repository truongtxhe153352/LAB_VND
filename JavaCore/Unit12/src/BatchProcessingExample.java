import java.io.File;
import java.sql.*;

public class BatchProcessingExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
        );
        statement = connection.createStatement();
        System.out.println("db path " + file.getAbsolutePath());
        for (int i = 0; i < 20; i++) {
            String name = "Tran Van " + i;
            int age = 10 + i;
           String sql = "insert into student (name, age)"+
                   " values( '" + name + "',"+age + ")";
           statement.addBatch(sql);
        }
        statement.executeBatch();

        ResultSet rs = statement.executeQuery("select count(*) from student");
        if (rs.next()) System.out.println(" total records = " + rs.getInt(1));
        connection.close();
    }
    }

