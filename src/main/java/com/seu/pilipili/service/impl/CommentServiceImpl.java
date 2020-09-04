package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.CommentDetails;
import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.CommentRepo;
import com.seu.pilipili.service.CommentService;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    final
    CommentRepo commentRepo;
    final
    UserService userService;

    @Value("${pilipili.comment.page.size}")
    int size;

    public CommentServiceImpl(CommentRepo commentRepo, UserService userService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
    }

    @Override
    public Comment newComment(Comment comment) {
        Comment save=new Comment();
        save.setMovieId(comment.getMovieId());
        save.setContent(comment.getContent());
        save.setUserId(comment.getUserId());
        if(comment.getScore()<0){
            save.setScore(5);
        }else if (comment.getScore()>10){
            save.setScore(10);
        }else{
            save.setScore(comment.getScore());
        }
        save.setApprovalNum(0);
        save.setCreatedAt(new Date());
        return commentRepo.save(save);
    }

    @Override
    public Comment modify(Comment comment) {
        return null;
    }

    @Override
    public Comment modifyApprovalNum(long commentId, boolean change) {
        Comment comment=commentRepo.findById(commentId).get();
        int approvalNum= comment.getApprovalNum();
        if (change){
            comment.setApprovalNum(approvalNum+1);
        }else{
            comment.setApprovalNum(approvalNum-1);
        }
        return commentRepo.save(comment);
    }

    @Override
    public List<Comment> getCommentsByMovieId(long movieId) {
        return commentRepo.findAllByMovieId(movieId);
    }

    @Override
    public List<Comment> getPage(int page) {
        Pageable pageable = PageRequest.of(page,size);
        return  commentRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Comment> getPageSortedByCreatedAtDESC(int page) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createdAt"));
        return  commentRepo.findAll(pageable).getContent();
    }

    @Override
    public List<Comment> getPageSortedByApprovalNumDESC(int page) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"approvalNum"));
        return  commentRepo.findAll(pageable).getContent();
    }

    @Override
    public Comment getByCommentId(long commentId) {
        Optional<Comment> comment = commentRepo.findById(commentId);
        return comment.get();
    }

    @Override
    public List<CommentDetails> getCommentsDetailsByMovieId(long movieId) throws IOException {
        List<Comment> comments = commentRepo.findAllByMovieId(movieId);
        List<CommentDetails> commentDetailsList=null;
        for(Comment comment:comments){
            commentDetailsList.add(new CommentDetails(
                    userService.getDetails(comment.getUserId()).getUsername(),
                    userService.getProfileBase64(comment.getUserId()),
                    comment
            ));
        }
        return commentDetailsList;
    }
}
