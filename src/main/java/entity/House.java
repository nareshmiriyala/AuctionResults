package entity;

import java.math.BigDecimal;

/**
 * @author Proprietary information of Utiba. Copyright 2015 Utiba Pte Ltd. All
 *         rights reserved.
 */
public class House {
    private int id;
    private Address address;
    private String noOfBedRooms;
    private TYPE type;

    private BigDecimal price;

    private STATUS status;
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

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public enum TYPE{

        h("HOUSE"),
        t("TOWN_HOUSE"),
        u("UNIT"),
        dev_site("DEVELOPMENT_SITE"),
        o_res("o res");
        private String house_type;
        private TYPE(String house_type){
            this.house_type=house_type;
        }

    }
    public enum STATUS{
        S("PROPERTY_SOLD"),
        SP("PROPERTY_SOLD_PRIOR"),
        PI("PROPERTY_PASSED_IN"),
        PN("SOLD_PRIOR_NOT_DISCLOSED"),
        SN("SOLD_NOT_DISCLOSED"),
        NB("NO_BID"),
        VB("VENDOR_BID"),
        W("WITHDRAWN_PRIOR_TO_AUCTION"),
        SA("SOLD"),
        SS("SOLD_AFTER_AUCTION_AUCTION_PRICE_NOT_DISCLOSED"),
        NA("PRICE_OR_HIGHEST_BID_NOT_AVAILABLE");
        private String status;

        private STATUS(String status){
            this.status=status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "STATUS{" +
                    "status='" + status + '\'' +
                    '}';
        }


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
