package com.dellnaresh.biz;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

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


    public String readPDFAndGetText() {
        String finalStr=null;
        try {
            PDDocument document=PDDocument.load(new File(pdfFilePath));
            if(!document.isEncrypted()){

                PDFTextStripper textStripper=new PDFTextStripper();
                textStripper.setWordSeparator(HouseBuilder.WORD_SEPARATOR);
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
