/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="dt_farmerdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="DtFarmerdetails.findAll", query="SELECT d FROM DtFarmerdetails d"), 
    @NamedQuery(name="DtFarmerdetails.findByFarmerId", query="SELECT d FROM DtFarmerdetails d WHERE d.farmerId = :farmerId"), 
    @NamedQuery(name="DtFarmerdetails.findByAccountNo", query="SELECT d FROM DtFarmerdetails d WHERE d.accountNo = :accountNo"), 
    @NamedQuery(name="DtFarmerdetails.findByBranch", query="SELECT d FROM DtFarmerdetails d WHERE d.branch = :branch"), 
    @NamedQuery(name="DtFarmerdetails.findByCenterNo", query="SELECT d FROM DtFarmerdetails d WHERE d.centerNo = :centerNo"),
    @NamedQuery(name="DtFarmerdetails.findByDob", query="SELECT d FROM DtFarmerdetails d WHERE d.dob = :dob"), 
    @NamedQuery(name="DtFarmerdetails.findByFname", query="SELECT d FROM DtFarmerdetails d WHERE d.fname = :fname"), 
    @NamedQuery(name="DtFarmerdetails.findByGender", query="SELECT d FROM DtFarmerdetails d WHERE d.gender = :gender"), 
    @NamedQuery(name="DtFarmerdetails.findByIdno", query="SELECT d FROM DtFarmerdetails d WHERE d.idno = :idno"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuranceID", query="SELECT d FROM DtFarmerdetails d WHERE d.insuranceID = :insuranceID"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuranceType", query="SELECT d FROM DtFarmerdetails d WHERE d.insuranceType = :insuranceType"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredAddress", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredAddress = :insuredAddress"),
    @NamedQuery(name="DtFarmerdetails.findByInsuredCity", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredCity = :insuredCity"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredDOB", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredDOB = :insuredDOB"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredEmail", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredEmail = :insuredEmail"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredFname", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredFname = :insuredFname"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredLname", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredLname = :insuredLname"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredPhone", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredPhone = :insuredPhone"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredRelationship", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredRelationship = :insuredRelationship"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredState", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredState = :insuredState"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredType", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredType = :insuredType"), 
    @NamedQuery(name="DtFarmerdetails.findByInsuredZip", query="SELECT d FROM DtFarmerdetails d WHERE d.insuredZip = :insuredZip"), 
    @NamedQuery(name="DtFarmerdetails.findByLname", query="SELECT d FROM DtFarmerdetails d WHERE d.lname = :lname"), 
    @NamedQuery(name="DtFarmerdetails.findByMaritalStatus", query="SELECT d FROM DtFarmerdetails d WHERE d.maritalStatus = :maritalStatus"), 
    @NamedQuery(name="DtFarmerdetails.findByMno", query="SELECT d FROM DtFarmerdetails d WHERE d.mno = :mno"), 
    @NamedQuery(name="DtFarmerdetails.findByPEmail", query="SELECT d FROM DtFarmerdetails d WHERE d.pEmail = :pEmail"), 
    @NamedQuery(name="DtFarmerdetails.findByPaddress", query="SELECT d FROM DtFarmerdetails d WHERE d.paddress = :paddress"), 
    @NamedQuery(name="DtFarmerdetails.findByPaymentType", query="SELECT d FROM DtFarmerdetails d WHERE d.paymentType = :paymentType"), 
    @NamedQuery(name="DtFarmerdetails.findByPcity", query="SELECT d FROM DtFarmerdetails d WHERE d.pcity = :pcity"), 
    @NamedQuery(name="DtFarmerdetails.findByPcoverageToDate", query="SELECT d FROM DtFarmerdetails d WHERE d.pcoverageToDate = :pcoverageToDate"), 
    @NamedQuery(name="DtFarmerdetails.findByPolicyGroup", query="SELECT d FROM DtFarmerdetails d WHERE d.policyGroup = :policyGroup"), 
    @NamedQuery(name="DtFarmerdetails.findByPphone", query="SELECT d FROM DtFarmerdetails d WHERE d.pphone = :pphone"), 
    @NamedQuery(name="DtFarmerdetails.findByPstate", query="SELECT d FROM DtFarmerdetails d WHERE d.pstate = :pstate"), 
    @NamedQuery(name="DtFarmerdetails.findByPzip", query="SELECT d FROM DtFarmerdetails d WHERE d.pzip = :pzip"), 
    @NamedQuery(name="DtFarmerdetails.findByRegDate", query="SELECT d FROM DtFarmerdetails d WHERE d.regDate = :regDate"), 
    @NamedQuery(name="DtFarmerdetails.findBySpouseFname", query="SELECT d FROM DtFarmerdetails d WHERE d.spouseFname = :spouseFname"), 
    @NamedQuery(name="DtFarmerdetails.findBySpouseID", query="SELECT d FROM DtFarmerdetails d WHERE d.spouseID = :spouseID"), 
    @NamedQuery(name="DtFarmerdetails.findBySpouseLname", query="SELECT d FROM DtFarmerdetails d WHERE d.spouseLname = :spouseLname")
})
public class DtFarmerdetails
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional=false)
  @Column(name="farmerId", nullable=false, length=100)
  private String farmerId;
  @Basic(optional=false)
  @Column(name="account_no", nullable=false, length=75)
  private String accountNo;
  @Basic(optional=false)
  @Column(name="branch", nullable=false)
  private int branch;
  @Basic(optional=false)
  @Column(name="center_no", nullable=false, length=20)
  private String centerNo;
  @Basic(optional=false)
  @Column(name="DOB", nullable=false, length=20)
  private String dob;
  @Basic(optional=false)
  @Column(name="Fname", nullable=false, length=100)
  private String fname;
  @Basic(optional=false)
  @Column(name="Gender", nullable=false, length=10)
  private String gender;
  @Basic(optional=false)
  @Column(name="idno", nullable=false, length=100)
  private String idno;
  @Basic(optional=false)
  @Column(name="InsuranceID", nullable=false, length=50)
  private String insuranceID;
  @Basic(optional=false)
  @Column(name="InsuranceType", nullable=false, length=100)
  private String insuranceType;
  @Basic(optional=false)
  @Column(name="insuredAddress", nullable=false, length=50)
  private String insuredAddress;
  @Basic(optional=false)
  @Column(name="InsuredCity", nullable=false, length=50)
  private String insuredCity;
  @Basic(optional=false)
  @Column(name="InsuredDOB", nullable=false, length=50)
  private String insuredDOB;
  @Basic(optional=false)
  @Column(name="InsuredEmail", nullable=false, length=50)
  private String insuredEmail;
  @Basic(optional=false)
  @Column(name="InsuredFname", nullable=false, length=100)
  private String insuredFname;
  @Basic(optional=false)
  @Column(name="InsuredLname", nullable=false, length=100)
  private String insuredLname;
  @Basic(optional=false)
  @Column(name="InsuredPhone", nullable=false, length=50)
  private String insuredPhone;
  @Basic(optional=false)
  @Column(name="InsuredRelationship", nullable=false, length=50)
  private String insuredRelationship;
  @Basic(optional=false)
  @Column(name="InsuredState", nullable=false, length=100)
  private String insuredState;
  @Basic(optional=false)
  @Column(name="InsuredType", nullable=false, length=50)
  private String insuredType;
  @Basic(optional=false)
  @Column(name="InsuredZip", nullable=false, length=50)
  private String insuredZip;
  @Basic(optional=false)
  @Column(name="Lname", nullable=false, length=100)
  private String lname;
  @Basic(optional=false)
  @Column(name="Marital_Status", nullable=false, length=50)
  private String maritalStatus;
  @Basic(optional=false)
  @Column(name="mno", nullable=false, length=50)
  private String mno;
  @Basic(optional=false)
  @Column(name="P_Email", nullable=false, length=100)
  private String pEmail;
  @Basic(optional=false)
  @Column(name="P_address", nullable=false, length=100)
  private String paddress;
  @Basic(optional=false)
  @Column(name="payment_type", nullable=false, length=50)
  private String paymentType;
  @Basic(optional=false)
  @Column(name="P_city", nullable=false, length=100)
  private String pcity;
  @Basic(optional=false)
  @Lob
  @Column(name="PcoverageFrmDate", nullable=false, length=Integer.MAX_VALUE)
  private String pcoverageFrmDate;
  @Basic(optional=false)
  @Column(name="PcoverageToDate", nullable=false, length=50)
  private String pcoverageToDate;
  @Basic(optional=false)
  @Lob
  @Column(name="Plan_name", nullable=false, length=Integer.MAX_VALUE)
  private String planname;
  @Basic(optional=false)
  @Column(name="PolicyGroup", nullable=false, length=100)
  private String policyGroup;
  @Basic(optional=false)
  @Column(name="P_phone", nullable=false, length=50)
  private String pphone;
  @Basic(optional=false)
  @Column(name="P_state", nullable=false, length=100)
  private String pstate;
  @Basic(optional=false)
  @Column(name="P_zip", nullable=false, length=20)
  private String pzip;
  @Basic(optional=false)
  @Column(name="RegDate", nullable=false, length=20)
  private String regDate;
  @Basic(optional=false)
  @Column(name="SpouseFname", nullable=false, length=100)
  private String spouseFname;
  @Basic(optional=false)
  @Column(name="SpouseID", nullable=false, length=50)
  private String spouseID;
  @Basic(optional=false)
  @Column(name="SpouseLname", nullable=false, length=100)
  private String spouseLname;
  
  public DtFarmerdetails() {}
  
  public DtFarmerdetails(String farmerId)
  {
    this.farmerId = farmerId;
  }
  
  public DtFarmerdetails(String farmerId, String accountNo, int branch, String centerNo, String dob, String fname, String gender, String idno, String insuranceID, String insuranceType, String insuredAddress, String insuredCity, String insuredDOB, String insuredEmail, String insuredFname, String insuredLname, String insuredPhone, String insuredRelationship, String insuredState, String insuredType, String insuredZip, String lname, String maritalStatus, String mno, String pEmail, String paddress, String paymentType, String pcity, String pcoverageFrmDate, String pcoverageToDate, String planname, String policyGroup, String pphone, String pstate, String pzip, String regDate, String spouseFname, String spouseID, String spouseLname)
  {
    this.farmerId = farmerId;
    this.accountNo = accountNo;
    this.branch = branch;
    this.centerNo = centerNo;
    this.dob = dob;
    this.fname = fname;
    this.gender = gender;
    this.idno = idno;
    this.insuranceID = insuranceID;
    this.insuranceType = insuranceType;
    this.insuredAddress = insuredAddress;
    this.insuredCity = insuredCity;
    this.insuredDOB = insuredDOB;
    this.insuredEmail = insuredEmail;
    this.insuredFname = insuredFname;
    this.insuredLname = insuredLname;
    this.insuredPhone = insuredPhone;
    this.insuredRelationship = insuredRelationship;
    this.insuredState = insuredState;
    this.insuredType = insuredType;
    this.insuredZip = insuredZip;
    this.lname = lname;
    this.maritalStatus = maritalStatus;
    this.mno = mno;
    this.pEmail = pEmail;
    this.paddress = paddress;
    this.paymentType = paymentType;
    this.pcity = pcity;
    this.pcoverageFrmDate = pcoverageFrmDate;
    this.pcoverageToDate = pcoverageToDate;
    this.planname = planname;
    this.policyGroup = policyGroup;
    this.pphone = pphone;
    this.pstate = pstate;
    this.pzip = pzip;
    this.regDate = regDate;
    this.spouseFname = spouseFname;
    this.spouseID = spouseID;
    this.spouseLname = spouseLname;
  }
  
  public String getFarmerId()
  {
    return this.farmerId;
  }
  
  public void setFarmerId(String farmerId)
  {
    this.farmerId = farmerId;
  }
  
  public String getAccountNo()
  {
    return this.accountNo;
  }
  
  public void setAccountNo(String accountNo)
  {
    this.accountNo = accountNo;
  }
  
  public int getBranch()
  {
    return this.branch;
  }
  
  public void setBranch(int branch)
  {
    this.branch = branch;
  }
  
  public String getCenterNo()
  {
    return this.centerNo;
  }
  
  public void setCenterNo(String centerNo)
  {
    this.centerNo = centerNo;
  }
  
  public String getDob()
  {
    return this.dob;
  }
  
  public void setDob(String dob)
  {
    this.dob = dob;
  }
  
  public String getFname()
  {
    return this.fname;
  }
  
  public void setFname(String fname)
  {
    this.fname = fname;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getIdno()
  {
    return this.idno;
  }
  
  public void setIdno(String idno)
  {
    this.idno = idno;
  }
  
  public String getInsuranceID()
  {
    return this.insuranceID;
  }
  
  public void setInsuranceID(String insuranceID)
  {
    this.insuranceID = insuranceID;
  }
  
  public String getInsuranceType()
  {
    return this.insuranceType;
  }
  
  public void setInsuranceType(String insuranceType)
  {
    this.insuranceType = insuranceType;
  }
  
  public String getInsuredAddress()
  {
    return this.insuredAddress;
  }
  
  public void setInsuredAddress(String insuredAddress)
  {
    this.insuredAddress = insuredAddress;
  }
  
  public String getInsuredCity()
  {
    return this.insuredCity;
  }
  
  public void setInsuredCity(String insuredCity)
  {
    this.insuredCity = insuredCity;
  }
  
  public String getInsuredDOB()
  {
    return this.insuredDOB;
  }
  
  public void setInsuredDOB(String insuredDOB)
  {
    this.insuredDOB = insuredDOB;
  }
  
  public String getInsuredEmail()
  {
    return this.insuredEmail;
  }
  
  public void setInsuredEmail(String insuredEmail)
  {
    this.insuredEmail = insuredEmail;
  }
  
  public String getInsuredFname()
  {
    return this.insuredFname;
  }
  
  public void setInsuredFname(String insuredFname)
  {
    this.insuredFname = insuredFname;
  }
  
  public String getInsuredLname()
  {
    return this.insuredLname;
  }
  
  public void setInsuredLname(String insuredLname)
  {
    this.insuredLname = insuredLname;
  }
  
  public String getInsuredPhone()
  {
    return this.insuredPhone;
  }
  
  public void setInsuredPhone(String insuredPhone)
  {
    this.insuredPhone = insuredPhone;
  }
  
  public String getInsuredRelationship()
  {
    return this.insuredRelationship;
  }
  
  public void setInsuredRelationship(String insuredRelationship)
  {
    this.insuredRelationship = insuredRelationship;
  }
  
  public String getInsuredState()
  {
    return this.insuredState;
  }
  
  public void setInsuredState(String insuredState)
  {
    this.insuredState = insuredState;
  }
  
  public String getInsuredType()
  {
    return this.insuredType;
  }
  
  public void setInsuredType(String insuredType)
  {
    this.insuredType = insuredType;
  }
  
  public String getInsuredZip()
  {
    return this.insuredZip;
  }
  
  public void setInsuredZip(String insuredZip)
  {
    this.insuredZip = insuredZip;
  }
  
  public String getLname()
  {
    return this.lname;
  }
  
  public void setLname(String lname)
  {
    this.lname = lname;
  }
  
  public String getMaritalStatus()
  {
    return this.maritalStatus;
  }
  
  public void setMaritalStatus(String maritalStatus)
  {
    this.maritalStatus = maritalStatus;
  }
  
  public String getMno()
  {
    return this.mno;
  }
  
  public void setMno(String mno)
  {
    this.mno = mno;
  }
  
  public String getPEmail()
  {
    return this.pEmail;
  }
  
  public void setPEmail(String pEmail)
  {
    this.pEmail = pEmail;
  }
  
  public String getPaddress()
  {
    return this.paddress;
  }
  
  public void setPaddress(String paddress)
  {
    this.paddress = paddress;
  }
  
  public String getPaymentType()
  {
    return this.paymentType;
  }
  
  public void setPaymentType(String paymentType)
  {
    this.paymentType = paymentType;
  }
  
  public String getPcity()
  {
    return this.pcity;
  }
  
  public void setPcity(String pcity)
  {
    this.pcity = pcity;
  }
  
  public String getPcoverageFrmDate()
  {
    return this.pcoverageFrmDate;
  }
  
  public void setPcoverageFrmDate(String pcoverageFrmDate)
  {
    this.pcoverageFrmDate = pcoverageFrmDate;
  }
  
  public String getPcoverageToDate()
  {
    return this.pcoverageToDate;
  }
  
  public void setPcoverageToDate(String pcoverageToDate)
  {
    this.pcoverageToDate = pcoverageToDate;
  }
  
  public String getPlanname()
  {
    return this.planname;
  }
  
  public void setPlanname(String planname)
  {
    this.planname = planname;
  }
  
  public String getPolicyGroup()
  {
    return this.policyGroup;
  }
  
  public void setPolicyGroup(String policyGroup)
  {
    this.policyGroup = policyGroup;
  }
  
  public String getPphone()
  {
    return this.pphone;
  }
  
  public void setPphone(String pphone)
  {
    this.pphone = pphone;
  }
  
  public String getPstate()
  {
    return this.pstate;
  }
  
  public void setPstate(String pstate)
  {
    this.pstate = pstate;
  }
  
  public String getPzip()
  {
    return this.pzip;
  }
  
  public void setPzip(String pzip)
  {
    this.pzip = pzip;
  }
  
  public String getRegDate()
  {
    return this.regDate;
  }
  
  public void setRegDate(String regDate)
  {
    this.regDate = regDate;
  }
  
  public String getSpouseFname()
  {
    return this.spouseFname;
  }
  
  public void setSpouseFname(String spouseFname)
  {
    this.spouseFname = spouseFname;
  }
  
  public String getSpouseID()
  {
    return this.spouseID;
  }
  
  public void setSpouseID(String spouseID)
  {
    this.spouseID = spouseID;
  }
  
  public String getSpouseLname()
  {
    return this.spouseLname;
  }
  
  public void setSpouseLname(String spouseLname)
  {
    this.spouseLname = spouseLname;
  }
  
  public int hashCode()
  {
    int hash = 0;
    hash += (this.farmerId != null ? this.farmerId.hashCode() : 0);
    return hash;
  }
  
  public boolean equals(Object object)
  {
    if (!(object instanceof DtFarmerdetails)) {
      return false;
    }
    DtFarmerdetails other = (DtFarmerdetails)object;
    if (((this.farmerId == null) && (other.farmerId != null)) || ((this.farmerId != null) && (!this.farmerId.equals(other.farmerId)))) {
      return false;
    }
    return true;
  }
  
  public String toString()
  {
    return "farm.entity.DtFarmerdetails[ farmerId=" + this.farmerId + " ]";
  }
}
