package com.dellnaresh.biz;

import com.dellnaresh.enums.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileListenerTest {
    private FileListener fileListener;

    @Before
    public void setUp() throws Exception {
        fileListener=new WebSiteFileListener();
    }

    @Test
    public void testRegisterWatcher() throws Exception {
        fileListener.registerWatcher("http://www.homepriceguide.com.au/saturday_auction_results/Melbourne_Domain.pdf");
    }
}