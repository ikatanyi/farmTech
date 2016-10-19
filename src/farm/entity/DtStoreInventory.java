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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_store_inventory")

@SecondaryTable(name="dt_store_stock", pkJoinColumns={
        @PrimaryKeyJoinColumn(name="Entry_Code", referencedColumnName="med_code")
    })
@org.hibernate.annotations.Table(
   appliesTo="dt_store_stock",
   fetch=FetchMode.SELECT,
   optional=false
)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtStoreInventory.findAll", query = "SELECT d FROM DtStoreInventory d"),
    @NamedQuery(name = "DtStoreInventory.findByMedCode", query = "SELECT d FROM DtStoreInventory d WHERE d.medCode = :medCode"),
    @NamedQuery(name = "DtStoreInventory.findBySName", query = "SELECT d FROM DtStoreInventory d WHERE d.sName = :sName"),
    @NamedQuery(name = "DtStoreInventory.findByDeliveryNo", query = "SELECT d FROM DtStoreInventory d WHERE d.deliveryNo = :deliveryNo"),
    @NamedQuery(name = "DtStoreInventory.findByCategory", query = "SELECT d FROM DtStoreInventory d WHERE d.category = :category"),
    @NamedQuery(name = "DtStoreInventory.findByItemName", query = "SELECT d FROM DtStoreInventory d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "DtStoreInventory.findByBatchNo", query = "SELECT d FROM DtStoreInventory d WHERE d.batchNo = :batchNo"),
    @NamedQuery(name = "DtStoreInventory.findByNomenclature", query = "SELECT d FROM DtStoreInventory d WHERE d.nomenclature = :nomenclature"),
    @NamedQuery(name = "DtStoreInventory.findByExpDate", query = "SELECT d FROM DtStoreInventory d WHERE d.expDate = :expDate"),
    @NamedQuery(name = "DtStoreInventory.findByStock", query = "SELECT d FROM DtStoreInventory d WHERE d.stock = :stock"),
    @NamedQuery(name = "DtStoreInventory.findByTradeprice", query = "SELECT d FROM DtStoreInventory d WHERE d.tradeprice = :tradeprice"),
    @NamedQuery(name = "DtStoreInventory.findByRetailprice", query = "SELECT d FROM DtStoreInventory d WHERE d.retailprice = :retailprice"),
    @NamedQuery(name = "DtStoreInventory.findByPurchaseDate", query = "SELECT d FROM DtStoreInventory d WHERE d.purchaseDate = :purchaseDate")})
public class DtStoreInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "med_code", nullable = false)
    private Integer medCode;
    @Basic(optional = false)
    @Column(name = "SName", nullable = false, length = 50)
    private String sName;
    @Basic(optional = false)
    @Column(name = "Delivery_No", nullable = false, length = 50)
    private String deliveryNo;
    @Basic(optional = false)
    @Column(name = "Category", nullable = false, length = 50)
    private String category;
    @Basic(optional = false)
    @Column(name = "Item_Name", nullable = false, length = 50)
    private String itemName;
    @Basic(optional = false)
    @Column(name = "Batch_No", nullable = false, length = 20)
    private String batchNo;
    @Basic(optional = false)
    @Column(name = "Nomenclature", nullable = false, length = 50)
    private String nomenclature;
    @Basic(optional = false)
    @Column(name = "Exp_Date", nullable = false, length = 20)
    private String expDate;
    @Basic(optional = false)
    @Column(name = "Stock", nullable = false)
    private int stock;
    @Basic(optional = false)
    @Column(name = "Trade_price", nullable = false)
    private double tradeprice;
    @Basic(optional = false)
    @Column(name = "Retail_price", nullable = false)
    private double retailprice;
    @Basic(optional = false)
    @Column(name = "Purchase_Date", nullable = false, length = 20)
    private String purchaseDate;
    
    @Column(table = "dt_store_stock",name = "ProductName", nullable = false, length = 100)
    private String productName;
    @Basic(optional = false)
    @Column(table = "dt_store_stock",name = "Batch_No", nullable = false, length = 20)
    private String batch;
    @Basic(optional = false)
    @Column(table = "dt_store_stock",name = "In_Stock", nullable = false)
    private int inStock;
    @Basic(optional = false)
    @Column(table = "dt_store_stock",name = "Out_Stock", nullable = false)
    private int outStock;
    @Basic(optional = false)
    @Column(table = "dt_store_stock",name = "TransDate", nullable = false, length = 20)
    private String transDate;

    public DtStoreInventory() {
    }

    public DtStoreInventory(Integer medCode) {
        this.medCode = medCode;
    }

    public DtStoreInventory(String sName, String deliveryNo, String category, String itemName, String batchNo, String nomenclature, String expDate, int stock, double tradeprice, double retailprice, String purchaseDate, String productName, String batch, int inStock, int outStock, String transDate) {
//        this.medCode = medCode;
        this.sName = sName;
        this.deliveryNo = deliveryNo;
        this.category = category;
        this.itemName = itemName;
        this.batchNo = batchNo;
        this.nomenclature = nomenclature;
        this.expDate = expDate;
        this.stock = stock;
        this.tradeprice = tradeprice;
        this.retailprice = retailprice;
        this.purchaseDate = purchaseDate;
        
        this.productName = productName;
        this.batch = batch;
        this.inStock = inStock;
        this.outStock = outStock;
        this.transDate = transDate;
    }

    public Integer getMedCode() {
        return medCode;
    }

    public void setMedCode(Integer medCode) {
        this.medCode = medCode;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getTradeprice() {
        return tradeprice;
    }

    public void setTradeprice(double tradeprice) {
        this.tradeprice = tradeprice;
    }

    public double getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(double retailprice) {
        this.retailprice = retailprice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getOutStock() {
        return outStock;
    }

    public void setOutStock(int outStock) {
        this.outStock = outStock;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medCode != null ? medCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtStoreInventory)) {
            return false;
        }
        DtStoreInventory other = (DtStoreInventory) object;
        if ((this.medCode == null && other.medCode != null) || (this.medCode != null && !this.medCode.equals(other.medCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtStoreInventory[ medCode=" + medCode + " ]";
    }
    
}
