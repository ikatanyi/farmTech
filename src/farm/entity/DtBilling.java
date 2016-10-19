/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "dt_billing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtBilling.findAll", query = "SELECT d FROM DtBilling d"),
    @NamedQuery(name = "DtBilling.findById", query = "SELECT d FROM DtBilling d WHERE d.id = :id"),
    @NamedQuery(name = "DtBilling.findByCustomerId", query = "SELECT d FROM DtBilling d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtBilling.findByDueDate", query = "SELECT d FROM DtBilling d WHERE d.dueDate = :dueDate"),
    @NamedQuery(name = "DtBilling.findByCredit", query = "SELECT d FROM DtBilling d WHERE d.credit = :credit"),
    @NamedQuery(name = "DtBilling.findByDebit", query = "SELECT d FROM DtBilling d WHERE d.debit = :debit"),
    @NamedQuery(name = "DtBilling.findByDate", query = "SELECT d FROM DtBilling d WHERE d.date = :date")})
public class DtBilling implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "service_code", nullable = false, length = 20)
    private String serviceCode;
    @Basic(optional = false)
    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;
    @Basic(optional = false)
    @Column(name = "debit", nullable = false)
    private double debit;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
//    private Set<DtSalestrx> sales;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment_code", referencedColumnName="id")
    private DtPayment payment;
    
    public DtBilling() {
    }

    public DtBilling(Integer id) {
        this.id = id;
    }

    public DtBilling(String serviceCode, String customerId, double credit, double debit, Date date) {
//        this.id = id;
        this.serviceCode = serviceCode;
        this.customerId = customerId;
        this.credit = credit;
        this.debit = debit;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DtPayment getPayment() {
        return payment;
    }

    public void setPayment(DtPayment payment) {
        this.payment = payment;
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
        if (!(object instanceof DtBilling)) {
            return false;
        }
        DtBilling other = (DtBilling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtBilling[ id=" + id + " ]";
    }
    
}
