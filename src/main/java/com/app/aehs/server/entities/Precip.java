/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(catalog = "aehs_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Precip.findAll", query = "SELECT p FROM Precip p")
    , @NamedQuery(name = "Precip.findById", query = "SELECT p FROM Precip p WHERE p.id = :id")
    , @NamedQuery(name = "Precip.findByValue", query = "SELECT p FROM Precip p WHERE p.value = :value")
    , @NamedQuery(name = "Precip.findByUpdated", query = "SELECT p FROM Precip p WHERE p.updated = :updated")
    , @NamedQuery(name = "Precip.findByDeleted", query = "SELECT p FROM Precip p WHERE p.deleted = :deleted")})
public class Precip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_id")
    private String id;
    @Size(max = 70)
    private String value;
    @Size(max = 10)
    private String updated;
    @Size(max = 10)
    private String deleted;

    public Precip() {
    }

    public Precip(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Precip)) {
            return false;
        }
        Precip other = (Precip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.Precip[ id=" + id + " ]";
    }
    
}
