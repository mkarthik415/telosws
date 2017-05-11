package telosws.beans;

/**
 * Created by karthikmarupeddi on 2/6/15.
 */
public class User {

    public User(Integer iD, Integer team) {
        this.iD = iD;
        this.team = team;
    }

    private Integer iD;
    private Integer team;


    public Integer getiD() {
        return iD;
    }

    public void setiD(Integer iD) {
        this.iD = iD;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }
}
