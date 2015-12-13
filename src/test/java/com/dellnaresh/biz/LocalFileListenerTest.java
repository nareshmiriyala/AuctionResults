package com.dellnaresh.biz;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class LocalFileListenerTest {
    private LocalFileListener listener;

    @Before
    public void setUp() throws Exception {
        listener=new LocalFileListener();

    }

    @Test
    public void testDoProcessing() throws Exception {
        listener.doProcessing(Paths.get("Melbourne_Auctions_01_12_2015.pdf"));
    }
}