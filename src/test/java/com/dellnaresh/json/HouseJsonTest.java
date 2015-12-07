package com.dellnaresh.json;

import com.dellnaresh.db.Address;
import com.dellnaresh.db.House;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import java.util.Date;

/**
 * Created by nareshm on 7/12/2015.
 */
public class HouseJsonTest {
    @Test
    public void testJsonHouse() throws Exception {
        House house=new House(1l);
        house.setAgency("McLennan");
        house.setAuctionDate(new Date());
        house.setAddressId(new Address(234l));
        house.setNoOfBedRooms("4");
        house.setPrice(100l);
        house.setStatus("Sold");
        house.setType("House");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(house);
        System.out.println(json);

    }
}
