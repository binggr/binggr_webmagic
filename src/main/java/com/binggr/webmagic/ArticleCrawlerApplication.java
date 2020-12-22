package com.binggr.webmagic;

import com.binggr.webmagic.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;

//注解依赖
@EnableJpaRepositories
@EnableScheduling
@SpringBootApplication
public class ArticleCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleCrawlerApplication.class, args);
    }

    @Value("${spring.redis.host}")
    private  String redis_host;

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    @Bean
    public RedisScheduler redisScheduler(){
        return new RedisScheduler(redis_host);
    }

}
