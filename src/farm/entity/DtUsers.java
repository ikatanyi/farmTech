/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtUsers.findAll", query = "SELECT d FROM DtUsers d"),
    @NamedQuery(name = "DtUsers.findByState", query = "SELECT d FROM DtUsers d WHERE d.state = :state"),
    @NamedQuery(name = "DtUsers.findByFname", query = "SELECT d FROM DtUsers d WHERE d.fname = :fname"),
    @NamedQuery(name = "DtUsers.findByLname", query = "SELECT d FROM DtUsers d WHERE d.lname = :lname"),
    @NamedQuery(name = "DtUsers.findByGender", query = "SELECT d FROM DtUsers d WHERE d.gender = :gender"),
    @NamedQuery(name = "DtUsers.findByIdNo", query = "SELECT d FROM DtUsers d WHERE d.idNo = :idNo"),
    @NamedQuery(name = "DtUsers.findByAddress", query = "SELECT d FROM DtUsers d WHERE d.address = :address"),
    @NamedQuery(name = "DtUsers.findByPhone", query = "SELECT d FROM DtUsers d WHERE d.phone = :phone"),
    @NamedQuery(name = "DtUsers.findByEmail", query = "SELECT d FROM DtUsers d WHERE d.email = :email"),
    @NamedQuery(name = "DtUsers.findByDob", query = "SELECT d FROM DtUsers d WHERE d.dob = :dob"),
    @NamedQuery(name = "DtUsers.findByUsername", query = "SELECT d FROM DtUsers d WHERE d.username = :username"),
    @NamedQuery(name = "DtUsers.findBySpeciality", query = "SELECT d FROM DtUsers d WHERE d.speciality = :speciality"),
    @NamedQuery(name = "DtUsers.findByLooknFeel", query = "SELECT d FROM DtUsers d WHERE d.looknFeel = :looknFeel"),
    @NamedQuery(name = "DtUsers.findByPassword", query = "SELECT d FROM DtUsers d WHERE d.password = :password"),
    @NamedQuery(name = "DtUsers.findByDate", query = "SELECT d FROM DtUsers d WHERE d.date = :date")})
public class DtUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "State")
    private String state;
    @Basic(optional = false)
    @Column(name = "Fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "Lname")
    private String lname;
    @Basic(optional = false)
    @Column(name = "Gender")
    private String gender;
    @Id
    @Basic(optional = false)
    @Column(name = "IdNo")
    private String idNo;
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "DOB")
    private String dob;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @Column(name = "Speciality")
    private String speciality;
    @Basic(optional = false)
    @Column(name = "Look_n_Feel")
    private String looknFeel;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "Date")
    private String date;
    
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private DtUserprivileges privileges;

    public DtUserprivileges getPrivileges() {
        return privileges;
    }

    public void setPrivileges(DtUserprivileges privileges) {
        this.privileges = privileges;
    }

    public DtUsers() {
    }

    public DtUsers(String idNo) {
        this.idNo = idNo;
    }

    public DtUsers(String idNo, String state, String fname, String lname, String gender, String address, String phone, String email, String dob, String username, String speciality, String looknFeel, String password, String date) {
        this.idNo = idNo;
        this.state = state;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.speciality = speciality;
        this.looknFeel = looknFeel;
        this.password = password;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLooknFeel() {
        return looknFeel;
    }

    public void setLooknFeel(String looknFeel) {
        this.looknFeel = looknFeel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNo != null ? idNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtUsers)) {
            return false;
        }
        DtUsers other = (DtUsers) object;
        if ((this.idNo == null && other.idNo != null) || (this.idNo != null && !this.idNo.equals(other.idNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtUsers[ idNo=" + idNo + " ]";
    }
    
}
