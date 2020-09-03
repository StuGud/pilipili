package com.seu.pilipili.service;

import com.seu.pilipili.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MovieService {
    /**
     * 上线新的影片
     * @param movie
     * @return
     */
    Movie release(Movie movie);
    Movie modify(Movie movie);
    Movie modifyScore(long movieId,int newScore,int newScoreNum);
    List<Movie> getAllMovies();

    Movie getByMovieId(long movieId);
    /**
     * 设置电影封面
     * @param movieId
     * @param newProfile
     * @return
     */
    Movie setImageDirectory(long movieId, MultipartFile newProfile);

    byte[] getImage(long movieId);

    /**
     * 随机分页
     * @param page
     * @return
     */
    List<Movie> getPageByRandom(int page);

    /**
     * 按得分降序排列
     * @param page
     * @return
     */
    List<Movie> getPageByScoreDESC(int page);

    List<Movie> getPageByName(String name,int page);

    List<Movie> getPageByClassification(String classification,int page);
}
