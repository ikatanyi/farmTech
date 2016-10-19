/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_salestrx")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtSalestrx.findAll", query = "SELECT d FROM DtSalestrx d"),
    @NamedQuery(name = "DtSalestrx.findById", query = "SELECT d FROM DtSalestrx d WHERE d.id = :id"),
    @NamedQuery(name = "DtSalestrx.findBySaleNo", query = "SELECT d FROM DtSalestrx d WHERE d.saleNo = :saleNo"),
    @NamedQuery(name = "DtSalestrx.findByServiceCode", query = "SELECT d FROM DtSalestrx d WHERE d.serviceCode = :serviceCode"),
    @NamedQuery(name = "DtSalestrx.findByProductCode", query = "SELECT d FROM DtSalestrx d WHERE d.productCode = :productCode"),
    @NamedQuery(name = "DtSalestrx.findByUnits", query = "SELECT d FROM DtSalestrx d WHERE d.units = :units"),
    @NamedQuery(name = "DtSalestrx.findByAmount", query = "SELECT d FROM DtSalestrx d WHERE d.amount = :amount"),
    @NamedQuery(name = "DtSalestrx.findByUserCode", query = "SELECT d FROM DtSalestrx d WHERE d.userCode = :userCode"),
    @NamedQuery(name = "DtSalestrx.findByDate", query = "SELECT d FROM DtSalestrx d WHERE d.date = :date")})
public class DtSalestrx implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "sale_no", nullable = false, length = 20)
    private String saleNo;
    @Basic(optional = false)
    @Column(name = "service_code", nullable = false, length = 20)
    private String serviceCode;
    @Basic(optional = false)
    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;
    @Basic(optional = false)
    @Column(name = "units", nullable = false)
    private int units;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "user_code", nullable = false, length = 20)
    private String userCode;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="out_stock_code", referencedColumnName="id")
    private DtStockMovement sale_stock;

    public DtSalestrx() {
    }

    public DtSalestrx(Integer id) {
        this.id = id;
    }

    public DtSalestrx(String saleNo, String serviceCode, String productCode, int units, double amount, String userCode, Date date) {
//        this.id = id;
        this.saleNo = saleNo;
        this.serviceCode = serviceCode;
        this.productCode = productCode;
        this.units = units;
        this.amount = amount;
        this.userCode = userCode;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Column(name = "sale_Id")
    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public DtStockMovement getSale_stock() {
        return sale_stock;
    }

    public void setSale_stock(DtStockMovement sale_stock) {
        this.sale_stock = sale_stock;
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
        if (!(object instanceof DtSalestrx)) {
            return false;
        }
        DtSalestrx other = (DtSalestrx) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtSalestrx[ id=" + id + " ]";
    }
    
}
