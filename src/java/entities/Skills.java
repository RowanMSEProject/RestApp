/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author junxin
 */
@Entity
@Table(name = "SKILLS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skills.findAll", query = "SELECT s FROM Skills s"),
    @NamedQuery(name = "Skills.findBySkillsid", query = "SELECT s FROM Skills s WHERE s.skillsid = :skillsid"),
    @NamedQuery(name = "Skills.findByDescription", query = "SELECT s FROM Skills s WHERE s.description = :description")})
public class Skills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SKILLSID")
    private Integer skillsid;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skills")
    private Collection<Skillsforusers> skillsforusersCollection;

    public Skills() {
    }

    public Skills(Integer skillsid) {
        this.skillsid = skillsid;
    }

    public Integer getSkillsid() {
        return skillsid;
    }

    public void setSkillsid(Integer skillsid) {
        this.skillsid = skillsid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Skillsforusers> getSkillsforusersCollection() {
        return skillsforusersCollection;
    }

    public void setSkillsforusersCollection(Collection<Skillsforusers> skillsforusersCollection) {
        this.skillsforusersCollection = skillsforusersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillsid != null ? skillsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skills)) {
            return false;
        }
        Skills other = (Skills) object;
        if ((this.skillsid == null && other.skillsid != null) || (this.skillsid != null && !this.skillsid.equals(other.skillsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Skills[ skillsid=" + skillsid + " ]";
    }
    
}
