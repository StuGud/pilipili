package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.CommentRepo;
import com.seu.pilipili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    final
    CommentRepo commentRepo;

    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public Comment newComment(Comment comment) {
        return commentRepo.save(comment);
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
}
