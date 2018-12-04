package giuliani.sql.jdbc.helper;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JDBCHelperFactory {

    private static final JDBCHelperFactory factory = new JDBCHelperFactory(null);
    private final DataSource dataSource;

    public JDBCHelperFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PrepareHelper helper(String sql) throws SQLException {
        return new PrepareHelper(sql, dataSource.getConnection());
    }

    static boolean test() {
        try {
            factory.helper("select * from some_table where column = ?").set(1).set("").executeUpdate().result();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    static void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                //Empty
            }
        }
    }
}