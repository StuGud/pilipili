package com.seu.pilipili.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_comment")
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
