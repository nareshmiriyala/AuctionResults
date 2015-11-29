package com.dellnaresh;

import com.dellnaresh.db.House;
import com.dellnaresh.db.HouseJpaController;
import com.dellnaresh.entity.DBConnection;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    public void testAddHouse()throws Exception{

        HouseJpaController jpaController=new HouseJpaController(DBConnection.getInstance().getEMFactory());
        House house = new House(1l);
        house.setAgency("Brady Road");
        jpaController.create(house);

    }

}
