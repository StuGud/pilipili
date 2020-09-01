package com.seu.pilipili.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 评论
 */
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private long movieId;
    /**
     *评分    10分制？
     */
    private int score;
    private String content;
    private Date createdAt;
    /**
     * 赞的个数
     */
    private int approvalNum;
}
