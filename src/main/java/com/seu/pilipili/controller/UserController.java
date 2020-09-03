package com.seu.pilipili.controller;

import com.seu.pilipili.entity.User;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    User testUser=new User(1,"张三","123456","18851103232","普通会员","d:/profile",3);

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public User login(@RequestBody User user){
        //User loginUser = userService.login(user.getUsername(), user.getPassword());
        //return loginUser;
        return testUser;
    }
}
