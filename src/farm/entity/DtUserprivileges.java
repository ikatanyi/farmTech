/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_userprivileges")
public class DtUserprivileges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "UserId")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
    private String userId;

    @Column(name = "UL1")
    private String ul1;
   
    @Column(name = "UL2")
    private String ul2;
    
    @Column(name = "UL3")
    private String ul3;
    
    @Column(name = "UL4")
    private String ul4;
    
    @Column(name = "UL5")
    private String ul5;
    
    @Column(name = "UL6")
    private String ul6;
    
    @Column(name = "UL7")
    private String ul7;
    
    @Column(name = "UL8")
    private String ul8;
    
    @Column(name = "UL9")
    private String ul9;
    
    @Column(name = "UL10")
    private String ul10;
    
    @Column(name = "UL11")
    private String ul11;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private DtUsers user;

    public DtUserprivileges() {
    }

    public DtUserprivileges(String userId) {
        this.userId = userId;
    }

    public DtUserprivileges(String ul1, String ul2, String ul3, String ul4, String ul5, String ul6, String ul7, String ul8, String ul9, String ul10, String ul11) {
        this.ul1 = ul1;
        this.ul2 = ul2;
        this.ul3 = ul3;
        this.ul4 = ul4;
        this.ul5 = ul5;
        this.ul6 = ul6;
        this.ul7 = ul7;
        this.ul8 = ul8;
        this.ul9 = ul9;
        this.ul10 = ul10;
        this.ul11 = ul11;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUl1() {
        return ul1;
    }

    public void setUl1(String ul1) {
        this.ul1 = ul1;
    }

    public String getUl2() {
        return ul2;
    }

    public void setUl2(String ul2) {
        this.ul2 = ul2;
    }

    public String getUl3() {
        return ul3;
    }

    public void setUl3(String ul3) {
        this.ul3 = ul3;
    }

    public String getUl4() {
        return ul4;
    }

    public void setUl4(String ul4) {
        this.ul4 = ul4;
    }

    public String getUl5() {
        return ul5;
    }

    public void setUl5(String ul5) {
        this.ul5 = ul5;
    }

    public String getUl6() {
        return ul6;
    }

    public void setUl6(String ul6) {
        this.ul6 = ul6;
    }

    public String getUl7() {
        return ul7;
    }

    public void setUl7(String ul7) {
        this.ul7 = ul7;
    }

    public String getUl8() {
        return ul8;
    }

    public void setUl8(String ul8) {
        this.ul8 = ul8;
    }

    public String getUl9() {
        return ul9;
    }

    public void setUl9(String ul9) {
        this.ul9 = ul9;
    }

    public String getUl10() {
        return ul10;
    }

    public void setUl10(String ul10) {
        this.ul10 = ul10;
    }

    public String getUl11() {
        return ul11;
    }

    public void setUl11(String ul11) {
        this.ul11 = ul11;
    }
    
     public DtUsers getUser() {
        return user;
    }

    public void setUser(DtUsers user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtUserprivileges)) {
            return false;
        }
        DtUserprivileges other = (DtUserprivileges) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtUserprivileges[ userId=" + userId + " ]";
    }
    
}
