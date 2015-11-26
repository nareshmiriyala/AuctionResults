package com.dellnaresh;

import entity.Address;
import entity.House;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
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
        System.out.println(text);
        BufferedReader bufReader = new BufferedReader(new StringReader(text));
        StringBuilder finalString=new StringBuilder();
        formatString(bufReader, finalString);
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
        if(splitAdd.length==2)
        address.setStreet(splitAdd[1]);
        return address;
    }

    private static void formatString(BufferedReader bufReader, StringBuilder finalString) throws IOException {
        String line;
        while( (line=bufReader.readLine()) != null )
        {
            if(IsDate(line)){
                continue;
            }
            if(finalString.length()==0||line.matches("([a-zA-Z]|\\s)*")){
                finalString.append(line);
            } else if( !line.matches("^\\w(\\w|\\s)*\\d.*")){
            finalString.append(WORD_SEPARATOR).append(line);
        } else {
                finalString.append("\n").append(line);
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
                textStripper.setDropThreshold(4);
                textStripper.setShouldSeparateByBeads(false);
                String text = textStripper.getText(document);
                text=text.replace("Saturday's Auctions","");
                text=text.replace("Suburb     Address     Type     Price     Result     Agent","");
                text=text.replace("KEY: S - property sold; SP - property sold prior; PI - property passed in; PN - sold prior not disclosed; SN - sold not disclosed; NB - no bid; VB - vendor bid; W - withdrawn prior to \n" +
                        "auction; SA - sold after auction; SS - sold after auction price not disclosed. N/A - price or highest bid not available. br - bedroom(s); h - house,cottage,villa, semi,terrace; u - unit, \n" +
                        "duplex; t - townhouse; dev site - development site; o res - other residential.\n" +
                        "For a more comprehensive list of results covering all reported sales by postcode in the past 12 or 24 months, visit http://www.homepriceguide.com.au\n" +
                        "Copyright © Australian Property Monitors 2015. Any reproduction of or reference to any part of this report must attribute Australian Property monitors as the source of the report.","");
                text=text.replace("Property Snapshot.*", "");
                text = Pattern.compile("Melbourne Auction Results.*default.aspx", Pattern.DOTALL).matcher(text).replaceAll("");
                text = Pattern.compile("(?m)^[ \\t]*\\r?\\n").matcher(text).replaceAll("");
                StringBuilder st=new StringBuilder(text.trim());

              finalStr= st.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalStr;
    }
}
