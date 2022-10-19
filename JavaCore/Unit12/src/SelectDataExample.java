import java.io.File;
import java.sql.*;

public class SelectDataExample {
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

           ResultSet rs = statement.executeQuery("select * from student");
            while (rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               int age = rs.getInt(3);

                System.out.println(id + "\t" + name + "\t" + age);
            }

          rs.close();
            connection.close();

        } finally {
            statement.close();
            connection.close();
        }
    }
}
