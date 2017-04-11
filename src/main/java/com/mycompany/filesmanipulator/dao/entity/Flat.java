/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filesmanipulator.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@Entity
@Table(name = "flat", catalog = "text_files_statistics", schema = "")
@XmlRootElement
public class Flat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flat_id")
    private Integer flatId;
    @Size(max = 30)
    @Column(name = "address")
    private String address;
    @ManyToMany
    @JoinTable(name = "flat_owner",
            joinColumns = @JoinColumn(name = "flat_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<FlatOwner> owners = new HashSet<>();
    public Set<FlatOwner> getOwners() {
        return owners;
    }
 
    public void setOwners(Set<FlatOwner> owners) {
        this.owners = owners;
    }

    public Flat() {
    }

    public Flat(Integer flatId) {
        this.flatId = flatId;
    }

    public Integer getFlatId() {
        return flatId;
    }

    public void setFlatId(Integer flatId) {
        this.flatId = flatId;
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
        hash += (flatId != null ? flatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flat)) {
            return false;
        }
        Flat other = (Flat) object;
        if ((this.flatId == null && other.flatId != null) || (this.flatId != null && !this.flatId.equals(other.flatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.filesmanipulator.dao.entity.Flat[ flatId=" + flatId + " ]";
    }
    
}
