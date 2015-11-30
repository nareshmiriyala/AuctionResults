package com.dellnaresh.schedule;

import com.dellnaresh.enums.Constants;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nareshm on 30/11/2015.
 */
public class WebsiteFileTimer {
    public void startTimer(int sec){
        System.out.println("Starting the Website download Timer Task");
        // monitor a single file
        TimerTask task = new WebSiteFileWatcher(new File(Constants.WEB_SITE_URL));

        Timer timer = new Timer();
        // repeat the check every second
        timer.schedule( task , new Date(), sec );
    }
}
