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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "v_current_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VCurrentStock.findAll", query = "SELECT v FROM VCurrentStock v"),
    @NamedQuery(name = "VCurrentStock.findById", query = "SELECT v FROM VCurrentStock v WHERE v.id = :id"),
    @NamedQuery(name = "VCurrentStock.findByProductCode", query = "SELECT v FROM VCurrentStock v WHERE v.productCode = :productCode"),
    @NamedQuery(name = "VCurrentStock.findByItemName", query = "SELECT v FROM VCurrentStock v WHERE v.itemName = :itemName"),
    @NamedQuery(name = "VCurrentStock.findByInStock", query = "SELECT v FROM VCurrentStock v WHERE v.inStock = :inStock"),
    @NamedQuery(name = "VCurrentStock.findByOutStock", query = "SELECT v FROM VCurrentStock v WHERE v.outStock = :outStock"),
    @NamedQuery(name = "VCurrentStock.findByAvailable", query = "SELECT v FROM VCurrentStock v WHERE v.available = :available"),
    @NamedQuery(name = "VCurrentStock.findByUnitCost", query = "SELECT v FROM VCurrentStock v WHERE v.unitCost = :unitCost"),
    @NamedQuery(name = "VCurrentStock.findByUnitPrice", query = "SELECT v FROM VCurrentStock v WHERE v.unitPrice = :unitPrice"),
    @NamedQuery(name = "VCurrentStock.findByDescription", query = "SELECT v FROM VCurrentStock v WHERE v.description = :description"),
    @NamedQuery(name = "VCurrentStock.findByWrheLocation", query = "SELECT v FROM VCurrentStock v WHERE v.wrheLocation = :wrheLocation")})
public class VCurrentStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "product_code")
    private String productCode;
    @Basic(optional = false)
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "in_stock")
    private Integer inStock;
    @Column(name = "out_stock")
    private Integer outStock;
    @Column(name = "available")
    private Integer available;
    @Basic(optional = false)
    @Column(name = "unit_cost")
    private double unitCost;
    @Basic(optional = false)
    @Column(name = "unit_price")
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "wrhe_location")
    private String wrheLocation;

    public VCurrentStock() {
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

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getOutStock() {
        return outStock;
    }

    public void setOutStock(Integer outStock) {
        this.outStock = outStock;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
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

    public String getWrheLocation() {
        return wrheLocation;
    }

    public void setWrheLocation(String wrheLocation) {
        this.wrheLocation = wrheLocation;
    }
    
}
