package com.seu.pilipili.controller;

import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.service.MovieService;
import com.seu.pilipili.service.MovieServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class MovieController {
    final
    MovieService movieService;
    final
    MovieServiceV2 movieServiceV2;

    public MovieController(MovieService movieService, MovieServiceV2 movieServiceV2) {
        this.movieService = movieService;
        this.movieServiceV2 = movieServiceV2;
    }

    /**
     * 获取电影封面
     * @param movieId
     * @return
     */
    @GetMapping("/movie/image/{movieId}")
    public File getMovieImage(@PathVariable("movieId") long movieId){
        return movieService.getImageFile(movieId);
    }

    @GetMapping("/movie/{movieId}")
    @ResponseBody
    public Movie showMovieByMovieId(@PathVariable("movieId") long movieId){
        return movieService.getByMovieId(movieId);
    }



    @GetMapping("/movies/findByNameLike/{page}")
    @ResponseBody
    public List<Movie> showPage(String name,@PathVariable("page") int page){
        return movieService.getPageByName(name, page);
    }

    @GetMapping("/movies/random/{page}")
    @ResponseBody
    public List<Movie> showMovies(@PathVariable("page") int page){
        return movieService.getPageByRandom(page);
    }

    @GetMapping("/movies/scoreDESC/{page}")
    @ResponseBody
    public List<Movie> showMoviesByScoreDESC(@PathVariable("page") int page){
        return movieService.getPageByScoreDESC(page);
    }

    @GetMapping("/movies/classification/{classification}/{page}")
    @ResponseBody
    public List<Movie> showMoviesByClassification(@PathVariable("classification") String classification,@PathVariable("page") int page){
        return movieService.getPageByClassification(classification,page);
    }



    //==================================================
    //version2.0
    @GetMapping("/moviesV2/findByNameLike/{page}")
    @ResponseBody
    public List<Movie> showPage2(String name,@PathVariable("page") int page) throws IOException {
        return movieServiceV2.getPageByName(name, page);
    }

    @GetMapping("/moviesV2/random/{page}")
    @ResponseBody
    public List<Movie> showMovies2(@PathVariable("page") int page) throws IOException {
        return movieServiceV2.getPageByRandom(page);
    }

    @GetMapping("/moviesV2/scoreDESC/{page}")
    @ResponseBody
    public List<Movie> showMoviesByScoreDESC2(@PathVariable("page") int page) throws IOException {
        return movieServiceV2.getPageByScoreDESC(page);
    }

    @GetMapping("/moviesV2/classification/{classification}/{page}")
    @ResponseBody
    public List<Movie> showMoviesByClassification2(@PathVariable("classification") String classification,@PathVariable("page") int page) throws IOException {
        return movieServiceV2.getPageByClassification(classification,page);
    }
}
