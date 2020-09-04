package com.seu.pilipili.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 用户基本信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String mail;
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
