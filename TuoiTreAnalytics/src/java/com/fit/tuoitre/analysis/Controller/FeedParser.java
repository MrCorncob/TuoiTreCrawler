/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.analysis.Controller;


import com.fit.tuoitre.analysis.POJO.Feed;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Corncob
 */
public class FeedParser {

    public Feed parseFromHTML(String html) {
        Feed feed = new Feed();
        Document doc = Jsoup.parse(html);
        CommentParser commentParser = new CommentParser();
        feed.setTitle(doc.title());
        feed.setObjectId(Integer.parseInt(doc.select("#object_id").val()));
        feed.setCategory(doc.select(".detail-content .active a").text());
        feed.setPublishedDate(doc.select("meta[name=pubdate]").attr("content"));
        List<String> tags = new ArrayList<String>();
        Elements tagElements = doc.select(".block-key li a");
        for (Element tagElement : tagElements) {
            String tag = tagElement.text();
            tags.add(tag);
        }
        feed.setTags(tags);
        feed.setComments(commentParser.getComment(feed.getObjectId()));
        return feed;
    }
}
