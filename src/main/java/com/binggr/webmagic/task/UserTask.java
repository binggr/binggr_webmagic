package com.binggr.webmagic.task;

import com.binggr.webmagic.pipeline.UserDbPipeline;
import com.binggr.webmagic.processor.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * 定时任务 定时爬取用户数据并对数据进行保存
 * @author: bing
 * @date: 2020/12/21 23:07
 */
@Component
public class UserTask {
    @Autowired
    UserProcessor userProcessor;

    @Autowired
    RedisScheduler redisScheduler;

    @Autowired
    UserDbPipeline userDbPipeline;

//    @Scheduled(cron = "30 09 11 * * ?")
    @Scheduled(cron = "* * * * * ?")
    public void aiTask(){
        System.out.println("爬取用户数据");
        Spider spider = Spider.create(userProcessor);
        spider.addUrl("https://www.csdn.net/");
        spider.addPipeline(userDbPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

}
