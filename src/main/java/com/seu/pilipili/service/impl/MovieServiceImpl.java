package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.repo.MovieRepo;
import com.seu.pilipili.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    final
    MovieRepo movieRepo;

    public MovieServiceImpl(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie release(Movie movie) {
        return movieRepo.save(movie);
    }

    @Override
    public Movie modify(Movie movie) {
        Movie save=movieRepo.findById(movie.getId()).get();
        if(movie.getName()!=null){
            save.setName(movie.getName());
        }
        if(movie.getClassification()!=null){
            save.setClassification(movie.getClassification());
        }
        if(movie.getDurationM()!=0){
            save.setDurationM(movie.getDurationM());
        }
        if(movie.getIntroduction()!=null){
            save.setIntroduction(movie.getIntroduction());
        }
        if(movie.getImageDirectory()!=null){
            save.setImageDirectory(movie.getImageDirectory());
        }
        if(movie.getMovieURL()!=null){
            save.setMovieURL(movie.getMovieURL());
        }
        if(movie.getLevel()!=null){
            save.setLevel(movie.getLevel());
        }
        return movieRepo.save(movie);
    }

    @Override
    public Movie modifyScore(long movieId, int newScore,int newScoreNum) {
        Movie movie=movieRepo.findById(movieId).get();
        if(newScore>=0&&newScore<=10){
           movie.setScore(newScore);
           movie.setScoreNum(newScoreNum);
        }
        return movieRepo.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepo.findAll();
    }
}
