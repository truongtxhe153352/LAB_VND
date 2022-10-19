import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class WebRowSetExample {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
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

            ResultSet rs = statement.executeQuery("select * from student");

            File files = new File("output.xml");
            FileWriter writer = new FileWriter(files);
            WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
            wrs.writeXml(rs, writer);

            Desktop.getDesktop().open(file);

        } finally {
            connection.close();
            statement.close();
        }
    }
}
