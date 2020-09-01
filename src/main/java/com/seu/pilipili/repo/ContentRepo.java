package com.seu.pilipili.repo;

import com.seu.pilipili.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContentRepo extends PagingAndSortingRepository<Comment,Long> {
}
