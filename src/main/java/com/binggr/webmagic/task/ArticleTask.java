package com.binggr.webmagic.task;

import com.binggr.webmagic.pipeline.ArticleDbPipeline;
import com.binggr.webmagic.processor.ArticleProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;


/**
 * 定时任务 定时爬取文章内容并对数据进行保存
 * @author: bing
 * @date: 2020/12/21 20:11
 */
@Component
public class ArticleTask {

    @Autowired
    private ArticleProcessor articleProcesor;

    @Autowired
    private RedisScheduler redisScheduler;

    @Autowired
    private ArticleDbPipeline articleDbPipeline;

    @Scheduled(cron = "30 38 22 * * ?")
    public void aiTask(){
        System.out.println("爬取AI文章");
        Spider spider = Spider.create(articleProcesor);
        spider.addUrl("https://ai.csdn.net/");
        articleDbPipeline.setChannelId("ai");
        spider.addPipeline(articleDbPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }


}
