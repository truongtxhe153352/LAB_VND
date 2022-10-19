import java.io.File;
import java.sql.*;

public class JdbcMetadataExample {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
            );
            statement = connection.createStatement();
            System.out.println("db path" + file.getAbsolutePath());
            System.out.println("Connect database successfully!");
            // siêu dữ liệu
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("db version: " + metaData.getDatabaseMajorVersion());
            System.out.println("driver name: " + metaData.getDriverName());

        } finally {
            connection.close();
            statement.close();
        }
    }
}
