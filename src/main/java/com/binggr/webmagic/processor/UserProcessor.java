package com.binggr.webmagic.processor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 用户爬取类，解析用户页面，抽取有用信息
 * @author: bing
 * @date: 2020/12/21 22:54
 */
@Component
public class UserProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all());
        //文章标题
        Selectable imgUrl = page.getHtml().xpath("//*[@id=\"asideProfile\"]/div[1]/div[1]/a").css("img","src");
        Selectable name = page.getHtml().xpath("//*[@id=\"uid\"]/span/text()");
        if(imgUrl!=null && name!=null){
            page.putField("imgUrl",imgUrl);
            page.putField("name",name);
        }else {
            page.setSkip(true);//跳过
        }
    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3000).setSleepTime(100);
    }
}
