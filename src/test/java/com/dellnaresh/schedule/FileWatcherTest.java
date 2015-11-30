package com.dellnaresh.schedule;

import com.dellnaresh.enums.Constants;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.Assert.*;

public class FileWatcherTest {
    public static void main(String args[]) {
        // monitor a single file
        TimerTask task = new WebSiteFileWatcher(new File(Constants.AUCTION_FILES_DIR+"change.txt"));

        Timer timer = new Timer();
        // repeat the check every second
        timer.schedule( task , new Date(), 1000 );
    }


}