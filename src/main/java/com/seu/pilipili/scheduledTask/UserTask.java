package com.seu.pilipili.scheduledTask;

import com.seu.pilipili.entity.User;
import com.seu.pilipili.repo.UserRepo;
import com.seu.pilipili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserTask")
public class UserTask {
    final
    UserRepo userRepo;

    public UserTask(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Scheduled(fixedRate = 24*60*60*1000)
    public void resetApprovalNum(){

        List<User> userList= (List<User>) userRepo.findAll();
        for(User user:userList){
            if ("充话费送的会员".equals(user.getLevel())){
                user.setApprovalNum(3);
            }else if ("普通会员".equals(user.getLevel())){
                user.setApprovalNum(8);
            }else{
                user.setApprovalNum(3);
            }
            userRepo.save(user);
        }
    }
}
