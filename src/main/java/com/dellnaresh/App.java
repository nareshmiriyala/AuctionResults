package com.dellnaresh;

import com.dellnaresh.biz.HouseBuilder;
import com.dellnaresh.biz.ReadFromPDF;
import com.dellnaresh.db.House;
import com.dellnaresh.db.HouseJpaController;
import com.dellnaresh.db.exceptions.PreexistingEntityException;
import com.dellnaresh.entity.DBConnection;

import java.io.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws Exception {

        String text = new ReadFromPDF("src/test/resources/Melbourne_Domain.pdf").readPDFAndGetText();
        List<House> houses = HouseBuilder.prepareHouseObjects(text);
        for(House house:houses){
            HouseJpaController houseJpaController=new HouseJpaController(DBConnection.getInstance().getEMFactory());
            houseJpaController.create(house);
        }
    }

}
