/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.Controller;

import com.fit.tuoitre.crawler.POJO.Feed;
import com.fit.tuoitre.crawler.TuoiTreCrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import static java.lang.System.out;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Corncob
 */
public class TuoiTreCrawler extends WebCrawler {

    private static final Logger logger = LoggerFactory.getLogger(TuoiTreCrawler.class);

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    private static final Pattern SUBDOMAIN_FILTERS = Pattern.compile("^http://(tuyensinh|diaoc|nhipsongso|dulich|thethao)\\.tuoitre\\.vn/.*");
    private static final String DOMAIN = "http://tuoitre.vn/";
    private static final Pattern NEWS_PAGE = Pattern.compile(".*/\\w+\\.html");
    private static final Pattern TAGS_PAGES = Pattern.compile(".*tuoitre\\.vn\\/(tag|chu-de).*");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        //out.print("checking "+href);
        boolean shouldBe = !FILTERS.matcher(href).matches() && (href.startsWith(DOMAIN) || SUBDOMAIN_FILTERS.matcher(href).matches());
        //if(shouldBe)
        //    out.println("  should visit");
        //else
        //    out.println("  should not visit");
        return shouldBe;
    }

    @Override
    public void visit(Page page) {
        
        String url = page.getWebURL().getURL();
        
        out.print("Visiting "+url);
        if (!NEWS_PAGE.matcher(url).matches() || TAGS_PAGES.matcher(url).matches()) {
            out.println(" -- skipped");
            return;
        }
       
        if (page.getParseData() instanceof HtmlParseData) {
            Feed feed;
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            FeedParser feedParser = new FeedParser();
            feed = feedParser.parseFromHTML(html);
            feed.setFeedUrl(url);
            TuoiTreCrawlController.mongoDAO.insertFeed(feed);
            out.println(" -- parsed");
        }
        else
            out.println("Instance error");

    }

}
