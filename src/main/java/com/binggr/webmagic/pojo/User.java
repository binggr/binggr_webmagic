package com.binggr.webmagic.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体类
 * @author: bing
 * @date: 2020/12/21 22:44
 */
@Entity
@Data
@Table(name = "tb_user")
public class User {
    @Id
    private String id;      //用户ID
    private String name;    //用户昵称
    private String imgUrl;  //用户头像地址
}
