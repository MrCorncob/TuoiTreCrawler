/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.DAO;

import com.fit.tuoitre.crawler.POJO.Feed;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 *
 * @author Corncob
 */
public class MongoDAO {

    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_NAME = "tuoitre";
    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection collection;
    private static MongoDAO instance = new MongoDAO();
    
    private MongoDAO() {
        this.mongoClient = new MongoClient();
        this.db = this.mongoClient.getDatabase(DB_NAME);
        this.collection = this.db.getCollection("feeds");
    }
    
    public static MongoDAO getInstance(){
      return instance;
   }

    public void insertFeed(Feed feed) {
        this.collection.insertOne(feed.getDocument());

    }

    public Feed findFeed(int objectId) {
        FindIterable<Document> iterable = collection.find(eq("borough", "Manhattan"));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
        return null;
    }
}
