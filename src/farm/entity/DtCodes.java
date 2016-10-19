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
@Table(name = "dt_codes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtCodes.findAll", query = "SELECT d FROM DtCodes d"),
    @NamedQuery(name = "DtCodes.findById", query = "SELECT d FROM DtCodes d WHERE d.id = :id"),
    @NamedQuery(name = "DtCodes.findByType", query = "SELECT d FROM DtCodes d WHERE d.type = :type"),
    @NamedQuery(name = "DtCodes.findByDescription", query = "SELECT d FROM DtCodes d WHERE d.description = :description"),
    @NamedQuery(name = "DtCodes.findByCode", query = "SELECT d FROM DtCodes d WHERE d.code = :code")})
public class DtCodes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Type", nullable = false, length = 7)
    private String type;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    public DtCodes() {
    }

    public DtCodes(Integer id) {
        this.id = id;
    }

    public DtCodes(String type, String description, String code) {
//        this.id = id;
        this.type = type;
        this.description = description;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(object instanceof DtCodes)) {
            return false;
        }
        DtCodes other = (DtCodes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtCodes[ id=" + id + " ]";
    }
    
}
