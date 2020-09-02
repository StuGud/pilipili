package com.seu.pilipili.scheduledTask;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.service.CommentService;
import com.seu.pilipili.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 在repo层上还是service层上?
 */
@Component("MovieTask")
public class MovieTask {
    final
    CommentService commentService;
    final
    MovieService movieService;

    public MovieTask(CommentService commentService, MovieService movieService) {
        this.commentService = commentService;
        this.movieService = movieService;
    }

    @Scheduled(fixedRate = 24*60*60*1000)
    public void calculateScore(){
        System.out.println("开始计算电影评分");
        int scoreSum=0;
        int scoreNum=0;
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movie : movies) {
            List<Comment> comments = commentService.getCommentsByMovieId(movie.getId());
            for (Comment comment : comments) {
                if (comment.getScore()>=0&&comment.getScore()<=10){
                    scoreNum++;
                    scoreSum+=comment.getScore();
                }
            }
            movieService.modifyScore(movie.getId(),scoreSum/scoreNum,scoreNum);
        }

    }
}
