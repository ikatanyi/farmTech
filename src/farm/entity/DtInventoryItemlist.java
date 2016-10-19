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
@Table(name = "dt_inventory_itemlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtInventoryItemlist.findAll", query = "SELECT d FROM DtInventoryItemlist d"),
    @NamedQuery(name = "DtInventoryItemlist.findById", query = "SELECT d FROM DtInventoryItemlist d WHERE d.id = :id"),
    @NamedQuery(name = "DtInventoryItemlist.findByProductCode", query = "SELECT d FROM DtInventoryItemlist d WHERE d.productCode = :productCode"),
    @NamedQuery(name = "DtInventoryItemlist.findByItemName", query = "SELECT d FROM DtInventoryItemlist d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "DtInventoryItemlist.findByCategory", query = "SELECT d FROM DtInventoryItemlist d WHERE d.category = :category"),
    @NamedQuery(name = "DtInventoryItemlist.findByPackage1", query = "SELECT d FROM DtInventoryItemlist d WHERE d.package1 = :package1"),
    @NamedQuery(name = "DtInventoryItemlist.findByUom", query = "SELECT d FROM DtInventoryItemlist d WHERE d.uom = :uom"),
    @NamedQuery(name = "DtInventoryItemlist.findBySupplierCode", query = "SELECT d FROM DtInventoryItemlist d WHERE d.supplierCode = :supplierCode"),
    @NamedQuery(name = "DtInventoryItemlist.findByOrderLimit", query = "SELECT d FROM DtInventoryItemlist d WHERE d.orderLimit = :orderLimit"),
    @NamedQuery(name = "DtInventoryItemlist.findByStock", query = "SELECT d FROM DtInventoryItemlist d WHERE d.stock = :stock"),
    @NamedQuery(name = "DtInventoryItemlist.findByPoCost", query = "SELECT d FROM DtInventoryItemlist d WHERE d.poCost = :poCost"),
    @NamedQuery(name = "DtInventoryItemlist.findByUnitCost", query = "SELECT d FROM DtInventoryItemlist d WHERE d.unitCost = :unitCost"),
    @NamedQuery(name = "DtInventoryItemlist.findByUnitPrice", query = "SELECT d FROM DtInventoryItemlist d WHERE d.unitPrice = :unitPrice"),
    @NamedQuery(name = "DtInventoryItemlist.findByModel", query = "SELECT d FROM DtInventoryItemlist d WHERE d.model = :model"),
    @NamedQuery(name = "DtInventoryItemlist.findByEngPin", query = "SELECT d FROM DtInventoryItemlist d WHERE d.engPin = :engPin"),
    @NamedQuery(name = "DtInventoryItemlist.findByRevision", query = "SELECT d FROM DtInventoryItemlist d WHERE d.revision = :revision"),
    @NamedQuery(name = "DtInventoryItemlist.findByPackUnits", query = "SELECT d FROM DtInventoryItemlist d WHERE d.packUnits = :packUnits"),
    @NamedQuery(name = "DtInventoryItemlist.findByPallet", query = "SELECT d FROM DtInventoryItemlist d WHERE d.pallet = :pallet"),
    @NamedQuery(name = "DtInventoryItemlist.findByNetWt", query = "SELECT d FROM DtInventoryItemlist d WHERE d.netWt = :netWt"),
    @NamedQuery(name = "DtInventoryItemlist.findByGrossWt", query = "SELECT d FROM DtInventoryItemlist d WHERE d.grossWt = :grossWt"),
    @NamedQuery(name = "DtInventoryItemlist.findByPicName", query = "SELECT d FROM DtInventoryItemlist d WHERE d.picName = :picName"),
    @NamedQuery(name = "DtInventoryItemlist.findByLeadDays", query = "SELECT d FROM DtInventoryItemlist d WHERE d.leadDays = :leadDays"),
    @NamedQuery(name = "DtInventoryItemlist.findByEcoOrder", query = "SELECT d FROM DtInventoryItemlist d WHERE d.ecoOrder = :ecoOrder"),
    @NamedQuery(name = "DtInventoryItemlist.findByMonthlyDemand", query = "SELECT d FROM DtInventoryItemlist d WHERE d.monthlyDemand = :monthlyDemand"),
    @NamedQuery(name = "DtInventoryItemlist.findByVat", query = "SELECT d FROM DtInventoryItemlist d WHERE d.vat = :vat"),
    @NamedQuery(name = "DtInventoryItemlist.findByDiscount", query = "SELECT d FROM DtInventoryItemlist d WHERE d.discount = :discount"),
    @NamedQuery(name = "DtInventoryItemlist.findByDescription", query = "SELECT d FROM DtInventoryItemlist d WHERE d.description = :description"),
    @NamedQuery(name = "DtInventoryItemlist.findByDate", query = "SELECT d FROM DtInventoryItemlist d WHERE d.date = :date")})
