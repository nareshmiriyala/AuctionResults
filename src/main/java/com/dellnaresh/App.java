package com.dellnaresh;

import entity.Address;
import entity.House;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final String WORD_SEPARATOR = "     ";
    private static int count=0;
    public static void main( String[] args ) throws IOException {

        String text = readPDFAndGetText();
        prepareHouseObjects(text);


    }

    private static void prepareHouseObjects(String text) throws IOException {
        BufferedReader bufReader = new BufferedReader(new StringReader(text));
        StringBuilder finalString=new StringBuilder();
        formatString(bufReader, finalString);
        createHouses(finalString);
    }

    private static void createHouses(StringBuilder finalString) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(finalString.toString()));
        String line=null;
        List<House> houseList=new ArrayList<House>();
        while( (line=reader.readLine()) != null ){
            String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, WORD_SEPARATOR);

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
        System.out.println(houseList);
    }

    private static House createHouse(String[] split) {
        House house = new House();

        try {
            house.setId(count++);
            house.setAgency(split[5]);
            house.setAddress(createAndSetAddress(split));
            if(split[2].length()>1) {
                String[] splitThirdColumn = StringUtils.split(split[2], " ");
                house.setNoOfBedRooms(splitThirdColumn[0]);
                house.setType(House.TYPE.valueOf(splitThirdColumn[2]));
            }
            if (!split[3].trim().contains("N/A")) {
                house.setPrice(BigDecimal.valueOf(Long.parseLong(split[3].replaceAll("[\\$,\\,]", ""))));
            }
            house.setStatus(House.STATUS.valueOf(split[4]));
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
        address.setHouseNo(splitAdd[0]);
        if(splitAdd.length>2)
        address.setStreet(split[1].replace(splitAdd[0],"").trim());
        return address;
    }

    private static void formatString(BufferedReader bufReader, StringBuilder finalString) throws IOException {
        String line;
        while( (line=bufReader.readLine()) != null )
        {
            if(IsDate(line)){
                continue;
            }
            if(line.contains(" br ") && line.contains("$")) {
                if (finalString.length() == 0 || line.matches("([a-zA-Z]|\\s)*")) {
                    finalString.append(line);
                } else if (!line.matches("^\\w(\\w|\\s)*\\d.*")) {
                    finalString.append(WORD_SEPARATOR).append(line);
                } else {
                    finalString.append("\n").append(line);
                }
            }
        }
    }

    private static boolean IsDate(String line) {
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        for(String mon:monthName){
            if (line.toLowerCase().indexOf(mon.toLowerCase()) != -1 ) {

                if(checkIfContainsDay(line)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkIfContainsDay(String line) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] arrayOfWeekDaysNames = dfs.getWeekdays();
        for( String dayName : arrayOfWeekDaysNames ) {
            if (line.toLowerCase().indexOf(dayName.toLowerCase()) != -1 ) {
                return true;
            }
        }

        return false;
    }

    private static String readPDFAndGetText() {
        String finalStr=null;
        try {
            PDDocument document=PDDocument.load(new File("src/test/resources/Melbourne_Domain.pdf"));
            if(!document.isEncrypted()){

                PDFTextStripper textStripper=new PDFTextStripper();
                textStripper.setWordSeparator(WORD_SEPARATOR);
                textStripper.setSortByPosition(true);
                String text = textStripper.getText(document);
                StringBuilder st=new StringBuilder(text.trim());

              finalStr= st.toString();
                File file = new File("src/test/resources/replace.properties");
                InputStream in = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line=null;
                while ((line=br.readLine())!=null){
                   if(line.length()>0){
                       finalStr=finalStr.replaceAll(line,"");
                   }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalStr;
    }
}
