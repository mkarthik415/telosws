package telosws.mapping;

import org.springframework.jdbc.core.RowMapper;
import telosws.beans.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by karthikmarupeddi on 2/8/15.
 */
public class DocumentMapping implements RowMapper {

    final private static String ID = "id";
    final private static String NAME = "name";
    final private static String DESCRIPTION = "description";
    final private static String SCANNED = "scanned";


    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws java.sql.SQLException if a SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document document = new Document(rs.getInt(ID),
                rs.getString(NAME), rs.getString(DESCRIPTION),
                rs.getBlob(SCANNED));
        return document;
    }
}
