import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DataAccessTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataAccessLogic data = new DataAccessLogic();
        List<String> names = new ArrayList<String>();
        names = data.loadNames(1);
        names.forEach(System.out::println);
        System.out.println("Total page = " + data.numberOfPage());

        IntStream.range(1, data.numberOfPage() + 1).forEach(page ->{
            System.out.println("================" + page);
                List<String> name = new ArrayList<String>();
            try {
                name = data.loadNames(page);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name);
        });
    }
}
