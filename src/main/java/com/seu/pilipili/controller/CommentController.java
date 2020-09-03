package com.seu.pilipili.controller;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.User;
import com.seu.pilipili.service.CommentService;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    final
    CommentService commentService;
    final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/findByMovieId/{movieId}")
    @ResponseBody
    public List<Comment> showCommentsByMovieId(@PathVariable("movieId") long movieId){
        return commentService.getCommentsByMovieId(movieId);
    }

    /**
     *
     * @param commentId
     * @return
     */
    @GetMapping("/{commentId}/approve")
    @ResponseBody
    public Comment approve(@PathVariable("commentId") long commentId,String username,String password){
        User user= userService.login(username,password);
        if(user.getApprovalNum()>=0){
            userService.modifyApprovalNum(user.getId(), false);
            return commentService.modifyApprovalNum(commentId,true);
        }else{
            return null;
        }
    }
}
