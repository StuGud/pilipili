package com.seu.pilipili.service.impl;

import com.seu.pilipili.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieServiceImplTest {

    @Autowired
    MovieService movieService;

    @Test
    void setImageDirectory() {
        movieService.setImageDirectory(0,null);
    }
}