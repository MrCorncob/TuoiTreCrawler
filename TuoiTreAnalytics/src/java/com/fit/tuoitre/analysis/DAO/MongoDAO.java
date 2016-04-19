/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.analysis.DAO;

import com.fit.tuoitre.analysis.POJO.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.ProcessBuilder.Redirect.Type;
import java.text.Normalizer;

/**
 *
 * @author Corncob
 */
public class MongoDAO {

    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_NAME = "tuoitre";
    private MongoClient mongoClient;
    private DB db;
    private DBCollection collection;
    private static MongoDAO instance = new MongoDAO();

    private MongoDAO() {
        this.mongoClient = new MongoClient();
        this.db = this.mongoClient.getDB(DB_NAME);
        this.collection = this.db.getCollection("feeds");
    }

    /**
     *
     * @return
     */
    public static MongoDAO getInstance() {
        return instance;
    }

    /**
     *
     * @param feed
     */
    public void insertFeed(Feed feed) {
        //this.collection.insertOne(feed.getDocument());

    }

    /**
     *
     * @param objectId
     * @return
     */
    /*
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
     */
    public User takeLogin(String username, String password) {
        User user = null;
        return user;
    }

    public long getFeedCount() {
        int count = 0;
        return this.collection.getCount();
    }

    public long getCommentCount() {
        //long count = this.collection.find((DBObject) JSON.parse("{'comments[]': {'$exists': true}}'")).length();
        //long count = this.collection.count((DBObject) JSON.parse("{'comments': {'$exists': true}}'"));
        long count = 0;
        DBCursor cursor = this.collection.find((DBObject) JSON.parse("{'comments': {'$exists': true}}'"));
        while (cursor.hasNext()) {
            DBObject doc = (DBObject) cursor.next();
            List<Comment> comments = (List<Comment>) doc.get("comments");
            count += comments.size();
        }
        return count;

    }

    public long getUserCount() {
        return 0;
    }

    public long getCategoryCount() {
        long count = 0;
        count = this.collection.distinct("category").size();
        return count;
    }

    public List<Feed> getFeeds(long page) {
        int count = 50;
        long skip = (page - 1) * 50;
        List<Feed> feeds = new ArrayList<Feed>();
        DBCursor cursor = this.collection.find().skip((int) skip);
        while (cursor.hasNext() && count > 0) {
            DBObject doc = (DBObject) cursor.next();
            Feed feed = new Feed();
            List<Comment> comments = (List<Comment>) doc.get("comments");
            List<String> tags = (List<String>) doc.get("tags");
            feed.setComments(comments);
            feed.setTitle(doc.get("title").toString());
            feed.setCategory(doc.get("category").toString());
            feed.setFeedUrl(doc.get("url").toString());
            feed.setObjectId((int) Float.parseFloat(doc.get("objectId").toString()));
            feed.setTags(tags);
            feed.setPublishedDate(doc.get("pubdate").toString());
            feeds.add(feed);
            count--;
        }
        return feeds;
    }

    public Feed getFeed(int objectId) {
        Feed feed = new Feed();
        Gson gson = new Gson();
        this.collection.setObjectClass(Feed.class);
        DBCursor cursor = this.collection.find((DBObject) JSON.parse("{'objectId':" + objectId + "}"));
        DBObject doc = (DBObject) cursor.next();

        DBObject dbObject = (DBObject) doc.get("comments");
        String json = gson.toJson(dbObject);
        java.lang.reflect.Type type = new TypeToken<List<Comment>>() {
        }.getType();
        List<Comment> comments = gson.fromJson(json, type);
        out.print(dbObject);
        //List<Comment> comments = null;
        //List<Comment> comments = (List<Comment>) dbObject;
        List<String> tags = (List<String>) doc.get("tags");
        feed.setComments(comments);
        feed.setTitle(doc.get("title").toString());
        feed.setCategory(doc.get("category").toString());
        feed.setFeedUrl(doc.get("url").toString());
        feed.setObjectId((int) Float.parseFloat(doc.get("objectId").toString()));
        feed.setTags(tags);
        feed.setPublishedDate(doc.get("pubdate").toString());

        return feed;
    }

    public List<Feed> getFeeds(String keyword) {

        List<Feed> feeds = new ArrayList<Feed>();
        DBCursor cursor = this.collection.find();
        while (cursor.hasNext()) {
            boolean isNear = false;
            DBObject doc = (DBObject) cursor.next();
            Feed feed = new Feed();
            List<String> tags = (List<String>) doc.get("tags");
            String title = doc.get("title").toString();
            keyword = keyword.toLowerCase();
            keyword = Normalizer.normalize(keyword, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            title = title.toLowerCase();
            title = Normalizer.normalize(title, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (title.contains(keyword)) {
                isNear = true;
            } else {
                for (String tag : tags) {
                    tag = tag.toLowerCase();
                    tag = Normalizer.normalize(tag, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                    if (tag.contains(keyword)) {
                        out.print(keyword);
                        out.print(tag);
                        isNear = true;
                        break;
                    }
                }
            }
            if (isNear) {
                List<Comment> comments = (List<Comment>) doc.get("comments");
                feed.setComments(comments);
                feed.setTitle(doc.get("title").toString());
                feed.setCategory(doc.get("category").toString());
                feed.setFeedUrl(doc.get("url").toString());
                feed.setObjectId((int) Float.parseFloat(doc.get("objectId").toString()));
                feed.setTags(tags);
                feed.setPublishedDate(doc.get("pubdate").toString());
                feeds.add(feed);
            }

        }
        return feeds;
    }
}
