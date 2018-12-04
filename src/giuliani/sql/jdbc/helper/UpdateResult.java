package giuliani.sql.jdbc.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UpdateResult {


    private final PreparedStatement ps;
    private final Connection con;

    UpdateResult(PreparedStatement ps, Connection con) {
        this.ps = ps;
        this.con = con;
    }

    public long result() throws SQLException {
        try {
            return ps.getLargeUpdateCount();
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }
}
