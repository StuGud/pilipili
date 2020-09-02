package com.seu.pilipili.controller;

import com.seu.pilipili.service.MovieService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
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
    @GetMapping("/image/{movieId}")
    public ResponseEntity<byte[]> getMovieImage(@PathVariable("movieId") long movieId){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(movieService.getImage(movieId), headers, HttpStatus.OK);
    }
}
