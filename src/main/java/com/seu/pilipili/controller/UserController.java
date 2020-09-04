package com.seu.pilipili.controller;

import com.seu.pilipili.entity.User;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

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
    @ResponseBody
    public User login(User user){
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        loginUser.setPassword("你猜");
        return loginUser;
    }

    @PostMapping("/register")
    @ResponseBody
    public User register(User user){
        if(userService.existsUsername(user.getUsername())){
            return null;
        }else{
            User registerUser = userService.register(user);
            return registerUser;
        }
    }

    @GetMapping("/{userId}/profile")
    @ResponseBody
    public File showProfile(@PathVariable("userId") long userId){
        return userService.getProfile(userId);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public User showDetails(@PathVariable("userId") long userId){
        return userService.getDetails(userId);
    }
}
