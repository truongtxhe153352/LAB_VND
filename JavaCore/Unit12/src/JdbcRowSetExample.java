import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.io.File;
import java.sql.SQLException;

public class JdbcRowSetExample {
    public static void main(String[] args) throws SQLException {
        JdbcRowSet jdbcRs = RowSetProvider.newFactory().createJdbcRowSet();
        File file = new File("./sampledb");
        jdbcRs.setUrl("jdbc:derby:" + file);
        jdbcRs.setCommand("select * from student");
        jdbcRs.execute();
        while (jdbcRs.next()) {
            System.out.println(jdbcRs.getInt(1) + "\t" + jdbcRs.getString("name"));
        }

        jdbcRs.first();
        jdbcRs.updateString("name", "Hoang Van X");
        jdbcRs.updateRow();
        jdbcRs.commit();

        jdbcRs.first();
        System.out.println(jdbcRs.getString("id") + "\t" + jdbcRs.getString(2));

        jdbcRs.absolute(8);
        System.out.println(jdbcRs.getString("name") + ":" + jdbcRs.getString("age"));


        jdbcRs.close();
    }

//    public static void main(String args[]) throws Exception {
//        try (// su dung try-with-resources
//             // tao connection
//             JdbcRowSet jRS = RowSetProvider.newFactory().createJdbcRowSet();) {
//            // thiet lap ket noi database
//            jRS.setUrl("jdbc:mysql:D:\\Unit12\\sampledb");
//            // thiet lap username
//            jRS.setUsername("root");
//            // thiet lap password
//            jRS.setPassword("1234567890");
//            // thiet lap sql query de thuc thi
//            jRS.setCommand("select * from user");
//            // thuc thi query
//            jRS.execute();
//            while (jRS.next()) {
//                System.out.println(jRS.getInt(1) + " " + jRS.getString(2)
//                        + " " + jRS.getString(3));
//            }
//        }
//        catch (Exception e) { // xu ly ngoai le
//            System.out.println(e);
//        }
//    }
}
