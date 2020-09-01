package com.seu.pilipili.repo;

import com.seu.pilipili.entity.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepo extends PagingAndSortingRepository<Movie,Long> {
}
