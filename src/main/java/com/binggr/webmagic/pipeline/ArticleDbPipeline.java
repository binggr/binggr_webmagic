package com.binggr.webmagic.pipeline;

import com.binggr.webmagic.dao.ArticleDao;
import com.binggr.webmagic.pojo.Article;
import com.binggr.webmagic.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 对文章数据抽取结果的处理并将数据存储在数据库
 * @author: bing
 * @date: 2020/12/21 19:42
 */
@Component
public class ArticleDbPipeline implements Pipeline {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    private String channelId;//频道ID

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title").toString();//取得标题
        String content = resultItems.get("content").toString();//取得内容
        Article article = new Article();
        article.setId(idWorker.nextId()+"");
        article.setTitle(title);
        article.setChannelId(channelId);
        article.setContent(content);
        articleDao.save(article); //保存数据到数据库
    }
}
