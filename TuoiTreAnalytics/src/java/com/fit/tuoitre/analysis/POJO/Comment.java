/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.analysis.POJO;

import java.io.Serializable;

/**
 *
 * @author Corncob
 */
public class Comment{
    private int commentId;//ID của bình luận
    private String author;//Tên người bình luận
    private String time;//Thời gian bình luận
    private int likeCount;//Lượt thích
    private String content;//Nội dung bình luận
    private int opinionMining;//Đánh giá bình luận không đồng tình/trung tính/đồng tình (optional)
    private boolean spam;//Spam
    private int gender;//Giới tính của người bình luận (Optional) -1: chưa xác định / 0: Nam / 1: Nữ

    /**
     * @return the commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

  
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the likeCount
     */
    public int getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount the likeCount to set
     */
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    

    /**
     * @return the opinionMining
     */
    public int getOpinionMining() {
        return opinionMining;
    }

    /**
     * @param opinionMining the opinionMining to set
     */
    public void setOpinionMining(int opinionMining) {
        this.opinionMining = opinionMining;
    }

    /**
     * @return the isSpam
     */
    public boolean spam() {
        return spam;
    }

    /**
     * @param spam the isSpam to set
     */
    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    /**
     * @return the gender
     * -1: chưa xác định / 0: Nam / 1: Nữ
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     * -1: chưa xác định / 0: Nam / 1: Nữ
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    
    
    
}
