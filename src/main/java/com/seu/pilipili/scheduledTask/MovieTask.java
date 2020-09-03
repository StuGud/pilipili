package com.seu.pilipili.scheduledTask;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.UserRepo;
import com.seu.pilipili.service.CommentService;
import com.seu.pilipili.service.MailService;
import com.seu.pilipili.service.MovieService;
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
    final
    MailService mailService;
    final
    UserRepo userRepo;

    public MovieTask(CommentService commentService, MovieService movieService, MailService mailService, UserRepo userRepo) {
        this.commentService = commentService;
        this.movieService = movieService;
        this.mailService = mailService;
        this.userRepo = userRepo;
    }

    @Scheduled(fixedRate = 24*60*60*1000)
    public void calculateScore(){
        System.out.println("开始计算电影评分");
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movie : movies) {
            int scoreSum=0;
            int scoreNum=0;
            List<Comment> comments = commentService.getCommentsByMovieId(movie.getId());
            for (Comment comment : comments) {
                if (comment.getScore()>=0&&comment.getScore()<=10){
                    scoreNum++;
                    scoreSum+=comment.getScore();
                }
            }
            if (scoreNum!=0) {
                movieService.modifyScore(movie.getId(), scoreSum / scoreNum, scoreNum);
            }
        }
    }

    /**
     * 电影推荐算法还没写
     */
    @Scheduled(fixedRate = 24*60*60*1000)
    public void recommendByMail(){
        List<Movie> movies = movieService.getPageByRandom(0);
        if (movies.get(0) != null) {
            Iterable<User> users = userRepo.findAll();
            String content = String.valueOf(movies.get(0));
            for (User user : users) {
                mailService.sendTxtMail(user.getMail(), "每日电影推荐", content);
            }
        }
    }
}
