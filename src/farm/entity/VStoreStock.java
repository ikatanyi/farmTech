package farm.entity;
// Generated Nov 17, 2014 1:31:23 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VStoreStock generated by hbm2java
 */
@Entity
@Table(name="v_store_stock"
    ,catalog="db_dms"
)
public class VStoreStock  implements java.io.Serializable {


     private String productName;
     private Integer availableStock;
     private Integer inStock;
     private Integer outStock;

    public VStoreStock() {
    }

	
    public VStoreStock(String productName) {
        this.productName = productName;
    }
    public VStoreStock(String productName, Integer availableStock, Integer inStock, Integer outStock) {
       this.productName = productName;
       this.availableStock = availableStock;
       this.inStock = inStock;
       this.outStock = outStock;
    }
   
     @Id 

    
    @Column(name="ProductName", nullable=false)
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    @Column(name="Available_Stock")
    public Integer getAvailableStock() {
        return this.availableStock;
    }
    
    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    
    @Column(name="In_Stock")
    public Integer getInStock() {
        return this.inStock;
    }
    
    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    
    @Column(name="Out_Stock")
    public Integer getOutStock() {
        return this.outStock;
    }
    
    public void setOutStock(Integer outStock) {
        this.outStock = outStock;
    }




}


