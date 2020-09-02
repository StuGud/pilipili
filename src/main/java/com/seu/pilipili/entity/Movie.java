package com.seu.pilipili.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_movie")
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
    private double score;
    /**
     * 参与评价的人数
     */
    private int scoreNum;
}
