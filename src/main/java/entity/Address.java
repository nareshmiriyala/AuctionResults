package entity;

/**
 * @author Proprietary information of Utiba. Copyright 2015 Utiba Pte Ltd. All
 *         rights reserved.
 */
public class Address {
    private String suburb;
    private String houseNo;
    private String street;

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
