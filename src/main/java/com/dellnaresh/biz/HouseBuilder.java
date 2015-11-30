package com.dellnaresh.biz;

import com.dellnaresh.db.Address;
import com.dellnaresh.db.House;
import com.dellnaresh.enums.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nareshm on 29/11/2015.
 */
public class HouseBuilder {
    private static long count=0;

    public static  List<House>  prepareHouseObjects(String text) throws IOException {


        return createHouses(new StringBuilder(text));
    }

    private static  List<House>  createHouses(StringBuilder finalString) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(finalString.toString()));
        String line=null;
        List<House> houseList=new ArrayList<House>();
        while( (line=reader.readLine()) != null ){
            String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, Constants.WORD_SEPARATOR);

            if(split.length<6){
                String[] temp=new String[6];
                String[] secondColSplit = split[1].trim().split("\\s\\d");
                temp[5]=split[4];
                temp[4]=split[3];
                temp[3]=split[2];
                temp[2]=split[1].trim().replace(secondColSplit[0],"");
                temp[1]=secondColSplit[0];
                temp[0]=split[0];
                split=temp;
            }
            houseList.add(createHouse(split));

        }
        return houseList;
    }

    private static House createHouse(String[] split) {
        com.dellnaresh.db.House house = new House();

        try {

            house.setAgency(split[5]);
            house.setAddressId(createAndSetAddress(split));
            if(split[2].length()>1) {
                String[] splitThirdColumn = StringUtils.split(split[2], " ");
                house.setNoOfBedRooms(splitThirdColumn[0]);
                house.setType(splitThirdColumn[2]);
            }
            if (!split[3].trim().contains("N/A")) {
                house.setPrice(Long.parseLong(split[3].replaceAll("[\\$,\\,]", "")));
            }
            house.setStatus((split[4]));
        }catch (Exception e){
            for(String s:split)
            System.out.println(s);
            e.printStackTrace();
        }
        return house;
    }

    private static Address createAndSetAddress(String[] split) {
        Address address=new Address();
        address.setSuburb(split[0]);
        String[] splitAdd = StringUtils.split(split[1], " ");
        address.setHouseno(splitAdd[0]);
        address.setId((long) count++);
        if(splitAdd.length>2)
        address.setStreet(split[1].replace(splitAdd[0],"").trim());
        return address;
    }

   }
