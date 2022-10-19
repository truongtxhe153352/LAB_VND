import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccessLogic {
    private final static Logger logger = Logger.getLogger(DataAccessLogic.class.getName());
    Connection connection = null;
    Statement statement = null;
    CachedRowSet rowset;


    int pageSize = 4;
    List<String> loadNames(int page) throws SQLException {
        List<String> names = new ArrayList<>();
        rowset.setPageSize(pageSize);
        int start = (page-1)*pageSize+1;
        if (!rowset.absolute(start)) return names;

         rowset.previous();
         while (rowset.next()){
             names.add(rowset.getString("name"));
             if (names.size()>=pageSize)break;
         }
         return names;
    }


    public DataAccessLogic() throws ClassNotFoundException, SQLException {
        File file = new File("./sampledb");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        connection = DriverManager.getConnection(
                "jdbc:derby:" + file.getAbsolutePath() + ";create=true"
        );
        statement = connection.createStatement();
        System.out.println("db path " + file.getAbsolutePath());
        System.out.println("Connect database successfully!");

        rowset = RowSetProvider.newFactory().createCachedRowSet();
        rowset.setCommand("select * from student");
        rowset.execute(connection);
    }

    public void disconnect(){
        try {
            if (connection != null) connection.close();
        } catch (SQLException e){
            logger.log(Level.WARNING, e.toString());
        }
    }

    public int numberOfPage() throws SQLException {
        statement = null;
        try {
statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from student");
            if (!rs.next()) return 0;
            int total = rs.getInt(1);
            int totalPage = total/pageSize;
            if (total%pageSize!= 0) totalPage++;
            return totalPage;
        } finally {
            statement.close();
        }
    }
}
