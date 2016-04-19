/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.POJO;

import com.google.gson.Gson;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.util.List;

/**
 *
 * @author Corncob
 */
public class Feed {

    private int objectId;
    private String title;
    private String category;
    private List<Comment> comments;
    private List<String> tags;
    private String publishedDate;
    private String feedUrl;
    
    /**
     * @return the objectId
     */
    public int getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getCommentsCount() {
        return this.comments.size();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public org.bson.Document getDocument() {
        org.bson.Document document = new org.bson.Document();
        document.append("objectId", this.objectId);
        document.append("url",this.feedUrl);
        document.append("title", this.title);
        document.append("category", this.category);
        document.append("pubdate",this.publishedDate);
        document.append("tags",this.tags);
        
        Gson gson = new Gson();
        String json = gson.toJson(this.comments);
        //BasicDBObject basicDBObject = new BasicDBObject("comments", json);
        DBObject bson = ( DBObject ) JSON.parse( json );
        document.append("comments",bson);
        return document;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate the publishedDate to set
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * @return the feedUrl
     */
    public String getFeedUrl() {
        return feedUrl;
    }

    /**
     * @param feedUrl the feedUrl to set
     */
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    
}
