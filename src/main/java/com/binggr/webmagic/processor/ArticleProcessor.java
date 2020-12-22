package com.binggr.webmagic.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 文章爬取类，解析文章页面，抽取有用信息
 * @author: bing
 * @date: 2020/12/21 19:23
 */

@Component
public class ArticleProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());
        //文章标题
        Selectable title = page.getHtml().xpath("//*[@id=\"articleContentId\"]");
        Selectable content = page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/article");
        if(title!=null && content!=null){
            page.putField("title",title);
            page.putField("content",content);
        }else {
            page.setSkip(true);//跳过
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
