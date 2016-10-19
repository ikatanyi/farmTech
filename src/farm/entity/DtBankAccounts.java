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
@Table(name = "dt_bank_accounts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtBankAccounts.findAll", query = "SELECT d FROM DtBankAccounts d"),
    @NamedQuery(name = "DtBankAccounts.findById", query = "SELECT d FROM DtBankAccounts d WHERE d.id = :id"),
    @NamedQuery(name = "DtBankAccounts.findByDeptId", query = "SELECT d FROM DtBankAccounts d WHERE d.deptId = :deptId"),
    @NamedQuery(name = "DtBankAccounts.findByBankName", query = "SELECT d FROM DtBankAccounts d WHERE d.bankName = :bankName"),
    @NamedQuery(name = "DtBankAccounts.findByAccount", query = "SELECT d FROM DtBankAccounts d WHERE d.account = :account")})
public class DtBankAccounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dept_id", nullable = false, length = 20)
    private String deptId;
    @Basic(optional = false)
    @Column(name = "bank_name", nullable = false, length = 100)
    private String bankName;
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 100)
    private String account;

    public DtBankAccounts() {
    }

    public DtBankAccounts(Integer id) {
        this.id = id;
    }

    public DtBankAccounts(String deptId, String bankName, String account) {
//        this.id = id;
        this.deptId = deptId;
        this.bankName = bankName;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
        if (!(object instanceof DtBankAccounts)) {
            return false;
        }
        DtBankAccounts other = (DtBankAccounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtBankAccounts[ id=" + id + " ]";
    }
    
}
