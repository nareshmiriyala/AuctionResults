package com.dellnaresh.biz;

import com.dellnaresh.db.House;
import com.dellnaresh.db.HouseJpaController;
import com.dellnaresh.elastic.ElasticController;
import com.dellnaresh.entity.DBConnection;
import com.dellnaresh.enums.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

/**
 * Created by nareshm on 30/11/2015.
 */
public class LocalFileListener extends FileListener {
    private static long count=1;
    @Override
    public void doProcessing(Path filePath) throws IOException, ParseException {
        String text = new ReadFromPDF(Constants.AUCTION_FILES_DIR+filePath.toFile()).readPDFAndGetText();
        List<House> houses = HouseBuilder.prepareHouseObjects(text);
//        insertIntoDB(houses);
        for(House house:houses){
            if(house.getId()==null){
                house.setId(count++);
            }
         ElasticController.insertHouse(house);

        }
    }

    private void insertIntoDB(List<House> houses) {
        for(House house:houses){
            HouseJpaController houseJpaController=new HouseJpaController(DBConnection.getInstance().getEMFactory());
            houseJpaController.create(house);
        }
    }
}
