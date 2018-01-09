package telosws.beans;

import telosws.util.Util;

import java.util.Date;

/**
 * Created by karthikmarupeddi on 2/8/15.
 */
public class TouchClients {

    private Integer id;
    private String clientName;
    private String industry;
    private Date policyEndDate;
    private Double totalPremiumAmount;
    private String department;


    public TouchClients(int iD,
                        String clientName,
                        String department,
                        Double totalPremiumAmount,
                        String industry,
                        Date policyEndDate) {
        this.id = iD;
        this.clientName = clientName;
        this.industry = industry;
        this.policyEndDate = policyEndDate;
        this.totalPremiumAmount = totalPremiumAmount;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(Date policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public Double getTotalPremiumAmount() {
        return totalPremiumAmount;
    }

    public void setTotalPremiumAmount(Double totalPremiumAmount) {
        this.totalPremiumAmount = totalPremiumAmount;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
