package com.seu.pilipili.service.impl;

import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.UserRepo;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final
    UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(User user) {
        return userRepo.save(user);
    }

    @Override
    public User login(String username,String password) {
        return userRepo.findByUsernameAndPassword(username,password);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User modify(User user) {
        User save = userRepo.findById(user.getId()).get();
        if (user.getUsername()!=null){
            save.setUsername(user.getUsername());
        }
        if(user.getPassword()!=null){
            save.setPassword(user.getPassword());
        }
        if (user.getPhone()!=null){
            save.setPhone(user.getPhone());
        }
        if(user.getLevel()!=null){
            save.setPhone(user.getLevel());
        }
        if (user.getProfileURL()!=null){
            save.setProfileURL(user.getProfileURL());
        }
        return userRepo.save(save);
    }

    @Override
    public User modifyApprovalNum(long userId,boolean change) {
        User user=userRepo.findById(userId).get();
        int approvalNum= user.getApprovalNum();
        if (change){
            user.setApprovalNum(approvalNum+1);
        }else{
            user.setApprovalNum(approvalNum-1);
        }
        return userRepo.save(user);
    }
}
