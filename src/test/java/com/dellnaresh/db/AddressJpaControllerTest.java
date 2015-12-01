package com.dellnaresh.db;

import com.dellnaresh.entity.DBConnection;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AddressJpaControllerTest {

    private AddressJpaController addressJpaController;

    @Before
    public void setUp() throws Exception {
        addressJpaController=new AddressJpaController(DBConnection.getInstance().getEMFactory());


    }

    @Test
    public void testAllAddressEntries() throws Exception {
        List<Address> addressEntities = addressJpaController.findAddressEntities();
        Set<String> suburbs=new TreeSet<String>();
        for(Address address:addressEntities){
            suburbs.add(address.getSuburb());

        }
        System.out.println(suburbs);

    }
}