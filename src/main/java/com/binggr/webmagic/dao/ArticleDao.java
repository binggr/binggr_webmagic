package com.binggr.webmagic.dao;

import com.binggr.webmagic.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 文章
 * @author: bing
 * @date: 2020/12/21 19:51
 */
public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {


}

