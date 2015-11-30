package com.dellnaresh.enums;

/**
 * Created by nareshm on 29/11/2015.
 */
public class Constants {
    public static final String WORD_SEPARATOR = "     ";

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
}
