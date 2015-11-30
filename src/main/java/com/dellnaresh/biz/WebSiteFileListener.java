package com.dellnaresh.biz;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by nareshm on 30/11/2015.
 */
public class WebSiteFileListener extends FileListener {
    @Override
    public void doProcessing(Path filePath) throws IOException {
        DownloadAuctionFile downloadAuctionFile=new DownloadAuctionFile();
        downloadAuctionFile.download(filePath.toUri().toString());
    }
}
