package com.seu.pilipili.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String classification;
    /**
     * 时长，以分钟为单位
     */
    private int durationM;
    private String introduction;
    /**
     * 封面的路径
     */
    private String imageDirectory;
    private String movieURL;
    /**
     * 级别
     */
    private String level;
    /**
     *评分    10分制？
     */
    private int score;
    /**
     * 参与评价的人数
     */
    private int scoreNum;
}
