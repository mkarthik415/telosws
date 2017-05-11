package telosws.beans;

import telosws.util.Util;

import java.util.Date;

/**
 * Created by karthikmarupeddi on 2/8/15.
 */
public class Clients {

    private Integer id;
    private String clientName;
    private String company;
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private String email;
    private String secondaryEmail;
    private String gender;
    private String industry;
    private String address;
    private Date dob;
    private String policyNumber;
    private String endrsNumber;
    private Date policyStartdate;
    private Date policyEndDate;
    private String InsCompanyName;
    private String InsBranchName;
    private String officeCode;
    private String source;
    private String policyDetails;
    private String agent;
    private String policyType;
    private Date collectionDate;
    private String fireTypeOfPolicy;
    private Double basicRate;
    private Double earthQuakePremium;
    private Double anyAdditionalPremium;
    private String marineTypeOfPolicy;
    private String marineOpenPolicy;
    private String marineOpenCover;
    private String marineOtherPolicies;
    private String marineVoyageFrom;
    private String marineVoyageTo;
    private Double premiumAmount;
    private Double terrorismPremiumAmount;
    private Double serviceTax;
    private Double serviceTaxAmount;
    private Double totalPremiumAmount;
    private Double commionRate;
    private Double commionRateAmount;
    private Double sumInsured;
    private String vehicleNumber;
    private String iDV;
    private String vehicleMake;
    private Date vehicleManufactureYear;
    private String nBC;
    private String department;
    private String miscTypeOfPolicy;
    private String miscIdCard;
    private Date miscDispatchDate;
    private Double renewalAmount;
    private String renewalCompany;


