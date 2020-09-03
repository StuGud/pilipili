package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.Movie;
import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.CommentRepo;
import com.seu.pilipili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    final
    CommentRepo commentRepo;

    @Value("${pilipili.comment.page.size}")
    int size;

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
}
