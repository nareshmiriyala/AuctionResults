package com.dellnaresh.biz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DownloadAuctionFileTest {
    private DownloadAuctionFile auctionFile;

    @Before
    public void setUp() throws Exception {
        auctionFile=new DownloadAuctionFile();

    }

    @Test
    public void testDownload() throws Exception {
        auctionFile.download("http://www.homepriceguide.com.au/saturday_auction_results/Melbourne_Domain.pdf");
    }
}