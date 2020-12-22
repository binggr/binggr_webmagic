package com.binggr.webmagic.pipeline;

import com.binggr.webmagic.dao.UserDao;
import com.binggr.webmagic.pojo.User;
import com.binggr.webmagic.util.FileDownLoad;
import com.binggr.webmagic.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.IOException;

/**
 * 对用户数据抽取结果的处理并将用户存储到数据库，并将头像数据保存到本地
 * @author: bing
 * @date: 2020/12/21 22:53
 */
@Component
public class UserDbPipeline implements Pipeline {
    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;


    @Override
    public void process(ResultItems resultItems, Task task) {
        //将头像和图片地址 保存数据到数据库
        String imgUrl = resultItems.get("imgUrl").toString();//取得图片地址
        String name = resultItems.get("name").toString();//取得昵称
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/")+1)+".jpg";
        User user = new User();
        user.setId(idWorker.nextId()+"");
        user.setImgUrl(imgUrl);
        user.setName(name);
        userDao.save(user);

        //将图片下载到本地
        try {
            FileDownLoad.downloads(imgUrl,fileName,"C:/Users/bing/Desktop/crawler-img-csdn");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
