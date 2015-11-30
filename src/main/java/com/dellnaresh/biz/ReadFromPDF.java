package com.dellnaresh.biz;

import com.dellnaresh.enums.Constants;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.*;
import java.text.DateFormatSymbols;

/**
 * Created by nareshm on 29/11/2015.
 */
public class ReadFromPDF {
    private static String pdfFilePath=null;

    private ReadFromPDF(){

    }
    public ReadFromPDF(String filePath){
        pdfFilePath=filePath;
    }

    static boolean checkIfContainsDay(String line) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] arrayOfWeekDaysNames = dfs.getWeekdays();
        for( String dayName : arrayOfWeekDaysNames ) {
            if (line.toLowerCase().indexOf(dayName.toLowerCase()) != -1 ) {
                return true;
            }
        }

        return false;
    }


    public String readPDFAndGetText() throws IOException {
        String finalStr=null;
        try {
            PDDocument document=PDDocument.load(new File(pdfFilePath));
            if(!document.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition( true );
                PDFTextStripper textStripper=new PDFTextStripper();
                textStripper.setWordSeparator(Constants.WORD_SEPARATOR);
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
        return formatString(finalStr);
    }
    private static String formatString(String finalString) throws IOException {
        BufferedReader bufReader = new BufferedReader(new StringReader(finalString));
        String line;
        StringBuilder sb=new StringBuilder();
        while( (line=bufReader.readLine()) != null )
        {
            if(IsDate(line)){
                continue;
            }
            if(line.contains(" br ") && line.contains("$")) {
                if (sb.length() == 0 || line.matches("([a-zA-Z]|\\s)*")) {
                    sb.append(line);
                } else if (!line.matches("^\\w(\\w|\\s)*\\d.*")) {
                    sb.append(Constants.WORD_SEPARATOR).append(line);
                } else {
                    sb.append("\n").append(line);
                }
            }
        }
        return sb.toString();
    }

    private static boolean IsDate(String line) {
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December" };
        for(String mon:monthName){
            if (line.toLowerCase().indexOf(mon.toLowerCase()) != -1 ) {

                if(ReadFromPDF.checkIfContainsDay(line)) {
                    return true;
                }
            }
        }
        return false;
    }


}
