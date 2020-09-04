package com.seu.pilipili.service;

import com.seu.pilipili.entity.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 用Base64覆盖movie中imageDirectory的值
 */
public interface MovieServiceV2 {

    //Movie getByMovieId(long movieId);

    /**
     * 随机分页
     * @param page
     * @return
     */
    List<Movie> getPageByRandom(int page) throws IOException;

    /**
     * 按得分降序排列
     * @param page
     * @return
     */
    List<Movie> getPageByScoreDESC(int page) throws IOException;

    List<Movie> getPageByName(String name,int page) throws IOException;

    List<Movie> getPageByClassification(String classification,int page) throws IOException;
}
