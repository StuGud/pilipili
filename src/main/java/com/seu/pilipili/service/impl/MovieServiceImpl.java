package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.repo.MovieRepo;
import com.seu.pilipili.service.MovieService;
import com.seu.pilipili.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    public static final String WINDOWS_PROFILES_PATH = "D:/pilipili/profiles/";
    public static final String MAC_PROFILES_PATH = "/Users/Gud/Workspace/temporaryFile/profiles/";

    @Value("${pilipili.movie.page.size}")
    int size;

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
        Movie save = movieRepo.findById(movie.getId()).get();
        if (movie.getName() != null) {
            save.setName(movie.getName());
        }
        if (movie.getClassification() != null) {
            save.setClassification(movie.getClassification());
        }
        if (movie.getDurationM() != 0) {
            save.setDurationM(movie.getDurationM());
        }
        if (movie.getIntroduction() != null) {
            save.setIntroduction(movie.getIntroduction());
        }
        if (movie.getImageDirectory() != null) {
            save.setImageDirectory(movie.getImageDirectory());
        }
        if (movie.getMovieURL() != null) {
            save.setMovieURL(movie.getMovieURL());
        }
        if (movie.getLevel() != null) {
            save.setLevel(movie.getLevel());
        }
        return movieRepo.save(movie);
    }

    @Override
    public Movie modifyScore(long movieId, int newScore, int newScoreNum) {
        Movie movie = movieRepo.findById(movieId).get();
        if (newScore >= 0 && newScore <= 10) {
            movie.setScore(newScore);
            movie.setScoreNum(newScoreNum);
        }
        return movieRepo.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepo.findAll();
    }

    @Override
    public Movie setImageDirectory(long movieId, MultipartFile newProfile) {
        Movie movie = movieRepo.findById(movieId).orElse(new Movie());

        // 根据Windows和Mac配置不同的头像保存路径
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : MAC_PROFILES_PATH;

        if (!newProfile.isEmpty()) {

            // 路径存库
            String newProfileName =  System.currentTimeMillis() + newProfile.getOriginalFilename();
            movie.setImageDirectory(newProfileName);
            newProfileName=profilesPath +newProfileName;
            movieRepo.save(movie);

            // 磁盘保存
            BufferedOutputStream out = null;
            try {
                File folder = new File(profilesPath);
                if (!folder.exists())
                    folder.mkdirs();
                out = new BufferedOutputStream(new FileOutputStream(newProfileName));
                // 写入新文件
                out.write(newProfile.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                //设置头像失败
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //设置头像成功
        }
        return movie;
    }

    @Override
    public byte[] getImage(long movieId) {
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : MAC_PROFILES_PATH;
        Optional<Movie> movie=movieRepo.findById(movieId);
        byte[] imageContent=null;
        if(movie.isPresent()){
            String imageDirectory=movie.get().getImageDirectory();
            try {
                imageContent = FileUtil.imageToByte(new File(profilesPath+imageDirectory));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return imageContent;
    }

    @Override
    public List<Movie> getPageByRandom(int page) {
        Pageable pageable =PageRequest.of(page,size);
        return  movieRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Movie> getPageByScoreDESC(int page) {
        Pageable pageable =PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"score"));
        return movieRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Movie> getPageByName(String name,int page) {
        Pageable pageable =PageRequest.of(page,size);
        return movieRepo.findByNameLike("%"+name+"%",pageable);
    }
}
