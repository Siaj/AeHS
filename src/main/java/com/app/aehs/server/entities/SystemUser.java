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
@Table(name = "system_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemUser.findAll", query = "SELECT s FROM SystemUser s")
    ,@NamedQuery(name = SystemUser.FIND_BY_USERNAME_PASSWORD, query = "SELECT s FROM SystemUser s WHERE s.username = :username AND s.password = :password")
    , @NamedQuery(name = "SystemUser.findById", query = "SELECT s FROM SystemUser s WHERE s.id = :id")
    , @NamedQuery(name = "SystemUser.findByFirstname", query = "SELECT s FROM SystemUser s WHERE s.firstname = :firstname")
    , @NamedQuery(name = "SystemUser.findByLastname", query = "SELECT s FROM SystemUser s WHERE s.lastname = :lastname")
    , @NamedQuery(name = "SystemUser.findByUsername", query = "SELECT s FROM SystemUser s WHERE s.username = :username")
    , @NamedQuery(name = "SystemUser.findByPassword", query = "SELECT s FROM SystemUser s WHERE s.password = :password")
    , @NamedQuery(name = "SystemUser.findByAccountStatus", query = "SELECT s FROM SystemUser s WHERE s.accountStatus = :accountStatus")
    , @NamedQuery(name = "SystemUser.findByGender", query = "SELECT s FROM SystemUser s WHERE s.gender = :gender")
    , @NamedQuery(name = "SystemUser.findByPhoneNumber", query = "SELECT s FROM SystemUser s WHERE s.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "SystemUser.findByUpdated", query = "SELECT s FROM SystemUser s WHERE s.updated = :updated")
    , @NamedQuery(name = "SystemUser.findByDeleted", query = "SELECT s FROM SystemUser s WHERE s.deleted = :deleted")})
public class SystemUser implements Serializable {

    public static final String FIND_BY_USERNAME_PASSWORD = "SystemUser.FIND_BY_USERNAME_PASSWORD";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "_id")
    private String id;
    @Size(max = 50)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "account_status")
    private String accountStatus;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Size(max = 25)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 50)
    @Column(name = "updated")
    private String updated;
    @Size(max = 50)
    @Column(name = "deleted")
    private String deleted;
    @JoinColumn(name = "user_role", referencedColumnName = "_id")
    @ManyToOne
    private UserRole userRole;
    @OneToMany(mappedBy = "owner")
    private List<FarmDetail> farmDetailList;

    public SystemUser() {
    }

    public SystemUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @XmlTransient
    public List<FarmDetail> getFarmDetailList() {
        return farmDetailList;
    }

    public void setFarmDetailList(List<FarmDetail> farmDetailList) {
        this.farmDetailList = farmDetailList;
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
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.SystemUser[ id=" + id + " ]";
    }

}
