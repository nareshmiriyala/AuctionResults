package com.dellnaresh.db;

import com.dellnaresh.entity.DBConnection;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nareshm on 1/12/2015.
 */
public class AddressFetcher {
    private AddressJpaController addressJpaController;
    public AddressFetcher(){
        addressJpaController=new AddressJpaController(DBConnection.getInstance().getEMFactory());
    }
    public Set<String> getAllSuburbs(){
        List<Address> addressEntities = addressJpaController.findAddressEntities();
        Set<String> suburbs=new TreeSet<String>();
        for(Address address:addressEntities){
            suburbs.add(address.getSuburb());
        }
        return suburbs;
    }
}
