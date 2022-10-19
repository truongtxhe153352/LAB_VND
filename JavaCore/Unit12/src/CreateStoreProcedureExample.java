import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;

public class CreateStoreProcedureExample {
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
            System.out.println("Create database successful");

            statement.executeUpdate("CREATE PROCEDUrE GETAGE(STREAM_NAME VARCHAR(255), OUT AGE INT)"+
                    " PARAMETER STYLE JAVA READS " +
                    " SQL DATA LANGUAGE JAVA EXTERNAL NAME" +
                    " 'jdbc.DbFunction.getAge'");
            System.out.println("done");

        } finally {
            connection.close();
            statement.close();
        }
    }
}
