package com.dellnaresh.entity;

import com.dellnaresh.enums.Constants;

import java.math.BigDecimal;

/**
 * @author Proprietary information of Utiba. Copyright 2015 Utiba Pte Ltd. All
 *         rights reserved.
 */
public class House {
    private int id;
    private Address address;
    private String noOfBedRooms;
    private Constants.TYPE type;

    private BigDecimal price;

    private Constants.STATUS status;
    private String agency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public Constants.TYPE getType() {
        return type;
    }

    public void setType(Constants.TYPE type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Constants.STATUS getStatus() {
        return status;
    }

    public void setStatus(Constants.STATUS status) {
        this.status = status;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", address=" + address +
                ", noOfBedRooms='" + noOfBedRooms + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", status=" + status +
                ", agency='" + agency + '\'' +
                '}';
    }
}
