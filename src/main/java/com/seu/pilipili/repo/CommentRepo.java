package com.seu.pilipili.repo;

import com.seu.pilipili.entity.Comment;
import com.seu.pilipili.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepo extends PagingAndSortingRepository<Comment,Long> {
    List<Comment> findAllByMovieId(long movieId);
}
