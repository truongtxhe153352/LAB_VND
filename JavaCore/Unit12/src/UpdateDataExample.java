import java.io.File;
import java.sql.*;

public class UpdateDataExample {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        //  Statement statement = null;
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
        );
     //   statement = connection.createStatement();
        System.out.println("db path " + file.getAbsolutePath());
        PreparedStatement statement =
                connection.prepareStatement(
                        "UPDATE Student SET Name = ? WHERE ID = ?");

        statement.setString(1, "Le Thi Z");
        statement.setInt(2, 2);
        statement.executeUpdate();
        System.out.println("Update database successful");



        connection.close();

    }
}
