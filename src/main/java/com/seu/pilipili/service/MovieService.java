package com.seu.pilipili.service;

import com.seu.pilipili.entity.Movie;

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
}