    public Clients(int iD,
                   String clientName,
                   String company,
                   String phoneNumber,
                   String secondaryPhoneNumber,
                   String email,
                   String secondaryEmail,
                   String gender,
                   String industry,
                   String address,
                   Date dob,
                   String policyNumber,
                   String endrsNumber,
                   Date policyStartdate,
                   Date policyEndDate,
                   String InsCompanyName,
                   String InsBranchName,
                   String officeCode,
                   String source,
                   String policyDetails,
                   String agent,
                   String policyType,
                   Date collectionDate,
                   String fireTypeOfPolicy,
                   Double basicRate,
                   Double earthQuakePremium,
                   Double anyAdditionalPremium,
                   String marineTypeOfPolicy,
                   String marineOpenPolicy,
                   String marineOpenCover,
                   String marineOtherPolicies,
                   String marineVoyageFrom,
                   String marineVoyageTo,
                   Double premiumAmount,
                   Double terrorismPremiumAmount,
                   Double serviceTax,
                   Double serviceTaxAmount,
                   Double totalPremiumAmount,
                   Double commionRate,
                   Double commionRateAmount,
                   Double sumInsured,
                   String vehicleNumber,
                   String iDV,
                   String vehicleMake,
                   Date vehicleManufactureYear,
                   String nBC,
                   String department,
                   String miscTypeOfPolicy,
                   String miscIdCard,
                   Date miscDispatchDate,
                   Double renewalAmount,
                   String renewalCompany) {
        this.id = iD;
        this.clientName = clientName;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        this.email = email;
        this.secondaryEmail = secondaryEmail;
        this.gender = gender;
        this.industry = industry;
        this.address = address;
        this.dob = dob;
        this.policyNumber = policyNumber;
        this.endrsNumber = endrsNumber;
        this.policyStartdate = policyStartdate;
        this.policyEndDate = policyEndDate;
        this.InsCompanyName = InsCompanyName;
        this.InsBranchName = InsBranchName;
        this.officeCode = officeCode;
        this.source = source;
        this.policyDetails = policyDetails;
        this.agent = agent;
        this.policyType = policyType;
        this.collectionDate = collectionDate;
        this.fireTypeOfPolicy = fireTypeOfPolicy;
        this.basicRate = basicRate;
        this.earthQuakePremium = earthQuakePremium;
        this.anyAdditionalPremium = anyAdditionalPremium;
        this.marineTypeOfPolicy = marineTypeOfPolicy;
        this.marineOpenPolicy = marineOpenPolicy;
        this.marineOpenCover = marineOpenCover;
        this.marineOtherPolicies = marineOtherPolicies;
        this.marineVoyageFrom = marineVoyageFrom;
        this.marineVoyageTo = marineVoyageTo;
        this.premiumAmount = premiumAmount;
        this.terrorismPremiumAmount = terrorismPremiumAmount;
        this.serviceTax = serviceTax;
        this.serviceTaxAmount = serviceTaxAmount;
        this.totalPremiumAmount = totalPremiumAmount;
        this.commionRate = commionRate;
        this.commionRateAmount = commionRateAmount;
        this.sumInsured = sumInsured;
        this.vehicleNumber = vehicleNumber;
        this.iDV = iDV;
        this.vehicleMake = vehicleMake;
        this.vehicleManufactureYear = vehicleManufactureYear;
        this.nBC = nBC;
        this.department = department;
        this.miscTypeOfPolicy = miscTypeOfPolicy;
        this.miscIdCard = miscIdCard;
        this.miscDispatchDate = miscDispatchDate;
        this.renewalAmount = renewalAmount;
        this.renewalCompany = renewalCompany;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getEndrsNumber() {
        return endrsNumber;
    }

    public void setEndrsNumber(String endrsNumber) {
        this.endrsNumber = endrsNumber;
    }

    public Date getPolicyStartdate() {
        return policyStartdate;
    }

    public void setPolicyStartdate(Date policyStartdate) {
        this.policyStartdate = policyStartdate;
    }

    public Date getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(Date policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public String getInsCompanyName() {
        return InsCompanyName;
    }

    public void setInsCompanyName(String insCompanyName) {
        InsCompanyName = insCompanyName;
    }

    public String getInsBranchName() {
        return InsBranchName;
    }

    public void setInsBranchName(String insBranchName) {
        InsBranchName = insBranchName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(String policyDetails) {
        this.policyDetails = policyDetails;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getFireTypeOfPolicy() {
        return fireTypeOfPolicy;
    }

    public void setFireTypeOfPolicy(String fireTypeOfPolicy) {
        this.fireTypeOfPolicy = fireTypeOfPolicy;
    }

    public Double getBasicRate() {
        return Util.decimalVale(basicRate);
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = Util.decimalVale(basicRate);
    }

    public Double getEarthQuakePremium() {
        return Util.decimalVale(earthQuakePremium);
    }

    public void setEarthQuakePremium(Double earthQuakePremium) {
        this.earthQuakePremium = earthQuakePremium;
    }

    public Double getAnyAdditionalPremium() {
        return Util.decimalVale(anyAdditionalPremium) ;
    }

    public void setAnyAdditionalPremium(Double anyAdditionalPremium) {
        this.anyAdditionalPremium = anyAdditionalPremium;
    }

    public String getMarineTypeOfPolicy() {
        return marineTypeOfPolicy;
    }

    public void setMarineTypeOfPolicy(String marineTypeOfPolicy) {
        this.marineTypeOfPolicy = marineTypeOfPolicy;
    }

    public String getMarineOpenPolicy() {
        return marineOpenPolicy;
    }

    public void setMarineOpenPolicy(String marineOpenPolicy) {
        this.marineOpenPolicy = marineOpenPolicy;
    }

    public String getMarineOpenCover() {
        return marineOpenCover;
    }

    public void setMarineOpenCover(String marineOpenCover) {
        this.marineOpenCover = marineOpenCover;
    }

    public String getMarineOtherPolicies() {
        return marineOtherPolicies;
    }

    public void setMarineOtherPolicies(String marineOtherPolicies) {
        this.marineOtherPolicies = marineOtherPolicies;
    }

    public String getMarineVoyageFrom() {
        return marineVoyageFrom;
    }

    public void setMarineVoyageFrom(String marineVoyageFrom) {
        this.marineVoyageFrom = marineVoyageFrom;
    }

    public String getMarineVoyageTo() {
        return marineVoyageTo;
    }

    public void setMarineVoyageTo(String marineVoyageTo) {
        this.marineVoyageTo = marineVoyageTo;
    }

    public Double getPremiumAmount() {
        return Util.decimalVale(premiumAmount) ;
    }

    public void setPremiumAmount(Double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public Double getTerrorismPremiumAmount() {
        return Util.decimalVale(terrorismPremiumAmount) ;
    }

    public void setTerrorismPremiumAmount(Double terrorismPremiumAmount) {
        this.terrorismPremiumAmount = terrorismPremiumAmount;
    }

    public Double getServiceTax() {
        return Util.decimalVale(serviceTax);
    }

    public void setServiceTax(Double serviceTax) {
        this.serviceTax = serviceTax;
    }

    public Double getServiceTaxAmount() {
        return Util.decimalVale(serviceTaxAmount);
    }

    public void setServiceTaxAmount(Double serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public Double getTotalPremiumAmount() {
        return Util.decimalVale(totalPremiumAmount);
    }

    public void setTotalPremiumAmount(Double totalPremiumAmount) {
        this.totalPremiumAmount = totalPremiumAmount;
    }

    public Double getCommionRate() {
        return Util.decimalVale(commionRate);
    }

    public void setCommionRate(Double commionRate) {
        this.commionRate = commionRate;
    }

    public Double getCommionRateAmount() {
        return Util.decimalVale(commionRateAmount);
    }

    public void setCommionRateAmount(Double commionRateAmount) {
        this.commionRateAmount = commionRateAmount;
    }

    public Double getSumInsured() {
        return Util.decimalVale(sumInsured);
    }

    public void setSumInsured(Double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getiDV() {
        return iDV;
    }

    public void setiDV(String iDV) {
        this.iDV = iDV;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public Date getVehicleManufactureYear() {
        return vehicleManufactureYear;
    }

    public void setVehicleManufactureYear(Date vehicleManufactureYear) {
        this.vehicleManufactureYear = vehicleManufactureYear;
    }

    public String getnBC() {
        return nBC;
    }

    public void setnBC(String nBC) {
        this.nBC = nBC;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMiscTypeOfPolicy() {
        return miscTypeOfPolicy;
    }

    public void setMiscTypeOfPolicy(String miscTypeOfPolicy) {
        this.miscTypeOfPolicy = miscTypeOfPolicy;
    }

    public String getMiscIdCard() {
        return miscIdCard;
    }

    public void setMiscIdCard(String miscIdCard) {
        this.miscIdCard = miscIdCard;
    }

    public Date getMiscDispatchDate() {
        return miscDispatchDate;
    }

    public void setMiscDispatchDate(Date miscDispatchDate) {
        this.miscDispatchDate = miscDispatchDate;
    }

    public Double getRenewalAmount() {
        return Util.decimalVale(renewalAmount) ;
    }

    public void setRenewalAmount(Double renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public String getRenewalCompany() {
        return renewalCompany;
    }

    public void setRenewalCompany(String renewalCompany) {
        this.renewalCompany = renewalCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
