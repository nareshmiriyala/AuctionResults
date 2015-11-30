package com.dellnaresh.biz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadFromPDFTest {
    private ReadFromPDF readFromPDF;

    @Before
    public void setUp() throws Exception {
        readFromPDF=new ReadFromPDF("src/test/resources/Melbourne_Domain.pdf");

    }

    @Test
    public void testReadPDFAndGetText() throws Exception {
        String s = readFromPDF.readPDFAndGetText();
        System.out.println(s);
    }
}