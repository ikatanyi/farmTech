package farm.entity;
// Generated Nov 17, 2014 1:31:23 PM by Hibernate Tools 3.6.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DtRoute generated by hbm2java
 */
@Entity
@Table(name="dt_route"
    ,catalog="db_dms"
)
public class DtRoute  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String centerNo;
     private String agentFname;
     private String agentLname;
     private String vehicleNo;
     private String agentPhone;
     private String agentAddress;
     private String agentEmail;
     private String leaderLname;
     private String leaderFname;
     private String leaderPhone;
     private String leaderAddress;
     private String leaderEmail;
     private int memberNo;
     private String altFname;
     private String altLname;
     private String altPhone;
     private String altAddress;
     private String userid;

    public DtRoute() {
    }

    public DtRoute(String name, String centerNo, String agentFname, String agentLname, String vehicleNo, String agentPhone, String agentAddress, String agentEmail, String leaderLname, String leaderFname, String leaderPhone, String leaderAddress, String leaderEmail, int memberNo, String altFname, String altLname, String altPhone, String altAddress, String userid) {
       this.name = name;
       this.centerNo = centerNo;
       this.agentFname = agentFname;
       this.agentLname = agentLname;
       this.vehicleNo = vehicleNo;
       this.agentPhone = agentPhone;
       this.agentAddress = agentAddress;
       this.agentEmail = agentEmail;
       this.leaderLname = leaderLname;
       this.leaderFname = leaderFname;
       this.leaderPhone = leaderPhone;
       this.leaderAddress = leaderAddress;
       this.leaderEmail = leaderEmail;
       this.memberNo = memberNo;
       this.altFname = altFname;
       this.altLname = altLname;
       this.altPhone = altPhone;
       this.altAddress = altAddress;
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

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="center_no", nullable=false, length=20)
    public String getCenterNo() {
        return this.centerNo;
    }
    
    public void setCenterNo(String centerNo) {
        this.centerNo = centerNo;
    }

    
    @Column(name="agent_fname", nullable=false, length=50)
    public String getAgentFname() {
        return this.agentFname;
    }
    
    public void setAgentFname(String agentFname) {
        this.agentFname = agentFname;
    }

    
    @Column(name="agent_lname", nullable=false, length=50)
    public String getAgentLname() {
        return this.agentLname;
    }
    
    public void setAgentLname(String agentLname) {
        this.agentLname = agentLname;
    }

    
    @Column(name="vehicle_no", nullable=false, length=50)
    public String getVehicleNo() {
        return this.vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    
    @Column(name="agent_phone", nullable=false, length=50)
    public String getAgentPhone() {
        return this.agentPhone;
    }
    
    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    
    @Column(name="agent_address", nullable=false, length=50)
    public String getAgentAddress() {
        return this.agentAddress;
    }
    
    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    
    @Column(name="agent_email", nullable=false, length=50)
    public String getAgentEmail() {
        return this.agentEmail;
    }
    
    public void setAgentEmail(String agentEmail) {
        this.agentEmail = agentEmail;
    }

    
    @Column(name="leader_lname", nullable=false, length=50)
    public String getLeaderLname() {
        return this.leaderLname;
    }
    
    public void setLeaderLname(String leaderLname) {
        this.leaderLname = leaderLname;
    }

    
    @Column(name="leader_fname", nullable=false, length=50)
    public String getLeaderFname() {
        return this.leaderFname;
    }
    
    public void setLeaderFname(String leaderFname) {
        this.leaderFname = leaderFname;
    }

    
    @Column(name="leader_phone", nullable=false, length=50)
    public String getLeaderPhone() {
        return this.leaderPhone;
    }
    
    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    
    @Column(name="leader_address", nullable=false, length=50)
    public String getLeaderAddress() {
        return this.leaderAddress;
    }
    
    public void setLeaderAddress(String leaderAddress) {
        this.leaderAddress = leaderAddress;
    }

    
    @Column(name="leader_email", nullable=false, length=50)
    public String getLeaderEmail() {
        return this.leaderEmail;
    }
    
    public void setLeaderEmail(String leaderEmail) {
        this.leaderEmail = leaderEmail;
    }

    
    @Column(name="member_no", nullable=false)
    public int getMemberNo() {
        return this.memberNo;
    }
    
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    
    @Column(name="alt_fname", nullable=false, length=50)
    public String getAltFname() {
        return this.altFname;
    }
    
    public void setAltFname(String altFname) {
        this.altFname = altFname;
    }

    
    @Column(name="alt_lname", nullable=false, length=50)
    public String getAltLname() {
        return this.altLname;
    }
    
    public void setAltLname(String altLname) {
        this.altLname = altLname;
    }

    
    @Column(name="alt_phone", nullable=false, length=50)
    public String getAltPhone() {
        return this.altPhone;
    }
    
    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    
    @Column(name="alt_address", nullable=false, length=50)
    public String getAltAddress() {
        return this.altAddress;
    }
    
    public void setAltAddress(String altAddress) {
        this.altAddress = altAddress;
    }

    
    @Column(name="userid", nullable=false, length=20)
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }




}

