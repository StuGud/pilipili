package com.seu.pilipili.repo;

import com.seu.pilipili.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    boolean existsByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
