package farm.entity;
// Generated Nov 17, 2014 1:31:23 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtGuarantors generated by hbm2java
 */
@Entity
@Table(name="dt_guarantors"
    ,catalog="db_dms"
)
public class DtGuarantors  implements java.io.Serializable {


     private Integer id;
     private String guanteeId;
     private String guarantorId;
     private String attendantId;
     private String date;

    public DtGuarantors() {
    }

    public DtGuarantors(String guanteeId, String guarantorId, String attendantId, String date) {
       this.guanteeId = guanteeId;
       this.guarantorId = guarantorId;
       this.attendantId = attendantId;
       this.date = date;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Guantee_id", nullable=false, length=100)
    public String getGuanteeId() {
        return this.guanteeId;
    }
    
    public void setGuanteeId(String guanteeId) {
        this.guanteeId = guanteeId;
    }

    
    @Column(name="Guarantor_id", nullable=false, length=100)
    public String getGuarantorId() {
        return this.guarantorId;
    }
    
    public void setGuarantorId(String guarantorId) {
        this.guarantorId = guarantorId;
    }

    
    @Column(name="Attendant_id", nullable=false, length=100)
    public String getAttendantId() {
        return this.attendantId;
    }
    
    public void setAttendantId(String attendantId) {
        this.attendantId = attendantId;
    }

    
    @Column(name="Date", nullable=false, length=20)
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }




}