public class DtInventoryItemlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_code", nullable = false, length = 20)
    private String productCode;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Basic(optional = false)
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @Basic(optional = false)
    @Column(name = "package", nullable = false, length = 20)
    private String package1;
    @Basic(optional = false)
    @Column(name = "uom", nullable = false, length = 20)
    private String uom;
    @Basic(optional = false)
    @Column(name = "supplier_code", nullable = false, length = 25)
    private String supplierCode;
    @Basic(optional = false)
    @Column(name = "order_limit", nullable = false)
    private int orderLimit;
    @Basic(optional = false)
    @Column(name = "stock", nullable = false)
    private double stock;
    @Basic(optional = false)
    @Column(name = "po_cost", nullable = false)
    private double poCost;
    @Basic(optional = false)
    @Column(name = "unit_cost", nullable = false)
    private double unitCost;
    @Basic(optional = false)
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "model", nullable = false, length = 20)
    private String model;
    @Basic(optional = false)
    @Column(name = "eng_pin", nullable = false, length = 20)
    private String engPin;
    @Basic(optional = false)
    @Column(name = "revision", nullable = false, length = 50)
    private String revision;
    @Basic(optional = false)
    @Column(name = "pack_units", nullable = false)
    private int packUnits;
    @Basic(optional = false)
    @Column(name = "pallet", nullable = false, length = 20)
    private String pallet;
    @Basic(optional = false)
    @Column(name = "net_wt", nullable = false)
    private double netWt;
    @Basic(optional = false)
    @Column(name = "gross_wt", nullable = false)
    private double grossWt;
    @Basic(optional = false)
    @Column(name = "pic_name", nullable = false, length = 50)
    private String picName;
    @Basic(optional = false)
    @Column(name = "lead_days", nullable = false)
    private int leadDays;
    @Basic(optional = false)
    @Column(name = "eco_order", nullable = false)
    private double ecoOrder;
    @Basic(optional = false)
    @Column(name = "monthly_demand", nullable = false)
    private double monthlyDemand;
    @Basic(optional = false)
    @Column(name = "vat", nullable = false)
    private double vat;
    @Basic(optional = false)
    @Column(name = "discount", nullable = false)
    private double discount;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtInventoryItemlist() {
    }

    public DtInventoryItemlist(Integer id) {
        this.id = id;
    }

    public DtInventoryItemlist(String productCode, String itemName, String category, String package1, String uom, String supplierCode, int orderLimit, double stock, double poCost, double unitCost, double unitPrice, String model, String engPin, String revision, int packUnits, String pallet, double netWt, double grossWt, String picName, int leadDays, double ecoOrder, double monthlyDemand, double vat, double discount, String description, Date date) {
        this.id = id;
        this.productCode = productCode;
        this.itemName = itemName;
        this.category = category;
        this.package1 = package1;
        this.uom = uom;
        this.supplierCode = supplierCode;
        this.orderLimit = orderLimit;
        this.stock = stock;
        this.poCost = poCost;
        this.unitCost = unitCost;
        this.unitPrice = unitPrice;
        this.model = model;
        this.engPin = engPin;
        this.revision = revision;
        this.packUnits = packUnits;
        this.pallet = pallet;
        this.netWt = netWt;
        this.grossWt = grossWt;
        this.picName = picName;
        this.leadDays = leadDays;
        this.ecoOrder = ecoOrder;
        this.monthlyDemand = monthlyDemand;
        this.vat = vat;
        this.discount = discount;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPackage1() {
        return package1;
    }

    public void setPackage1(String package1) {
        this.package1 = package1;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public int getOrderLimit() {
        return orderLimit;
    }

    public void setOrderLimit(int orderLimit) {
        this.orderLimit = orderLimit;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPoCost() {
        return poCost;
    }

    public void setPoCost(double poCost) {
        this.poCost = poCost;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngPin() {
        return engPin;
    }

    public void setEngPin(String engPin) {
        this.engPin = engPin;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public int getPackUnits() {
        return packUnits;
    }

    public void setPackUnits(int packUnits) {
        this.packUnits = packUnits;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public double getNetWt() {
        return netWt;
    }

    public void setNetWt(double netWt) {
        this.netWt = netWt;
    }

    public double getGrossWt() {
        return grossWt;
    }

    public void setGrossWt(double grossWt) {
        this.grossWt = grossWt;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public int getLeadDays() {
        return leadDays;
    }

    public void setLeadDays(int leadDays) {
        this.leadDays = leadDays;
    }

    public double getEcoOrder() {
        return ecoOrder;
    }

    public void setEcoOrder(double ecoOrder) {
        this.ecoOrder = ecoOrder;
    }

    public double getMonthlyDemand() {
        return monthlyDemand;
    }

    public void setMonthlyDemand(double monthlyDemand) {
        this.monthlyDemand = monthlyDemand;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof DtInventoryItemlist)) {
            return false;
        }
        DtInventoryItemlist other = (DtInventoryItemlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtInventoryItemlist[ id=" + id + " ]";
    }
    
}
