package com.seu.pilipili.service;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.CommentDetails;

import java.io.IOException;
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

    /**
     * 获取所有评论
     * @param movieId
     * @return
     */
    List<Comment> getCommentsByMovieId(long movieId);

    /**
     * 随机分页评论
     * @return
     */
    List<Comment> getPage(int page);

    /**
     * 按时间倒序分页
     * @param page
     * @return
     */
    List<Comment> getPageSortedByCreatedAtDESC(int page);

    /**
     * 按点赞数倒序分页
     * @param page
     * @return
     */
    List<Comment> getPageSortedByApprovalNumDESC(int page);

    /**
     * 根据评论号找到对应评论
     * @param commentId
     * @return
     */
    Comment getByCommentId(long commentId);

    List<CommentDetails> getCommentsDetailsByMovieId(long movieId) throws IOException;
}
