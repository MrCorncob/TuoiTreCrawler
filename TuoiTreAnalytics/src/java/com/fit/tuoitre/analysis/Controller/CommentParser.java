/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.analysis.Controller;


import com.fit.tuoitre.analysis.POJO.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Corncob
 */
public class CommentParser {
    private static final String USER_AGENT ="Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private static final String REFERER = "http://www.google.com";
    private static final String REQUEST_URL ="http://cm.tuoitre.vn/comment/ttocreateiframe";
    private static final int APP_ID = 6;
    private static final int LAYOUT = 3;
    private static final String SORT_TYPE = "date";
    
    public List<Comment> getComment(int objectId)
    {
        List<Comment> comments = new ArrayList<Comment>();
        int offset = 1;
        while(true)
        {
            List<Comment> _fragComments = this.getComment(objectId, offset);
            if(_fragComments.isEmpty())
                break;
            comments.addAll(_fragComments);
            offset++;
        }
        return comments;
    }
    
    public List<Comment> getComment(int objectId, int offset)
    {
        List<Comment> comments = new ArrayList<Comment>();
        Elements commentElements = this.getCommentElements(objectId, offset);
        for(Element commentElement: commentElements)
        {
            Comment comment = new Comment();
            comment.setAuthor(commentElement.select(".name").text());
            int commentId = Integer.parseInt(commentElement.select(".like_btn, .btn-like-cm").attr("id").substring("like_comment_id-".length()));
            comment.setCommentId(commentId);
            String cmMinimize = commentElement.select(".cm-content .minimize").text();
            String cmRemain = commentElement.select(".cm-content .remain").text();
            comment.setContent(cmMinimize+cmRemain);
            comment.setTime(commentElement.select(".time").text());
            comment.setLikeCount(Integer.parseInt(commentElement.select(".like_number").text()));
            comments.add(comment);
        }
        return comments;
    }
    
    private Elements getCommentElements(int objectId,int offset)
    {
        Elements commentElements;
        try {
            Document doc = Jsoup.connect(REQUEST_URL)
                    .data("offset",Integer.toString(offset))
                    .data("app_id",Integer.toString(APP_ID))
                    .data("object_id",Integer.toString(objectId))
                    .data("layout",Integer.toString(LAYOUT))
                    .data("sort",SORT_TYPE)
                    .userAgent(USER_AGENT)
                    .post();
            commentElements = doc.select("dd");
            return commentElements;
        } catch (IOException ex) {
            Logger.getLogger(CommentParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
