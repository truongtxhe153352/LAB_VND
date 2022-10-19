import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
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
            System.out.println("Insert database successful");

            statement.execute("insert into student (name, age) values ('Tran Van B', 20)");
            statement.execute("insert into student (name, age) values ('Nguyen Van C', 56)");
            statement.execute("insert into student (name, age) values ('Trinh Huong D', 21)");
            statement.execute("insert into student (name, age) values ('Trinh Xuan E', 20)");

        } finally {
            statement.close();
            connection.close();
        }
    }
}
