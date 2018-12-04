package giuliani.sql.jdbc.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Simple Wrapper class for basic JDBC, use any JDBC connection to quickly execute SQL
 *
 * ** Not Thread Safe **
 */
public class PrepareHelper implements JDBCHelper {

    private final PreparedStatement ps;
    private final Connection con;
    private int index = 1;

    /**
     * @param sql
     * @param con
     * @throws SQLException         if a database access error occurs or this method is called on a closed connection
     * @throws NullPointerException if Connection is null
     */
//        @Transactional
    PrepareHelper(String sql, Connection con) throws SQLException {
        this.con = con;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            con.close();
            throw e;
        }

    }

    @Override
    public PrepareHelper set(int param) throws SQLException {
        try {
            ps.setInt(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper set(long param) throws SQLException {
        try {
            ps.setLong(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }


    @Override
    public PrepareHelper set(String param) throws SQLException {
        try {
            ps.setString(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper set(byte param) throws SQLException {
        try {
            ps.setByte(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper set(float param) throws SQLException {
        try {
            ps.setFloat(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper set(double param) throws SQLException {
        try {
            ps.setDouble(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper set(Timestamp param) throws SQLException {
        try {
            ps.setTimestamp(index++, param);
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }

    @Override
    public PrepareHelper addBatch() throws SQLException {
        try {
            ps.addBatch();
            index = 1;
            return this;
        } catch (SQLException e) {
            throw handleException(e);
        }
    }


    @Override
    public QueryResult executeQuery() throws SQLException {
        try {
            ps.execute();
            return new QueryResult(con, ps);
        } catch (SQLException e) {
            throw handleException(e);
        }
    }


    @Override
    public UpdateResult executeUpdate() throws SQLException {
        try {
            ps.execute();
            return new UpdateResult(ps, con);
        } catch (SQLException e) {
            throw handleException(e);
        }
    }


    private SQLException handleException(SQLException e) {
        JDBCHelperFactory.close(con, ps);
        return e;
    }

}