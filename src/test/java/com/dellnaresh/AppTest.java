package com.dellnaresh;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang3.StringUtils;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
//        String s="40 The Boulevard 3 br h";
        String s="804/228 The Avenue 4 br u";
//        s=s.replaceAll("[-+.^:,]","");
        String[] split = s.split("\\s\\d");
        for(String s1:split) {
            System.out.println(s1);
        }
        System.out.printf(s.replace(split[0],""));

        assertTrue(true);
    }
}
