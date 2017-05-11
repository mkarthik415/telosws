package telosws.beans;

/**
 * Created by karthikmarupeddi on 10/11/15.
 */
public class ChartResults {

    private Integer fromYear;
    private Integer toYear;
    private String department;
    private Integer numberOfPolicies;
    private Double commissionAmount;
    private Integer missedPolicies;
    private Integer newPolicies;
    private Integer renewalPolicies;

    public ChartResults(Integer fromYear, Integer toYear, String department, Integer numberOfPolicies, Double commissionAmount, Integer missedPolicies, Integer newPolicies, Integer renewalPolicies) {
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.department = department;
        this.numberOfPolicies = numberOfPolicies;
        this.commissionAmount = commissionAmount;
        this.missedPolicies = missedPolicies;
        this.newPolicies = newPolicies;
        this.renewalPolicies = renewalPolicies;
    }

    public ChartResults(Integer toYear, String department, Integer numberOfPolicies) {
        this.toYear = toYear;
        this.department = department;
        this.numberOfPolicies = numberOfPolicies;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getNumberOfPolicies() {
        return numberOfPolicies;
    }

    public void setNumberOfPolicies(Integer numberOfPolicies) {
        this.numberOfPolicies = numberOfPolicies;
    }

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Integer getMissedPolicies() {
        return missedPolicies;
    }

    public void setMissedPolicies(Integer missedPolicies) {
        this.missedPolicies = missedPolicies;
    }

    public Integer getNewPolicies() {
        return newPolicies;
    }

    public void setNewPolicies(Integer newPolicies) {
        this.newPolicies = newPolicies;
    }

    public Integer getRenewalPolicies() {
        return renewalPolicies;
    }

    public void setRenewalPolicies(Integer renewalPolicies) {
        this.renewalPolicies = renewalPolicies;
    }
}
