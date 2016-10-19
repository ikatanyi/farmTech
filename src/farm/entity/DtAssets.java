package farm.entity;
// Generated Nov 17, 2014 1:31:23 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtAssets generated by hbm2java
 */
@Entity
@Table(name="dt_assets"
    ,catalog="db_dms"
)
public class DtAssets  implements java.io.Serializable {


     private Integer id;
     private String type;
     private String license;
     private String regno;
     private String size;
     private double amount;
     private String description;
     private String purchaseDate;
     private String userid;

    public DtAssets() {
    }

    public DtAssets(String type, String license, String regno, String size, double amount, String description, String purchaseDate, String userid) {
       this.type = type;
       this.license = license;
       this.regno = regno;
       this.size = size;
       this.amount = amount;
       this.description = description;
       this.purchaseDate = purchaseDate;
       this.userid = userid;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="type", nullable=false, length=50)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="license", nullable=false, length=100)
    public String getLicense() {
        return this.license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }

    
    @Column(name="regno", nullable=false, length=100)
    public String getRegno() {
        return this.regno;
    }
    
    public void setRegno(String regno) {
        this.regno = regno;
    }

    
    @Column(name="size", nullable=false, length=50)
    public String getSize() {
        return this.size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }

    
    @Column(name="amount", nullable=false, precision=22, scale=0)
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    @Column(name="description", nullable=false, length=2000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="purchase_date", nullable=false, length=20)
    public String getPurchaseDate() {
        return this.purchaseDate;
    }
    
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    
    @Column(name="userid", nullable=false, length=50)
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }




}


