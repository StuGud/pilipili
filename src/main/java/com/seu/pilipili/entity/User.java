package com.seu.pilipili.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户基本信息
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private String phone;
    /**
     * 用户级别
     */
    private String level;
    /**
     * 头像
     */
    private String profileURL;
    /**
     * 一天最多评论的次数，定时刷新
     */
     private int approvalNum;
}
