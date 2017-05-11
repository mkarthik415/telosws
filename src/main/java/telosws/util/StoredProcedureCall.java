package telosws.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by karthikmarupeddi on 5/30/15.
 */


public class StoredProcedureCall extends StoredProcedure {
    public StoredProcedureCall(JdbcTemplate jdbcTemplate, String spName) {
        super(jdbcTemplate, spName);
        setFunction(false);
    }
}