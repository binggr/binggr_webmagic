package com.binggr.webmagic.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文章实体类
 * @author: bing
 * @date: 2020/12/21 19:52
 */
@Data
@Entity
@Table(name = "tb_article")
public class Article {
    @Id
    private String id;        //文章ID
    private String title;     //文章标题
    private String content;   //文章主题内容
    private String channelId; //文章分类
}
