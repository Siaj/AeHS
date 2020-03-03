/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "farm_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmDetail.findAll", query = "SELECT f FROM FarmDetail f")
    , @NamedQuery(name = "FarmDetail.findById", query = "SELECT f FROM FarmDetail f WHERE f.id = :id")
    , @NamedQuery(name = "FarmDetail.findByName", query = "SELECT f FROM FarmDetail f WHERE f.name = :name")
    , @NamedQuery(name = "FarmDetail.findByMainPlantation", query = "SELECT f FROM FarmDetail f WHERE f.mainPlantation = :mainPlantation")
    , @NamedQuery(name = "FarmDetail.findByLocation", query = "SELECT f FROM FarmDetail f WHERE f.location = :location")
    , @NamedQuery(name = "FarmDetail.findByUpdated", query = "SELECT f FROM FarmDetail f WHERE f.updated = :updated")
    , @NamedQuery(name = "FarmDetail.findByDeleted", query = "SELECT f FROM FarmDetail f WHERE f.deleted = :deleted")})
public class FarmDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "_id")
    private String id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "main_plantation")
    private String mainPlantation;
    @Size(max = 255)
    @Column(name = "location")
    private String location;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @JoinColumn(name = "owner", referencedColumnName = "_id")
    @ManyToOne
    private SystemUser owner;
    @OneToMany(mappedBy = "farmDetail")
    private List<MlPrediction> mlPredictionList;

    public FarmDetail() {
    }

    public FarmDetail(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPlantation() {
        return mainPlantation;
    }

    public void setMainPlantation(String mainPlantation) {
        this.mainPlantation = mainPlantation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public SystemUser getOwner() {
        return owner;
    }

    public void setOwner(SystemUser owner) {
        this.owner = owner;
    }

    @XmlTransient
    public List<MlPrediction> getMlPredictionList() {
        return mlPredictionList;
    }

    public void setMlPredictionList(List<MlPrediction> mlPredictionList) {
        this.mlPredictionList = mlPredictionList;
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
        if (!(object instanceof FarmDetail)) {
            return false;
        }
        FarmDetail other = (FarmDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.FarmDetail[ id=" + id + " ]";
    }
    
}
