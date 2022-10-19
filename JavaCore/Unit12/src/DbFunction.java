import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

public class DbFunction {
    public static void getAge(String name, int[] ages) throws SQLException, ClassNotFoundException {
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

            ResultSet rs = statement.executeQuery("select max(age) from student where name like '%" + name + "%'");
            ages[0] = rs.next() ? rs.getInt(1) : -1;
        } finally {
            connection.close();
            statement.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int[] ages = new int[1];
        getAge("Nguyen ", ages);
        System.out.println(ages[0]);
    }
}
