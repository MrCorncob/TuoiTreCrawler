package com.fit.tuoitre.crawler;

/*
 * Copyright 2015 Corncob.
 *
 */
/**
 *
 * @author Corncob
 */
import com.fit.tuoitre.crawler.Controller.TuoiTreCrawler;
import com.fit.tuoitre.crawler.DAO.MongoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import static java.lang.System.out;

public class TuoiTreCrawlController {

    private static final Logger logger = LoggerFactory.getLogger(TuoiTreCrawlController.class);
    public static final MongoDAO mongoDAO = MongoDAO.getInstance();

    public static void main(String[] args) throws Exception {
        //args = new String[2];
        out.print(args[0]);
        out.print(args[1]);
        if (args.length != 2) {

            logger.info("Needed parameters: ");
            logger.info("\t rootFolder (it will contain intermediate crawl data)");
            logger.info("\t numberOfCralwers (number of concurrent threads)");
            out.println("wrong parameters!");
            return;
        }

        /*
         *Thư mục để lưu cache crawl
         */
        String crawlStorageFolder = args[0];

        /*
         *Số lượng thread cần khởi tạo để crawl
         */
        int numberOfCrawlers = Integer.parseInt(args[1]);

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Thời gian delay giữa các lần send request
         */
        config.setPolitenessDelay(1000);

        /*
         * Độ sâu khi crawl, mặc định -1 là unlimited
         */
        config.setMaxDepthOfCrawling(-1);

        /*
         * Số page tối đa có thể crawl, mặc định -1 là unlimited
         */
        config.setMaxPagesToFetch(-1);

        /*
         * Thiết lập để crawl các tập tin nhị phân
         */
        config.setIncludeBinaryContentInCrawling(false);

        /*
         * Thiết lập này cho phép crawl tiếp tục tiến trình cũ
         * Nếu muốn crawl mới phải xóa dữ liệu thư mục lưu trữ đi 
         */
        config.setResumableCrawling(true);

        /*
         * Các biến cần thiết
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        int attempCount = 0;

        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * Seed URL, link URL bắt đầu duyệt để crawl
         */
        controller.addSeed("http://tuoitre.vn/");
        controller.addSeed("http://tuyensinh.tuoitre.vn/");
        controller.addSeed("http://diaoc.tuoitre.vn/");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2014");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2013");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-1-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-2-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-3-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-4-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-5-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-6-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-7-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-8-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-9-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-10-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-11-2012");
        controller.addSeed("http://tuoitre.vn/tim-kiem/?to=1-12-2012");
        
        /*
         * Bắt đầu crawl
         */
        controller.start(TuoiTreCrawler.class, numberOfCrawlers);
        
        // Send the shutdown request and then wait for finishing
        //controller.shutdown();
        //controller.waitUntilFinish();

        /*
         * Xử lí sau khi kết thúc crawl
         */
        
    }
}
