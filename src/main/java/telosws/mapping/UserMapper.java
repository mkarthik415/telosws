package telosws.mapping;

import org.springframework.jdbc.core.RowMapper;
import telosws.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by karthikmarupeddi on 2/6/15.
 */
public class UserMapper implements RowMapper {


    final private static String TEAM	=	"team";
    final private static String ID	=	"id";

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User(rs.getInt(ID),rs.getInt(TEAM));
        return user;
    }



}
