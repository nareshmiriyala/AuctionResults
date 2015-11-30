/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dellnaresh.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nareshm
 */
@Entity
@Table(name = "house")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "House.findAll", query = "SELECT h FROM House h"),
    @NamedQuery(name = "House.findById", query = "SELECT h FROM House h WHERE h.id = :id"),
    @NamedQuery(name = "House.findByAgency", query = "SELECT h FROM House h WHERE h.agency = :agency"),
    @NamedQuery(name = "House.findByNoOfBedRooms", query = "SELECT h FROM House h WHERE h.noOfBedRooms = :noOfBedRooms"),
    @NamedQuery(name = "House.findByPrice", query = "SELECT h FROM House h WHERE h.price = :price"),
    @NamedQuery(name = "House.findByType", query = "SELECT h FROM House h WHERE h.type = :type"),
    @NamedQuery(name = "House.findByStatus", query = "SELECT h FROM House h WHERE h.status = :status")})
public class House implements Serializable {
    @Basic(optional = false)
    @Column(name = "auction_date")
    @Temporal(TemporalType.DATE)
    private Date auctionDate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "agency")
    private String agency;
    @Column(name = "noOfBedRooms")
    private String noOfBedRooms;
    @Column(name = "price")
    private Long price;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address addressId;

    public House() {
    }

    public House(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
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
        if (!(object instanceof House)) {
            return false;
        }
        House other = (House) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "House{" + "id=" + id + ", agency=" + agency + ", noOfBedRooms=" + noOfBedRooms + ", price=" + price + ", type=" + type + ", status=" + status + ", addressId=" + addressId + '}';
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

   
    
}
