package com.seu.pilipili.service;

import com.seu.pilipili.entity.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 发布评论
     * @param comment
     * @return
     */
    Comment newComment(Comment comment);

    /**
     * 暂不支持
     * @param comment
     * @return
     */
    Comment modify(Comment comment);

    Comment modifyApprovalNum(long commentId,boolean change);

    List<Comment> getCommentsByMovieId(long movieId);
}
