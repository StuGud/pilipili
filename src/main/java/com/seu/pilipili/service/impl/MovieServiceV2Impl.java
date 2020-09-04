package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.repo.MovieRepo;
import com.seu.pilipili.service.MovieServiceV2;
import com.seu.pilipili.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public class MovieServiceV2Impl implements MovieServiceV2 {
    @Value("${pilipili.profilePath.WIN}")
    String WINDOWS_PROFILES_PATH;
    @Value("${pilipili.profilePath.MAC}")
    String MAC_PROFILES_PATH;

    @Value("${pilipili.movie.page.size}")
    int size;

    final
    MovieRepo movieRepo;

    public MovieServiceV2Impl(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }


    @Override
    public List<Movie> getPageByRandom(int page) throws IOException {
        Pageable pageable = PageRequest.of(page,size);
        List<Movie> movies = movieRepo.findAll(pageable).getContent();
        return replace(movies);
    }

    @Override
    public List<Movie> getPageByScoreDESC(int page) throws IOException {
        Pageable pageable =PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"score"));
        List<Movie> movies =  movieRepo.findAll(pageable).getContent();
        return replace(movies);
    }

    @Override
    public List<Movie> getPageByName(String name, int page) throws IOException {
        Pageable pageable =PageRequest.of(page,size);
        List<Movie> movies =  movieRepo.findByNameLike("%"+name+"%",pageable);
        return replace(movies);
    }

    @Override
    public List<Movie> getPageByClassification(String classification, int page) throws IOException {
        Pageable pageable =PageRequest.of(page,size);
        List<Movie> movies =  movieRepo.findAllByClassification(classification,pageable);
        return replace(movies);
    }

    private List<Movie> replace(List<Movie> movies) throws IOException {
        for (Movie movie:movies){
            String relativePath=movie.getImageDirectory();
            movie.setImageDirectory(FileUtil.fileToBase64(relativePath));
        }
        return movies;
    }
}
