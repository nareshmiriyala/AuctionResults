package com.dellnaresh.entity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by nareshm on 29/11/2015.
 */
public class DBConnection {
    private static DBConnection dbConnection;
    private static EntityManagerFactory entityManagerFactory=null;
    private DBConnection() {

    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                if (dbConnection == null) {
                    dbConnection = new DBConnection();
                }
            }
        }
        return dbConnection;
    }

    public EntityManagerFactory getEMFactory(){
        if(entityManagerFactory==null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("com.dellnaresh_AuctionResults_jar_1.0-SNAPSHOTPU");
        }
        return entityManagerFactory;
    }
}
