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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtInventory.findAll", query = "SELECT d FROM DtInventory d"),
    @NamedQuery(name = "DtInventory.findById", query = "SELECT d FROM DtInventory d WHERE d.id = :id"),
    @NamedQuery(name = "DtInventory.findByItemId", query = "SELECT d FROM DtInventory d WHERE d.itemId = :itemId"),
    @NamedQuery(name = "DtInventory.findByDescription", query = "SELECT d FROM DtInventory d WHERE d.description = :description"),
    @NamedQuery(name = "DtInventory.findBySerialNo", query = "SELECT d FROM DtInventory d WHERE d.serialNo = :serialNo"),
    @NamedQuery(name = "DtInventory.findByItemName", query = "SELECT d FROM DtInventory d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "DtInventory.findByWrheLocation", query = "SELECT d FROM DtInventory d WHERE d.wrheLocation = :wrheLocation"),
    @NamedQuery(name = "DtInventory.findByReference", query = "SELECT d FROM DtInventory d WHERE d.reference = :reference"),
    @NamedQuery(name = "DtInventory.findByExpDate", query = "SELECT d FROM DtInventory d WHERE d.expDate = :expDate"),
    @NamedQuery(name = "DtInventory.findByUnits", query = "SELECT d FROM DtInventory d WHERE d.units = :units"),
    @NamedQuery(name = "DtInventory.findByUnitCost", query = "SELECT d FROM DtInventory d WHERE d.unitCost = :unitCost"),
    @NamedQuery(name = "DtInventory.findByUnitPrice", query = "SELECT d FROM DtInventory d WHERE d.unitPrice = :unitPrice"),
    @NamedQuery(name = "DtInventory.findByDate", query = "SELECT d FROM DtInventory d WHERE d.date = :date")})
public class DtInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "item_id", nullable = false, length = 50)
    private String itemId;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @Basic(optional = false)
    @Column(name = "serial_no", nullable = false, length = 50)
    private String serialNo;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Basic(optional = false)
    @Column(name = "wrhe_location", nullable = false, length = 50)
    private String wrheLocation;
    @Basic(optional = false)
    @Column(name = "reference", nullable = false, length = 50)
    private String reference;
    @Basic(optional = false)
    @Column(name = "exp_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expDate;
    @Basic(optional = false)
    @Column(name = "units", nullable = false)
    private int units;
    @Basic(optional = false)
    @Column(name = "unit_cost", nullable = false)
    private double unitCost;
    @Basic(optional = false)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;    
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_stock_code", referencedColumnName="id")
    private DtStockMovement stock;

    public DtInventory() {
    }

    public DtInventory(Integer id) {
        this.id = id;
    }

    public DtInventory(String itemId, String description, String serialNo, String itemName, String wrheLocation, String reference, Date expDate, int units, double unitCost, double unitPrice, Date date) {
//        this.id = id;
        this.itemId = itemId;
        this.description = description;
        this.serialNo = serialNo;
        this.itemName = itemName;
        this.wrheLocation = wrheLocation;
        this.reference = reference;
        this.expDate = expDate;
        this.units = units;
        this.unitCost = unitCost;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getWrheLocation() {
        return wrheLocation;
    }

    public void setWrheLocation(String wrheLocation) {
        this.wrheLocation = wrheLocation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DtStockMovement getStock() {
        return stock;
    }

    public void setStock(DtStockMovement stock) {
        this.stock = stock;
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
        if (!(object instanceof DtInventory)) {
            return false;
        }
        DtInventory other = (DtInventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtInventory[ id=" + id + " ]";
    }
    
}
