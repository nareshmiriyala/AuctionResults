package com.dellnaresh.biz;

import com.dellnaresh.enums.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nareshm on 30/11/2015.
 */
public class DownloadAuctionFile implements DownloadFile {
    @Override
    public void download(String fileUrl) throws IOException {
        URL website = new URL(fileUrl);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(Constants.AUCTION_FILES_DIR +"Melbourne_Auctions_"+new SimpleDateFormat("dd_MM_yyyy").format(new Date())+".pdf");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
