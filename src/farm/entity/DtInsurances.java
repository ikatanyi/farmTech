/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_insurances")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtInsurances.findAll", query = "SELECT d FROM DtInsurances d"),
    @NamedQuery(name = "DtInsurances.findById", query = "SELECT d FROM DtInsurances d WHERE d.id = :id"),
    @NamedQuery(name = "DtInsurances.findByClientName", query = "SELECT d FROM DtInsurances d WHERE d.clientName = :clientName"),
    @NamedQuery(name = "DtInsurances.findByInsuranceCode", query = "SELECT d FROM DtInsurances d WHERE d.insuranceCode = :insuranceCode"),
    @NamedQuery(name = "DtInsurances.findBySchemeName", query = "SELECT d FROM DtInsurances d WHERE d.schemeName = :schemeName"),
    @NamedQuery(name = "DtInsurances.findBySchemeName_1", query = "SELECT d FROM DtInsurances d WHERE d.schemeName = :schemeName AND d.clientName = :clientName"),
    @NamedQuery(name = "DtInsurances.findByPhone", query = "SELECT d FROM DtInsurances d WHERE d.phone = :phone"),
    @NamedQuery(name = "DtInsurances.findByAddress", query = "SELECT d FROM DtInsurances d WHERE d.address = :address"),
    @NamedQuery(name = "DtInsurances.findByContactFname", query = "SELECT d FROM DtInsurances d WHERE d.contactFname = :contactFname"),
    @NamedQuery(name = "DtInsurances.findByContactLname", query = "SELECT d FROM DtInsurances d WHERE d.contactLname = :contactLname"),
    @NamedQuery(name = "DtInsurances.findByEmail", query = "SELECT d FROM DtInsurances d WHERE d.email = :email"),
    @NamedQuery(name = "DtInsurances.findByStatus", query = "SELECT d FROM DtInsurances d WHERE d.status = :status"),
    @NamedQuery(name = "DtInsurances.findBySmartStatus", query = "SELECT d FROM DtInsurances d WHERE d.smartStatus = :smartStatus"),
    @NamedQuery(name = "DtInsurances.findByDate", query = "SELECT d FROM DtInsurances d WHERE d.date = :date")})
public class DtInsurances implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "client_name", nullable = false, length = 100)
    private String clientName;
    @Basic(optional = false)
    @Column(name = "insurance_code", nullable = false, length = 20)
    private String insuranceCode;
    @Basic(optional = false)
    @Column(name = "scheme_name", nullable = false, length = 100)
    private String schemeName;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 30)
    private String address;
    @Basic(optional = false)
    @Column(name = "contact_fname", nullable = false, length = 20)
    private String contactFname;
    @Basic(optional = false)
    @Column(name = "contact_lname", nullable = false, length = 50)
    private String contactLname;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private boolean status;
    @Basic(optional = false)
    @Column(name = "smart_status", nullable = false)
    private boolean smartStatus;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtInsurances() {
    }

    public DtInsurances(Integer id) {
        this.id = id;
    }

    public DtInsurances(Integer id, String clientName, String insuranceCode, String schemeName, String phone, String address, String contactFname, String contactLname, String email, boolean status, boolean smartStatus, Date date) {
        this.id = id;
        this.clientName = clientName;
        this.insuranceCode = insuranceCode;
        this.schemeName = schemeName;
        this.phone = phone;
        this.address = address;
        this.contactFname = contactFname;
        this.contactLname = contactLname;
        this.email = email;
        this.status = status;
        this.smartStatus = smartStatus;
        this.date = date;
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

    public String getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(String insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactFname() {
        return contactFname;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    public String getContactLname() {
        return contactLname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getSmartStatus() {
        return smartStatus;
    }

    public void setSmartStatus(boolean smartStatus) {
        this.smartStatus = smartStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtInsurances)) {
            return false;
        }
        DtInsurances other = (DtInsurances) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtInsurances[ id=" + id + " ]";
    }
    
}
