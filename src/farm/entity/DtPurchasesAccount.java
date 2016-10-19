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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_purchases_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtPurchasesAccount.findAll", query = "SELECT d FROM DtPurchasesAccount d"),
    @NamedQuery(name = "DtPurchasesAccount.findById", query = "SELECT d FROM DtPurchasesAccount d WHERE d.id = :id"),
    @NamedQuery(name = "DtPurchasesAccount.findByDate", query = "SELECT d FROM DtPurchasesAccount d WHERE d.date = :date"),
    @NamedQuery(name = "DtPurchasesAccount.findByDepartmentId", query = "SELECT d FROM DtPurchasesAccount d WHERE d.departmentId = :departmentId"),
    @NamedQuery(name = "DtPurchasesAccount.findByPurchaseId", query = "SELECT d FROM DtPurchasesAccount d WHERE d.purchaseId = :purchaseId"),
    @NamedQuery(name = "DtPurchasesAccount.findByDescription", query = "SELECT d FROM DtPurchasesAccount d WHERE d.description = :description"),
    @NamedQuery(name = "DtPurchasesAccount.findByDebit", query = "SELECT d FROM DtPurchasesAccount d WHERE d.debit = :debit"),
    @NamedQuery(name = "DtPurchasesAccount.findByCredit", query = "SELECT d FROM DtPurchasesAccount d WHERE d.credit = :credit"),
    @NamedQuery(name = "DtPurchasesAccount.findByUnits", query = "SELECT d FROM DtPurchasesAccount d WHERE d.units = :units")})
public class DtPurchasesAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "department_id", nullable = false,length = 50)
    private String departmentId;
    @Basic(optional = false)
    @Column(name = "purchase_id",nullable = false,length = 50)
    private String purchaseId;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Basic(optional = false)
    @Column(name = "debit", nullable = false)
    private double debit;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;
    @Basic(optional = false)
    @Column(name = "units", nullable = false)
    private double units;
    
//    @OneToOne
//    @MapsId
//    @JoinColumn(name="purchase_id",insertable=false,updatable=false)
//    private DtCoffeCollection coffee_collec;
//    
//    @OneToOne
//    @MapsId
    @OneToOne(mappedBy="purchases")
    private DtDailycollection collection;        

    public DtPurchasesAccount() {
    }

    public DtPurchasesAccount(Integer id) {
        this.id = id;
    }

    public DtPurchasesAccount(int id, Date date, String departmentId, String description, double debit, double credit, double units,String purchaseId) {
        this.id = id;
        this.date = date;
        this.departmentId = departmentId;
        this.description = description;
        this.purchaseId=purchaseId;
        this.debit = debit;
        this.credit = credit;
        this.units = units;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public DtDailycollection getCollection() {
        return collection;
    }

    public void setCollection(DtDailycollection collection) {
        this.collection = collection;
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
        if (!(object instanceof DtPurchasesAccount)) {
            return false;
        }
        DtPurchasesAccount other = (DtPurchasesAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtPurchasesAccount[ id=" + id + " ]";
    }
    
}
