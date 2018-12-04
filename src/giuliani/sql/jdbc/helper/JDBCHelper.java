package giuliani.sql.jdbc.helper;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface JDBCHelper {

    PrepareHelper set(int param) throws SQLException;

    PrepareHelper set(long param) throws SQLException;

    PrepareHelper set(String param) throws SQLException;

    PrepareHelper set(byte param) throws SQLException;

    PrepareHelper set(float param) throws SQLException;

    PrepareHelper set(double param) throws SQLException;

    PrepareHelper set(Timestamp param) throws SQLException;

    PrepareHelper addBatch() throws SQLException;

    QueryResult executeQuery() throws SQLException;

    UpdateResult executeUpdate() throws SQLException;

}