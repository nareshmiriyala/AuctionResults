package com.dellnaresh;

import com.dellnaresh.biz.HouseBuilder;
import com.dellnaresh.biz.LocalFileListener;
import com.dellnaresh.biz.ReadFromPDF;
import com.dellnaresh.biz.WebSiteFileListener;
import com.dellnaresh.db.House;
import com.dellnaresh.db.HouseJpaController;
import com.dellnaresh.entity.DBConnection;
import com.dellnaresh.enums.Constants;
import com.dellnaresh.schedule.WebsiteFileTimer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws Exception {
        Thread webSiteThread=new Thread(new Runnable() {
            @Override
            public void run() {
                WebsiteFileTimer webSiteFileListener=new WebsiteFileTimer();
                    webSiteFileListener.startTimer(1000);

            }
        });
        webSiteThread.start();
        Thread localFileThread=new Thread(new Runnable() {
            @Override
            public void run() {
                LocalFileListener localFileListener=new LocalFileListener();

                try {
                    localFileListener.registerWatcher(Constants.AUCTION_FILES_DIR);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
      localFileThread.start();

    }


}
