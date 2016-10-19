package farm.entity;
// Generated Nov 17, 2014 1:31:23 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtSecurities generated by hbm2java
 */
@Entity
@Table(name="dt_securities"
    ,catalog="db_dms"
)
public class DtSecurities  implements java.io.Serializable {


     private Integer id;
     private String farmerId;
     private String type;
     private String description;
     private String serial;
     private double value;
     private String date;
     private String attendantId;

    public DtSecurities() {
    }

    public DtSecurities(String farmerId, String type, String description, String serial, double value, String date, String attendantId) {
       this.farmerId = farmerId;
       this.type = type;
       this.description = description;
       this.serial = serial;
       this.value = value;
       this.date = date;
       this.attendantId = attendantId;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="farmerId", nullable=false, length=100)
    public String getFarmerId() {
        return this.farmerId;
    }
    
    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    
    @Column(name="type", nullable=false, length=100)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="description", nullable=false, length=100)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="serial", nullable=false, length=100)
    public String getSerial() {
        return this.serial;
    }
    
    public void setSerial(String serial) {
        this.serial = serial;
    }

    
    @Column(name="value", nullable=false, precision=22, scale=0)
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }

    
    @Column(name="date", nullable=false, length=20)
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    
    @Column(name="attendant_id", nullable=false, length=50)
    public String getAttendantId() {
        return this.attendantId;
    }
    
    public void setAttendantId(String attendantId) {
        this.attendantId = attendantId;
    }




}

