/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farm.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "v_expiring_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VExpiringStock.findAll", query = "SELECT v FROM VExpiringStock v"),
    @NamedQuery(name = "VExpiringStock.findById", query = "SELECT v FROM VExpiringStock v WHERE v.id = :id"),
    @NamedQuery(name = "VExpiringStock.findByProductCode", query = "SELECT v FROM VExpiringStock v WHERE v.productCode = :productCode"),
    @NamedQuery(name = "VExpiringStock.findByItemName", query = "SELECT v FROM VExpiringStock v WHERE v.itemName = :itemName"),
    @NamedQuery(name = "VExpiringStock.findByAvailable", query = "SELECT v FROM VExpiringStock v WHERE v.available = :available"),
    @NamedQuery(name = "VExpiringStock.findByUnitCost", query = "SELECT v FROM VExpiringStock v WHERE v.unitCost = :unitCost"),
    @NamedQuery(name = "VExpiringStock.findByUnitPrice", query = "SELECT v FROM VExpiringStock v WHERE v.unitPrice = :unitPrice"),
    @NamedQuery(name = "VExpiringStock.findByDescription", query = "SELECT v FROM VExpiringStock v WHERE v.description = :description"),
    @NamedQuery(name = "VExpiringStock.findByLocation", query = "SELECT v FROM VExpiringStock v WHERE v.location = :location"),
    @NamedQuery(name = "VExpiringStock.findByExpDate", query = "SELECT v FROM VExpiringStock v WHERE v.expDate = :expDate"),
    @NamedQuery(name = "VExpiringStock.findByPurchaseDate", query = "SELECT v FROM VExpiringStock v WHERE v.purchaseDate = :purchaseDate")})
public class VExpiringStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Column(name = "available")
    private BigInteger available;
    @Basic(optional = false)
    @Column(name = "unit_cost", nullable = false)
    private double unitCost;
    @Basic(optional = false)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(name = "location", nullable = false, length = 50)
    private String location;
    @Basic(optional = false)
    @Column(name = "exp_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expDate;
    @Basic(optional = false)
    @Column(name = "purchase_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    public VExpiringStock() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigInteger getAvailable() {
        return available;
    }

    public void setAvailable(BigInteger available) {
        this.available = available;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
}
