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
@Table(name = "stock_to_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockToOrder.findAll", query = "SELECT s FROM StockToOrder s"),
    @NamedQuery(name = "StockToOrder.findById", query = "SELECT s FROM StockToOrder s WHERE s.id = :id"),
    @NamedQuery(name = "StockToOrder.findByProductCode", query = "SELECT s FROM StockToOrder s WHERE s.productCode = :productCode"),
    @NamedQuery(name = "StockToOrder.findByItemName", query = "SELECT s FROM StockToOrder s WHERE s.itemName = :itemName"),
    @NamedQuery(name = "StockToOrder.findByAvailable", query = "SELECT s FROM StockToOrder s WHERE s.available = :available"),
    @NamedQuery(name = "StockToOrder.findByUnitCost", query = "SELECT s FROM StockToOrder s WHERE s.unitCost = :unitCost"),
    @NamedQuery(name = "StockToOrder.findByUnitPrice", query = "SELECT s FROM StockToOrder s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "StockToOrder.findByDescription", query = "SELECT s FROM StockToOrder s WHERE s.description = :description"),
    @NamedQuery(name = "StockToOrder.findByLocation", query = "SELECT s FROM StockToOrder s WHERE s.location = :location"),
    @NamedQuery(name = "StockToOrder.findByPurchaseDate", query = "SELECT s FROM StockToOrder s WHERE s.purchaseDate = :purchaseDate"),
    @NamedQuery(name = "StockToOrder.findByOlimit", query = "SELECT s FROM StockToOrder s WHERE s.olimit = :olimit")})
public class StockToOrder implements Serializable {
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
    @Column(name = "purchase_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    @Basic(optional = false)
    @Column(name = "Olimit", nullable = false)
    private int olimit;

    public StockToOrder() {
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getOlimit() {
        return olimit;
    }

    public void setOlimit(int olimit) {
        this.olimit = olimit;
    }
    
}
