/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.DAO;

import com.fit.tuoitre.crawler.POJO.Feed;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author Corncob
 */
public class MongoDAO {

    private static String DB_HOSTNAME;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_NAME;

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private static MongoDAO instance = new MongoDAO();
    
    private MongoDAO() {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "db.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            DB_HOSTNAME = prop.getProperty("DB_HOSTNAME");
            DB_USER = prop.getProperty("DB_USER");
            DB_PASSWORD = prop.getProperty("DB_PASSWORD");
            DB_NAME = prop.getProperty("DB_NAME");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MongoCredential credential = MongoCredential.createCredential(DB_USER, DB_NAME, DB_PASSWORD.toCharArray());
        List<MongoCredential> credentialArrayList= new ArrayList<MongoCredential>();
        credentialArrayList.add(credential);

        this.mongoClient = new MongoClient(new ServerAddress(DB_HOSTNAME));

        this.db = this.mongoClient.getDatabase(DB_NAME);

        boolean hasFeedsCollection = false;

        MongoCursor<String> iterator = this.db.listCollectionNames().iterator();

        while(iterator.hasNext()){
            if (iterator.next().equals("feeds")){
                hasFeedsCollection = true;
                break;
            }
        }

        if(!hasFeedsCollection){
            this.db.createCollection("feeds");
        }

        this.collection = this.db.getCollection("feeds");
    }
    
    public static MongoDAO getInstance(){
      return instance;
   }

    public void insertFeed(Feed feed) {
        this.collection.insertOne(feed.getDocument());
    }

}
