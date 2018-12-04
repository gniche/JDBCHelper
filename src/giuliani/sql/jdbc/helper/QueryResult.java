package giuliani.sql.jdbc.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;

public class QueryResult {
    private final Connection con;
    private final Statement ps;

    QueryResult(Connection con, Statement ps) {
        this.con = con;
        this.ps = ps;
    }

    public int intResult(int def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getInt(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public long longResult(long def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getLong(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public String stringResult(String def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getString(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public float floatResult(int def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getFloat(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public double doubleResult(double def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getDouble(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    /**
     * @param truthValue expected string value expected for true
     * @return
     * @throws SQLException
     */
    public boolean booleanResult(String truthValue) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() && truthValue.equals(rs.getString(1));
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    /**
     *
     * @param truthValue expected string value expected for true
     * @return
     * @throws SQLException
     */
    public boolean booleanResult(int truthValue) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() && truthValue == rs.getInt(1);
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public byte byteResult(byte def) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return rs.next() ? rs.getByte(1) : def;
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

    public <T> T result(Function<ResultSet, T> function) throws SQLException {
        try (ResultSet rs = ps.getResultSet()) {
            return function.apply(rs);
        } finally {
            JDBCHelperFactory.close(con, ps);
        }
    }

}
