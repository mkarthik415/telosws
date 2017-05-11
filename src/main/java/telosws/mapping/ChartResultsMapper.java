package telosws.mapping;


import org.springframework.jdbc.core.RowMapper;
import telosws.beans.ChartResults;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by karthikmarupeddi on 10/11/15.
 */
public class ChartResultsMapper implements RowMapper {

    public static final String YEARS = "years";
    public static final String DEPARTMENT = "DEPARTMENT";
    public static final String NUMBER = "Number";
    public static final String COMMISSION_AMOUNT = "CommissionAmount";
    public static final String MISSED_POLICIES = "MissedPolicies";
    public static final String NEW_POLICIES = "NewPolicies";
    public static final String RENEWAL_POLICIES = "RenewalPolicies";

    /**
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call {@code next()} on
     * the ResultSet; it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws SQLException if a SQLException is encountered getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChartResults chartResults = new ChartResults(rs.getInt(YEARS),rs.getString(DEPARTMENT),rs.getInt(NUMBER));
                return chartResults;
    }
}
