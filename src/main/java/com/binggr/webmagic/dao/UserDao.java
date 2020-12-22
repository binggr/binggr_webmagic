package com.binggr.webmagic.dao;

import com.binggr.webmagic.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户
 * @author: bing
 * @date: 2020/12/21 22:43
 */
public interface UserDao  extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

}
