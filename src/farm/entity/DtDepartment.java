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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtDepartment.findAll", query = "SELECT d FROM DtDepartment d"),
    @NamedQuery(name = "DtDepartment.findById", query = "SELECT d FROM DtDepartment d WHERE d.id = :id"),
    @NamedQuery(name = "DtDepartment.findByCode", query = "SELECT d FROM DtDepartment d WHERE d.code = :code"),
    @NamedQuery(name = "DtDepartment.findByDeptName", query = "SELECT d FROM DtDepartment d WHERE d.deptName = :deptName"),
    @NamedQuery(name = "DtDepartment.findByTaxId", query = "SELECT d FROM DtDepartment d WHERE d.taxId = :taxId"),
    @NamedQuery(name = "DtDepartment.findByAdmin", query = "SELECT d FROM DtDepartment d WHERE d.admin = :admin"),
    @NamedQuery(name = "DtDepartment.findByPhone", query = "SELECT d FROM DtDepartment d WHERE d.phone = :phone"),
    @NamedQuery(name = "DtDepartment.findByAddress", query = "SELECT d FROM DtDepartment d WHERE d.address = :address")})
public class DtDepartment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 20)
    private String code;
    @Basic(optional = false)
    @Column(name = "dept_name", nullable = false, length = 100)
    private String deptName;
    @Basic(optional = false)
    @Column(name = "tax_id", nullable = false, length = 50)
    private String taxId;
    @Basic(optional = false)
    @Column(name = "admin", nullable = false, length = 50)
    private String admin;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    public DtDepartment() {
    }

    public DtDepartment(Integer id) {
        this.id = id;
    }

    public DtDepartment(String code, String deptName, String taxId, String admin, String phone, String address) {
//        this.id = id;
        this.code = code;
        this.deptName = deptName;
        this.taxId = taxId;
        this.admin = admin;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(object instanceof DtDepartment)) {
            return false;
        }
        DtDepartment other = (DtDepartment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtDepartment[ id=" + id + " ]";
    }
    
}
