import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFilterExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        FilteredRowSet frs = RowSetProvider.newFactory().createFilteredRowSet();
        File file = new File("./sampledb");
        frs.setUrl("jdbc:derby:" + file);

        frs.setCommand("select * from student");
        frs.setFilter(new DataFilter());
        frs.execute();

        System.out.println("ID\tName\t\tAge");
        while (frs.next()){
            System.out.println(frs.getString("id") + "\t" + frs.getString("name")+ "\t" + frs.getString("age"));
        }
    }
}
