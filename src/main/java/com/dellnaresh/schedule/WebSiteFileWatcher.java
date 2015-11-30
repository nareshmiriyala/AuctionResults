package com.dellnaresh.schedule;

import com.dellnaresh.biz.DownloadAuctionFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by nareshm on 30/11/2015.
 */
public class WebSiteFileWatcher extends FileWatcher {
    public WebSiteFileWatcher(File file) {
        super(file);
    }

    @Override
    protected void onChange(File file) {
        // here we code the action on a change
        System.out.println( "File "+ file.getName() +" have changes !" );
        DownloadAuctionFile downloadAuctionFile=new DownloadAuctionFile();
        try {
            downloadAuctionFile.download(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
