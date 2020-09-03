package com.seu.pilipili.repo;

import com.seu.pilipili.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MovieRepo extends PagingAndSortingRepository<Movie,Long> {
    List<Movie> findByNameLike(String name, Pageable pageable);
}
