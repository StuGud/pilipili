package com.seu.pilipili.controller;

import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.service.MovieService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MovieController {
    final
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * 获取电影封面
     * @param movieId
     * @return
     */
    @GetMapping("/movie/image/{movieId}")
    public ResponseEntity<byte[]> getMovieImage(@PathVariable("movieId") long movieId){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(movieService.getImage(movieId), headers, HttpStatus.OK);
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

    @GetMapping("/movie/{movieId}")
    @ResponseBody
    public Movie showMovieByMovieId(@PathVariable("movieId") long movieId){
        return movieService.getByMovieId(movieId);
    }

}
