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
@Table(name = "dt_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtPayment.findAll", query = "SELECT d FROM DtPayment d"),
    @NamedQuery(name = "DtPayment.findById", query = "SELECT d FROM DtPayment d WHERE d.id = :id"),
    @NamedQuery(name = "DtPayment.findByDate", query = "SELECT d FROM DtPayment d WHERE d.date = :date"),
    @NamedQuery(name = "DtPayment.findByServiceCode", query = "SELECT d FROM DtPayment d WHERE d.serviceCode = :serviceCode"),
    @NamedQuery(name = "DtPayment.findByCustomerId", query = "SELECT d FROM DtPayment d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtPayment.findByDescription", query = "SELECT d FROM DtPayment d WHERE d.description = :description"),
    @NamedQuery(name = "DtPayment.findByPaymentMethod", query = "SELECT d FROM DtPayment d WHERE d.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "DtPayment.findByMethodNo", query = "SELECT d FROM DtPayment d WHERE d.methodNo = :methodNo"),
    @NamedQuery(name = "DtPayment.findByAmount", query = "SELECT d FROM DtPayment d WHERE d.amount = :amount")})
public class DtPayment implements Serializable {
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
    @Column(name = "service_code", nullable = false, length = 50)
    private String serviceCode;
    @Basic(optional = false)
    @Column(name = "customer_id", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;
    @Basic(optional = false)
    @Column(name = "method_no", nullable = false, length = 50)
    private String methodNo;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    
    @OneToOne(mappedBy="payment")
    private DtBilling bill;

    public DtPayment() {
    }

    public DtPayment(Integer id) {
        this.id = id;
    }

    public DtPayment(Date date, String serviceCode, String customerId, String description, String paymentMethod, String methodNo, double amount) {
//        this.id = id;
        this.date = date;
        this.serviceCode = serviceCode;
        this.customerId = customerId;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.methodNo = methodNo;
        this.amount = amount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMethodNo() {
        return methodNo;
    }

    public void setMethodNo(String methodNo) {
        this.methodNo = methodNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        if (!(object instanceof DtPayment)) {
            return false;
        }
        DtPayment other = (DtPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtPayment[ id=" + id + " ]";
    }
    
}
