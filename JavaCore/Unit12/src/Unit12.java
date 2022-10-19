import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Unit12 {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();

            System.out.println("db path " + file.getAbsolutePath());
            System.out.println("Create database successful");

            String sql = "create table student(" +
                    " id bigint primary key" +
                    " generated always as identity (start with 1, increment by 1)," +
                    " name varchar(1000), " +
                    " age integer default 20 ) ";

            System.out.println(statement.execute(sql));
        } catch (ClassNotFoundException e) {
            System.out.println("loi la:" + e);
//            throw new RuntimeException(e);
//            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }


    }
}
